package nals.hrm.api_nals_hrm.controller;

import nals.hrm.api_nals_hrm.dto.APIResponseDTO;
import nals.hrm.api_nals_hrm.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProjectCompanyDashboardController {
  @Autowired
  ProjectService projectService;

  @RequestMapping(value = "/dashboard/project-company", method = RequestMethod.GET)
  @PreAuthorize("hasAuthority('PO') or hasAuthority('SM/AL')")
  public APIResponseDTO projectCompanyDashboard() {
    return new APIResponseDTO(200, "Success!", projectService.projectCompanyDashboard());
  }

}
