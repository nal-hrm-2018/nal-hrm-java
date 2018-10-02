package nals.hrm.api_nals_hrm.service;


import nals.hrm.api_nals_hrm.define.Define;
import nals.hrm.api_nals_hrm.dto.*;
import nals.hrm.api_nals_hrm.entities.Employee;
import nals.hrm.api_nals_hrm.entities.EmployeeType;
import nals.hrm.api_nals_hrm.exception.CustomException;
import nals.hrm.api_nals_hrm.repository.EmployeeRepository;
import nals.hrm.api_nals_hrm.repository.EmployeeTypeRepository;
import nals.hrm.api_nals_hrm.repository.RoleRepository;
import nals.hrm.api_nals_hrm.security.JwtTokenProvider;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeTypeRepository employeeTypeRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ModelMapper modelMapper;

    public String login(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return jwtTokenProvider.createToken(username);
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", 422);
        }
    }


    public Employee findByEmail(String email) {
        return employeeRepository.findByEmailAndDeleteFlagAndWorkStatus(email, 0, 0);
    }


    //get profile of user login by token
    public ProfileDTO getProfile(HttpServletRequest req) {

        Employee employee = employeeRepository.findByEmailAndDeleteFlagAndWorkStatus(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)),0 , 0);
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO = modelMapper.map(employee, profileDTO.getClass());

        //config gender
        GenderDTO gender = new GenderDTO();
        gender.setGender(employee.getGender()); //gender 1->female, 2->male, 3->others
        profileDTO.setGenderDTO(gender);

        //config marital status
        MaritalStatusDTO maritalStatus = new MaritalStatusDTO();
        maritalStatus.setMaritalStatus(employee.getMaritalStatus());
        profileDTO.setMaritalStatusDTO(maritalStatus);

        return profileDTO;
    }

    //get list employee
    public ListDTO getListEmployees(Optional<Integer> page, Optional<Integer> pageSize) {
        //evaluate page size
        //return pageSize != null ? pageSize : Define.initialPageSize
        int evalPageSize = pageSize.orElse(Define.initialPageSize);
        //evaluate page
        int evalPage = (page.orElse(0) < 1) ? Define.initialPage : page.get() - 1;
        //find all employee who have isEmployee = 1 and deleteFlag = 0 (paging result)
        List<Employee> listEmployees  = employeeRepository.findByIsEmployeeAndDeleteFlag(1, 0, PageRequest.of(evalPage, evalPageSize));
        ArrayList<Object> listResult = new ArrayList<>();
        listEmployeeStructure(listEmployees,listResult);
        int total = employeeRepository.findByIsEmployeeAndDeleteFlag(1, 0).size();
        return new ListDTO(total, listResult);

    }


    //view basic profile any employee by idEmployee
    public ProfileDTO viewBasicEmployee(int id) {

        Employee employee = employeeRepository.findByIdEmployeeAndIsEmployeeAndDeleteFlag(id, 1, 0);
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO = modelMapper.map(employee, profileDTO.getClass());

        //config gender
        GenderDTO gender = new GenderDTO();
        gender.setGender(employee.getGender()); //gender 1->female, 2->male, 3->others
        profileDTO.setGenderDTO(gender);

        //config marital status
        MaritalStatusDTO maritalStatus = new MaritalStatusDTO();
        maritalStatus.setMaritalStatus(employee.getMaritalStatus());
        profileDTO.setMaritalStatusDTO(maritalStatus);

        return profileDTO;
    }

    //search employee by email or name
    public ListDTO findByEmailContainingOrNameEmployeeContainingAndIsEmployeeAndDeleteFlag(String email, String name, int isEmployee, int deleteFlag, Optional<Integer> page, Optional<Integer> pageSize) {
        int evalPageSize = pageSize.orElse(Define.initialPageSize);
        int evalPage = (page.orElse(0) < 1) ? Define.initialPage : page.get() - 1;

        //find all employee(isEmployee = 1 and deleteFlag = 0) by name or email
        // paging result
        List<Employee> listEmployees  = employeeRepository.findByEmailContainingOrNameEmployeeContainingAndIsEmployeeAndDeleteFlag(email,name,1, 0, PageRequest.of(evalPage, evalPageSize));

        ArrayList<Object> listResult = new ArrayList<>();
        listEmployeeStructure(listEmployees,listResult);
        int total = employeeRepository.findByEmailContainingOrNameEmployeeContainingAndIsEmployeeAndDeleteFlag(email, name,1,0).size();
        return new ListDTO(total, listResult);
    }
    
    public void listEmployeeStructure(List<Employee> listEmployees, ArrayList<Object> listResult){
        for (Employee objEmp : listEmployees) {
            ProfileDTO profileDTO = new ProfileDTO();
            //mapping
            profileDTO = modelMapper.map(objEmp, profileDTO.getClass());

            //config gender
            GenderDTO gender = new GenderDTO();
            gender.setGender(objEmp.getGender()); //gender 1->female, 2->male, 3->others
            profileDTO.setGenderDTO(gender);

            //config marital status
            MaritalStatusDTO maritalStatus = new MaritalStatusDTO();
            maritalStatus.setMaritalStatus(objEmp.getMaritalStatus());
            profileDTO.setMaritalStatusDTO(maritalStatus);

            listResult.add(profileDTO);
        }
    }


    public EmployeeDashboardDTO employeeInformationDashboard() {
        int totalEmployee;
        int official = 0;
        int probation = 0;
        int internship = 0;
        int partTime = 0;
        String employeeTypeName;

        List<Employee> employeeList = employeeRepository.findByIsEmployeeAndWorkStatusAndDeleteFlag(1, 0, 0);
        for(Employee objEmployee : employeeList){
            if(objEmployee.getEmployeeTypeId() != null){
                employeeTypeName = objEmployee.getEmployeeType().getNameEmployeeType();
                switch (employeeTypeName){
                    case "Internship":
                        internship += 1;
                        break;
                    case "Probationary":
                        probation += 1;
                        break;
                    case "FullTime":
                        official += 1;
                        break;
                    case "PartTime":
                        partTime += 1;
                        break;
                }
            }

        }
        totalEmployee = employeeList.size();
        return new EmployeeDashboardDTO(totalEmployee, official, probation, internship, partTime);
    }

    public EventDashboardDTO eventDashboard() {
        int newEmployees = 0;
        int birthdays = 0;
        int employeesQuit = 0;

        newEmployees = employeeRepository.newEmployee();
        birthdays = employeeRepository.birthdays();
        employeesQuit = employeeRepository.employeeQuit();
        return new EventDashboardDTO(newEmployees, birthdays, employeesQuit);
    }
}


