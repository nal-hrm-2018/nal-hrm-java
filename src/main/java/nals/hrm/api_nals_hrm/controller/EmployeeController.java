package nals.hrm.api_nals_hrm.controller;

import io.swagger.annotations.*;
import nals.hrm.api_nals_hrm.dto.*;
import nals.hrm.api_nals_hrm.entities.Employee;
import nals.hrm.api_nals_hrm.respository.RoleRepository;
import nals.hrm.api_nals_hrm.service.EmployeeService;
import nals.hrm.api_nals_hrm.service.EmployeeTypeService;
import nals.hrm.api_nals_hrm.service.PermissionService;
import nals.hrm.api_nals_hrm.service.TeamService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    EmployeeTypeService employeeTypeService;

    @Autowired
    PermissionService permissionService;

    @Autowired
    TeamService teamService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/login")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Something went wrong"), @ApiResponse(code = 422, message = "Invalid username/password supplied")})
    public APIResponseDTO login(@ApiParam("username") @RequestParam String username, @ApiParam("password") @RequestParam String password) {
//        if(employeeService.findByEmail(username)!= null && BCrypt.checkpw(password, employeeService.findByEmail(username).getPassword().replace("$2y$","$2a$")) == true)
            return new APIResponseDTO(200, "Login success!", employeeService.login(username, password));
//      else return new APIResponseDTO(500,"Invalid username/password supplied",null);
    }


    @GetMapping(value = "/profile")
    @PreAuthorize("hasAuthority('Dev') or hasAuthority('HR') or hasAuthority('PO') or hasAuthority('ACCOUNTANT') or hasAuthority('SM')")
    @ApiOperation(value = "${EmployeeController.profile}", response = EmployeeDTO.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public APIResponseDTO getProfile(HttpServletRequest req) {
        Employee employee = employeeService.getProfile(req);
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO = modelMapper.map(employeeService.getProfile(req), profileDTO.getClass());
        profileDTO.setEmployeeType(employeeTypeService.findByIdEmployeeType(employee.getEmployeeTypeId()));

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
        return new APIResponseDTO(200,"Success!",profileDTO);
    }

}

