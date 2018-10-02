package nals.hrm.api_nals_hrm.controller;


import io.swagger.annotations.ApiParam;
import nals.hrm.api_nals_hrm.dto.APIResponseDTO;
import nals.hrm.api_nals_hrm.dto.OvertimeDTO;
import nals.hrm.api_nals_hrm.entities.Overtime;
import nals.hrm.api_nals_hrm.service.OvertimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class ConfirmOvertimeController {
    @Autowired
    OvertimeService overtimeService;

    @RequestMapping( value = "/manage/overtime/confirm/{id}", method = RequestMethod.PUT )
    public APIResponseDTO confirmOvertime(@PathVariable("id") int id, @ApiParam @RequestBody Overtime overtime, HttpServletRequest req) {
        return new APIResponseDTO(200,"Success!", overtimeService.confirmOvertime(id, overtime, req));
    }
}
