package nals.hrm.api_nals_hrm.controller;

import nals.hrm.api_nals_hrm.dto.APIResponseDTO;
import nals.hrm.api_nals_hrm.service.EmployeeService;
import nals.hrm.api_nals_hrm.service.HolidayDefaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api")
public class ListHolidayDefaultController {

    @Autowired
    HolidayDefaultService holidayDefaultService;

    @GetMapping(value = "/holiday/default")
    public APIResponseDTO getListHolidayDefault() {
        return new APIResponseDTO(200,"Success!",holidayDefaultService.getListHolidayDefault());
    }

}

