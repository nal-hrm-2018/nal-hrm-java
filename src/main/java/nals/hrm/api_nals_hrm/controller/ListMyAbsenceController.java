package nals.hrm.api_nals_hrm.controller;

import nals.hrm.api_nals_hrm.dto.APIResponseDTO;
import nals.hrm.api_nals_hrm.service.AbsenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ListAbsenceEmployeeByTokenController {

    @Autowired
    AbsenceService absenceService;

    @RequestMapping( value = "/absence",params = { "page", "pageSize" }, method = RequestMethod.GET )
    public APIResponseDTO getListAbsenceEmployeeByToken(HttpServletRequest req, @RequestParam("page") Optional<Integer> page, @RequestParam("pageSize")  Optional<Integer> pageSize) {
        return new APIResponseDTO(200,"Success!",absenceService.getListAbsenceEmployeeByToken(req,page,pageSize));
    }
}
