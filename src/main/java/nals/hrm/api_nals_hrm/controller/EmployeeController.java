package nals.hrm.api_nals_hrm.controller;

import io.swagger.annotations.*;
import nals.hrm.api_nals_hrm.dto.APIResponseDTO;
import nals.hrm.api_nals_hrm.dto.EmployeeDTO;
import nals.hrm.api_nals_hrm.entities.Employee;
import nals.hrm.api_nals_hrm.respository.RoleRepository;
import nals.hrm.api_nals_hrm.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping("/api")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/login")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Something went wrong"), @ApiResponse(code = 422, message = "Invalid username/password supplied")})
    public APIResponseDTO login(@ApiParam("username") @RequestParam String username, @ApiParam("password") @RequestParam String password) {
        System.out.println("pass:"+employeeService.findByEmail(username).getPassword());
        if(employeeService.findByEmail(username)!= null && BCrypt.checkpw(password, employeeService.findByEmail(username).getPassword().replace("$2y$","$2a$")) == true)
            return new APIResponseDTO(200, "Login success!", employeeService.login(username, password));
      else return new APIResponseDTO(500,"Invalid username/password supplied",null);
    }


    @GetMapping(value = "/profile")
    @PreAuthorize("hasAuthority('DEV') or hasAuthority('HR') or hasAuthority('PO') or hasAuthority('ACCOUNTANT') or hasAuthority('SM')")
    @ApiOperation(value = "${EmployeeController.profile}", response = EmployeeDTO.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public APIResponseDTO getProfile(HttpServletRequest req) {
        return new APIResponseDTO(200,"Success!",modelMapper.map(employeeService.getProfile(req), EmployeeDTO.class));
    }



    @GetMapping(value = "/list/employee")
    @PreAuthorize("hasAuthority('HR')")
    public APIResponseDTO getListEmployees(){
        List<Employee> employeeList = employeeService.findByDeleteFlag(0);
        for (Employee employee :employeeList) {
           employee.setRole(roleRepository.findByIdRole(employee.getIdRole()));
        }

        return new APIResponseDTO(200, "Success!",employeeList);
    }

}
