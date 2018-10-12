package nals.hrm.api_nals_hrm.controller;


import nals.hrm.api_nals_hrm.dto.APIResponseDTO;
import nals.hrm.api_nals_hrm.service.OvertimeService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class SearchOvertimeHRController {
  @Autowired
  OvertimeService overtimeService;

  @RequestMapping(value = "/manage/overtime/search", params = {"keyword", "page", "pageSize"}, method = RequestMethod.GET)
  @PreAuthorize("hasAuthority('BO') or hasAuthority('CEO')")
  public APIResponseDTO searchAbsenceEmployee(HttpServletRequest req, @RequestParam("keyword") String keyword, @RequestParam("page") Optional<Integer> page, @RequestParam("pageSize") Optional<Integer> pageSize) {
    return new APIResponseDTO(200, "Success!", overtimeService.searchOvertimeHR(req, keyword, page, pageSize));
  }
}
