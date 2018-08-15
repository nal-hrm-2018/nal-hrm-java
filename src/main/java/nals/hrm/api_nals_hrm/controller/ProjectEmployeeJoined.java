package nals.hrm.api_nals_hrm.controller;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import nals.hrm.api_nals_hrm.dto.APIResponseDTO;
import nals.hrm.api_nals_hrm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProjectEmployeeJoined {
//    @Autowired
//    EmployeeService employeeService;
//
//    @RequestMapping( value = "/list/project/employee?",params = { "id"}, method = RequestMethod.GET )
////    @PreAuthorize("hasAuthority('HR')")
//    @ApiResponses(value = {//
//            @ApiResponse(code = 400, message = "Something went wrong"), //
//            @ApiResponse(code = 403, message = "Access denied"), //
//            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
//    public APIResponseDTO getProjectEmployeeJoined(@RequestParam("id")  int id) {
//        return new APIResponseDTO(200,"Success!",employeeService.findByIdEmployeeAndIsEmployee(id,1));
//    }
}
