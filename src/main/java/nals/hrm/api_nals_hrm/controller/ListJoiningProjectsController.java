package nals.hrm.api_nals_hrm.controller;

import nals.hrm.api_nals_hrm.dto.APIResponseDTO;
import nals.hrm.api_nals_hrm.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class ListJoiningProjectsController {

  @Autowired
  ProjectService projectService;

  @RequestMapping(value = "/list/project/joining", method = RequestMethod.GET)
  public APIResponseDTO getListJoiningProjects(HttpServletRequest req) {
    return new APIResponseDTO(200, "Success!", projectService.getListJoiningProjects(req));
  }
}
