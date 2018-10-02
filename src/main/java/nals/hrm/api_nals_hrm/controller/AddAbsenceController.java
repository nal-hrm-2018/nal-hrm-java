package nals.hrm.api_nals_hrm.controller;


import io.swagger.annotations.ApiParam;
import nals.hrm.api_nals_hrm.dto.APIResponseDTO;
import nals.hrm.api_nals_hrm.entities.Absence;
import nals.hrm.api_nals_hrm.service.AbsenceService;
import nals.hrm.api_nals_hrm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AddAbsenceController {
    @Autowired
    AbsenceService absenceService;

    @RequestMapping( value = "/absence/add", method = RequestMethod.POST)
    public APIResponseDTO addAbsence(@ApiParam @RequestBody Absence absence, HttpServletRequest req) {
        return new APIResponseDTO(200,"Success!",absenceService.addAbsence(absence, req));
    }
}
