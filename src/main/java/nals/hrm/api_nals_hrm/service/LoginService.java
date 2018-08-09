package nals.hrm.api_nals_hrm.service;


import nals.hrm.api_nals_hrm.dto.GenderDTO;
import nals.hrm.api_nals_hrm.dto.MaritalStatusDTO;
import nals.hrm.api_nals_hrm.dto.ProfileDTO;
import nals.hrm.api_nals_hrm.entities.Employee;
import nals.hrm.api_nals_hrm.exception.CustomException;
import nals.hrm.api_nals_hrm.respository.EmployeeRepository;
import nals.hrm.api_nals_hrm.respository.EmployeeTypeRepository;
import nals.hrm.api_nals_hrm.respository.RoleRepository;
import nals.hrm.api_nals_hrm.security.JwtTokenProvider;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@Transactional
public class LoginService {

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
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
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

    public Employee findByIdEmployee(int idEmployee) {
        return employeeRepository.findByIdEmployee(idEmployee);
    }

    public List<Employee> findByDeleteFlag(int deleteFlag) {
        return employeeRepository.findByDeleteFlag(deleteFlag);
    }
}
