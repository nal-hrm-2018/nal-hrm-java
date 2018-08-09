package nals.hrm.api_nals_hrm.controller;

import io.swagger.annotations.*;
import nals.hrm.api_nals_hrm.dto.*;
import nals.hrm.api_nals_hrm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoginController {
    @Autowired
    EmployeeService employeeService;

    @PostMapping("/login")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Something went wrong"), @ApiResponse(code = 422, message = "Invalid username/password supplied")})
    public APIResponseDTO login(@ApiParam("username") @RequestParam String username, @ApiParam("password") @RequestParam String password) {
        return new APIResponseDTO(200, "Login success!", employeeService.login(username, password));
    }
}

