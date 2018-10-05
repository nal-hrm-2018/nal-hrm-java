package nals.hrm.api_nals_hrm.controller;


import nals.hrm.api_nals_hrm.dto.APIResponseDTO;
import nals.hrm.api_nals_hrm.service.OvertimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class DeleteOvertimeController {
  @Autowired
  OvertimeService overtimeService;

  @RequestMapping(value = "/overtime/delete/{id}", method = RequestMethod.DELETE)
  public APIResponseDTO deleteOvertime(@PathVariable("id") int id, HttpServletRequest req) {
    return new APIResponseDTO(200, "Success!", overtimeService.deleteOvertime(id, req));
  }
}
