package nals.hrm.api_nals_hrm.controller;

import nals.hrm.api_nals_hrm.dto.APIResponseDTO;
import nals.hrm.api_nals_hrm.entities.Processes;
import nals.hrm.api_nals_hrm.service.ProcessesService;
import nals.hrm.api_nals_hrm.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ListProjectPOController {

    @Autowired
    ProcessesService processesService;

    @Autowired
    ProjectService projectService;

    @RequestMapping( value = "/manage/absence/po",params = { "page", "pageSize" }, method = RequestMethod.GET )
    @PreAuthorize("hasAuthority('view_employee_absence_history')")
    public APIResponseDTO getListAbsenceEmployee(HttpServletRequest req, @RequestParam("page") Optional<Integer> page, @RequestParam("pageSize")  Optional<Integer> pageSize) {
        return new APIResponseDTO(200,"Success!",processesService.findProjectProcessesManageRolePO(req,page,pageSize));
    }

}
