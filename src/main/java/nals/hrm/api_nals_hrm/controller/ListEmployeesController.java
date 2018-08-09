package nals.hrm.api_nals_hrm.controller;

import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import nals.hrm.api_nals_hrm.dto.APIResponseDTO;
import nals.hrm.api_nals_hrm.respository.RoleRepository;
import nals.hrm.api_nals_hrm.service.EmployeeService;
import nals.hrm.api_nals_hrm.service.EmployeeTypeService;
import nals.hrm.api_nals_hrm.service.PermissionService;
import nals.hrm.api_nals_hrm.service.TeamService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class ListEmployeesController {
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

//    @GetMapping(value = "/list/employees")
    @RequestMapping( value = "/list/employees",params = { "page", "pageSize" }, method = RequestMethod.GET )
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public APIResponseDTO getListEmployees(HttpServletRequest req, @RequestParam("page")  Optional<Integer> page, @RequestParam("pageSize")  Optional<Integer> pageSize) {
        return new APIResponseDTO(200,"Success!",employeeService.getListEmployees(req,page,pageSize));
    }

}

