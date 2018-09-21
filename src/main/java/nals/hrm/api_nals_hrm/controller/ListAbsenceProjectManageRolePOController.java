package nals.hrm.api_nals_hrm.controller;

import nals.hrm.api_nals_hrm.dto.APIResponseDTO;
import nals.hrm.api_nals_hrm.service.AbsenceService;
import nals.hrm.api_nals_hrm.service.ProcessesService;
import nals.hrm.api_nals_hrm.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ListAbsenceProjectManageRolePOController {

    @Autowired
    ProcessesService processesService;

    @Autowired
    ProjectService projectService;

    @Autowired
    AbsenceService absenceService;

    @RequestMapping( value = "/manage/absence/po/project",params = {"id" }, method = RequestMethod.GET )
    public APIResponseDTO getListAbsenceProjectManageRolePO(@RequestParam("id")  String id, HttpServletRequest req) {
        return new APIResponseDTO(200,"Success!",absenceService.findAbsenceProjectProcessesManageRolePO(id, req));
    }

}
