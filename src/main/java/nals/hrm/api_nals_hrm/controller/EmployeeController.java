package nals.hrm.api_nals_hrm.controller;

import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import nals.hrm.api_nals_hrm.dto.LoginResponseDTO;
import nals.hrm.api_nals_hrm.respository.RoleRepository;
import nals.hrm.api_nals_hrm.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/api/login")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Something went wrong"), @ApiResponse(code = 422, message = "Invalid username/password supplied")})
    public LoginResponseDTO login(@ApiParam("username") @RequestParam String username, @ApiParam("password") @RequestParam String password) {
      //  if(employeeService.findByEmail(username)!= null && BCrypt.checkpw(password, employeeService.findByEmail(username).getPassword()) == true)
            return new LoginResponseDTO(HttpStatus.OK, "Login success!", employeeService.login(username, password));
       // else return new LoginResponseDTO(HttpStatus.resolve(200),"Invalid username/password supplied",null);
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

}
