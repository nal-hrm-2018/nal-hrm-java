package nals.hrm.api_nals_hrm.controller;


import io.swagger.annotations.ApiParam;
import nals.hrm.api_nals_hrm.dto.APIResponseDTO;
import nals.hrm.api_nals_hrm.dto.OvertimeDTO;
import nals.hrm.api_nals_hrm.service.OvertimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class EditOvertimeController {
  @Autowired
  OvertimeService overtimeService;

  @RequestMapping(value = "/overtime/edit/{id}", method = RequestMethod.PUT)
  public APIResponseDTO editOvertime(@PathVariable("id") int id, @ApiParam @RequestBody OvertimeDTO overtimeDTO, HttpServletRequest req) {
    return new APIResponseDTO(200, "Success!", overtimeService.editOvertime(id, overtimeDTO, req));
  }
}
