package nals.hrm.api_nals_hrm.controller;

import nals.hrm.api_nals_hrm.dto.APIResponseDTO;
import nals.hrm.api_nals_hrm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EventDashboardController {
  @Autowired
  EmployeeService employeeService;

  @RequestMapping(value = "/bo/dashboard/event", method = RequestMethod.GET)
  @PreAuthorize("hasAuthority('BO')")
  public APIResponseDTO eventDashboard() {
    return new APIResponseDTO(200, "Success!", employeeService.eventDashboard());
  }

}
