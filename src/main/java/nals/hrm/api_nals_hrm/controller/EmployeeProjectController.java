package nals.hrm.api_nals_hrm.controller;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import nals.hrm.api_nals_hrm.dto.APIResponseDTO;
import nals.hrm.api_nals_hrm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EmployeeProjectController {
    @Autowired
    EmployeeService employeeService;

    @RequestMapping( value = "/list/project/employee?",params = { "id"}, method = RequestMethod.GET )
    @PreAuthorize("hasAuthority('view_employee_project')")
    public APIResponseDTO getProjectEmployeeJoined(@RequestParam("id")  int id) {
//        return new APIResponseDTO(200,"Success!",employeeService.findByIdEmployeeAndIsEmployee(id,1));
        return null;
    }
}
