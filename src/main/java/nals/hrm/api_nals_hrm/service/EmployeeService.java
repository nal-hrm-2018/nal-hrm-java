package nals.hrm.api_nals_hrm.service;


import nals.hrm.api_nals_hrm.define.Define;
import nals.hrm.api_nals_hrm.dto.GenderDTO;
import nals.hrm.api_nals_hrm.dto.ListEmployeeDTO;
import nals.hrm.api_nals_hrm.dto.MaritalStatusDTO;
import nals.hrm.api_nals_hrm.dto.ProfileDTO;
import nals.hrm.api_nals_hrm.entities.Employee;
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
    private RoleRepository roleRepository;


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
        return employeeRepository.findByEmail(email);
    }


    public ProfileDTO getProfile(HttpServletRequest req) {
        Employee employee = employeeRepository.findByEmail(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO = modelMapper.map(employee, profileDTO.getClass());
        profileDTO.setEmployeeType(employeeTypeRepository.findByIdEmployeeType(employee.getEmployeeTypeId()));

        //config gender
        GenderDTO gender = new GenderDTO();
        gender.setGender(employee.getGender()); //gender 1->female, 2->male, 3->others
        profileDTO.setGenderDTO(gender);

        //config marital status
        MaritalStatusDTO maritalStatus = new MaritalStatusDTO();
        maritalStatus.setMaritalStatus(employee.getMaritalStatus());
        profileDTO.setMaritalStatusDTO(maritalStatus);
        profileDTO.setPermission(employee.getPermissions());
        profileDTO.setRole(roleRepository.findByIdRole(employee.getIdRole()));
        profileDTO.setTeams(employee.getTeams());

        return profileDTO;
    }

    public ListEmployeeDTO getListEmployees(Optional<Integer> page, Optional<Integer> pageSize) {
        List<Employee> listEmployees = null;
        ArrayList<ProfileDTO> listProfiles = new ArrayList<ProfileDTO>();
        ProfileDTO profileDTO;
        int evalPageSize = pageSize.orElse(Define.initialPageSize);
        int evalPage = (page.orElse(0) < 1) ? Define.initialPage : page.get() - 1;
        listEmployees = employeeRepository.findByIsEmployeeAndDeleteFlag(1, 0, PageRequest.of(evalPage, evalPageSize));
        for (Employee objEmp : listEmployees) {
            profileDTO = new ProfileDTO();
            profileDTO = modelMapper.map(objEmp, profileDTO.getClass());
            profileDTO.setEmployeeType(employeeTypeRepository.findByIdEmployeeType(objEmp.getEmployeeTypeId()));
            //config gender
            GenderDTO gender = new GenderDTO();
            gender.setGender(objEmp.getGender()); //gender 1->female, 2->male, 3->others
            profileDTO.setGenderDTO(gender);
            //config marital status
            MaritalStatusDTO maritalStatus = new MaritalStatusDTO();
            maritalStatus.setMaritalStatus(objEmp.getMaritalStatus());
            profileDTO.setMaritalStatusDTO(maritalStatus);
            profileDTO.setPermission(objEmp.getPermissions());
            profileDTO.setRole(roleRepository.findByIdRole(objEmp.getIdRole()));
            profileDTO.setTeams(objEmp.getTeams());
            listProfiles.add(profileDTO);
        }
        int total = employeeRepository.findByIsEmployeeAndDeleteFlag(1, 0).size();
        return new ListEmployeeDTO(total, listProfiles);

    }


    public ProfileDTO findByIdEmployeeAndIsEmployeeAndDeleteFlag(int id, int isEmployee, int deleteFlag) {
        Employee employee = employeeRepository.findByIdEmployeeAndIsEmployeeAndDeleteFlag(id, isEmployee, deleteFlag);
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO = modelMapper.map(employee, profileDTO.getClass());
        profileDTO.setEmployeeType(employeeTypeRepository.findByIdEmployeeType(employee.getEmployeeTypeId()));

        //config gender
        GenderDTO gender = new GenderDTO();
        gender.setGender(employee.getGender()); //gender 1->female, 2->male, 3->others
        profileDTO.setGenderDTO(gender);

        //config marital status
        MaritalStatusDTO maritalStatus = new MaritalStatusDTO();
        maritalStatus.setMaritalStatus(employee.getMaritalStatus());
        profileDTO.setMaritalStatusDTO(maritalStatus);
        profileDTO.setPermission(employee.getPermissions());
        profileDTO.setRole(roleRepository.findByIdRole(employee.getIdRole()));
        profileDTO.setTeams(employee.getTeams());

        return profileDTO;
    }

    public Employee findByIdEmployee(int idEmployee) {
        return employeeRepository.findByIdEmployee(idEmployee);
    }

    public Employee findByIdEmployeeAndDeleteFlag(int idEmployee, int deleteFlag) {
        return employeeRepository.findByIdEmployeeAndDeleteFlag(idEmployee,deleteFlag);
    }
}


