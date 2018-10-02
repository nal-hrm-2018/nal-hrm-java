package nals.hrm.api_nals_hrm.controller;

import io.swagger.annotations.ApiParam;
import nals.hrm.api_nals_hrm.dto.APIResponseDTO;
import nals.hrm.api_nals_hrm.entities.Absence;
import nals.hrm.api_nals_hrm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EmployeeInformationDashboardController {
    @Autowired
    EmployeeService employeeService;

    @RequestMapping( value = "/bo/dashboard/employee", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('BO')")
    public APIResponseDTO employeeInformationDashboard() {
        return new APIResponseDTO(200,"Success!", employeeService.employeeInformationDashboard());
    }

}
