package nals.hrm.api_nals_hrm.service;


import nals.hrm.api_nals_hrm.entities.Employee;
import nals.hrm.api_nals_hrm.exception.CustomException;
import nals.hrm.api_nals_hrm.respository.EmployeeRepository;
import nals.hrm.api_nals_hrm.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    public String login(String username, String password) {
        System.out.println("emp: "+employeeRepository.findByEmail("hr1@nal.com").toString());
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            System.out.println("emp2: "+employeeRepository.findByEmail("hr1@nal.com").toString());

           return jwtTokenProvider.createToken(username);

        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public Employee findByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }


    public Employee getProfile(HttpServletRequest req) {
        return employeeRepository.findByEmail(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
    }

    public Employee findByIdEmployee(int idEmployee) {
        return employeeRepository.findByIdEmployee(idEmployee);
    }

    public List<Employee> findByDeleteFlag(int deleteFlag) {
        return employeeRepository.findByDeleteFlag(deleteFlag);
    }
}
