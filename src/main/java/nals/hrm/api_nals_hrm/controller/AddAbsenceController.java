package nals.hrm.api_nals_hrm.controller;


import io.swagger.annotations.ApiParam;
import nals.hrm.api_nals_hrm.dto.APIResponseDTO;
import nals.hrm.api_nals_hrm.entities.Absence;
import nals.hrm.api_nals_hrm.service.AbsenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class AddAbsenceController {
  @Autowired
  AbsenceService absenceService;

  @RequestMapping(value = "/absence/add", method = RequestMethod.POST)
  public APIResponseDTO addAbsence(@ApiParam @RequestBody Absence absence, HttpServletRequest req) {
    return new APIResponseDTO(200, "Success!", absenceService.addAbsence(absence, req));
  }
}
