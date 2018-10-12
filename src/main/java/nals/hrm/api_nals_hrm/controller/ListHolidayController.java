package nals.hrm.api_nals_hrm.controller;

import nals.hrm.api_nals_hrm.dto.APIResponseDTO;
import nals.hrm.api_nals_hrm.service.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class ListHolidayController {

  @Autowired
  HolidayService holidayService;

  @GetMapping(value = "/holiday")
  public APIResponseDTO getListHolidayDefault() {
    return new APIResponseDTO(200, "Success!", holidayService.getListHoliday());
  }

}

