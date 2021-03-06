package nals.hrm.api_nals_hrm.controller;

import nals.hrm.api_nals_hrm.dto.APIResponseDTO;
import nals.hrm.api_nals_hrm.service.AbsenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ListAbsenceByIdEmployeeController {

  @Autowired
  AbsenceService absenceService;

  @RequestMapping(value = "/manage/absence/employee", params = {"id", "page", "pageSize"}, method = RequestMethod.GET)
  @PreAuthorize("hasAuthority('BO') and hasAuthority('view_employee_absence_history')")
  public APIResponseDTO getListAbsenceByIdEmployee(@RequestParam("id") int id, @RequestParam("page") Optional<Integer> page, @RequestParam("pageSize") Optional<Integer> pageSize) {
    return new APIResponseDTO(200, "Success!", absenceService.getListAbsenceByIdEmployee(id, page, pageSize));
  }
}
