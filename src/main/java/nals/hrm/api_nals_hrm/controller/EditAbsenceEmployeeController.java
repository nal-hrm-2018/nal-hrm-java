package nals.hrm.api_nals_hrm.controller;


import io.swagger.annotations.ApiParam;
import nals.hrm.api_nals_hrm.dto.APIResponseDTO;
import nals.hrm.api_nals_hrm.entities.Absence;
import nals.hrm.api_nals_hrm.service.AbsenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class EditAbsenceEmployeeController {
    @Autowired
    AbsenceService absenceService;

    @RequestMapping( value = "/manage/absence/edit/{id}", method = RequestMethod.PUT )
    @PreAuthorize("hasAuthority('edit_absence_employee')")
    public APIResponseDTO editAbsence(@PathVariable("id") int id, @ApiParam @RequestBody Absence absence) {
        return new APIResponseDTO(200,"Success!",absenceService.editAbsence(id, absence));
    }
}
