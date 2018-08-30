package nals.hrm.api_nals_hrm.controller;

import nals.hrm.api_nals_hrm.dto.APIResponseDTO;
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
public class ListProjectManageRolePOController {

    @Autowired
    ProcessesService processesService;

    @Autowired
    ProjectService projectService;

    @RequestMapping( value = "/manage/absence/po",params = { "page", "pageSize" }, method = RequestMethod.GET )
    public APIResponseDTO getListProjectManageRolePO(HttpServletRequest req, @RequestParam("page") Optional<Integer> page, @RequestParam("pageSize")  Optional<Integer> pageSize) {
        return new APIResponseDTO(200,"Success!",projectService.findProjectProcessesManageRolePO(req,page,pageSize));
    }

}
