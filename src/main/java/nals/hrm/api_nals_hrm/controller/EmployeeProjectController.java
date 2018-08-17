package nals.hrm.api_nals_hrm.controller;

import nals.hrm.api_nals_hrm.dto.APIResponseDTO;
import nals.hrm.api_nals_hrm.entities.Employee;
import nals.hrm.api_nals_hrm.entities.Processes;
import nals.hrm.api_nals_hrm.service.EmployeeService;
import nals.hrm.api_nals_hrm.service.ProcessesService;
import nals.hrm.api_nals_hrm.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeProjectController {
    @Autowired
    EmployeeService employeeService;

    @Autowired
    ProjectService projectService;

    @Autowired
    ProcessesService processesService;

    @RequestMapping( value = "/project/employee",params = { "id"}, method = RequestMethod.GET )
    @PreAuthorize("hasAuthority('view_employee_project')")
    public APIResponseDTO getProjectEmployeeJoined(@RequestParam("id")  int id) {
        return new APIResponseDTO(200,"Success!",projectService.getListProjectByIdEmployee(id));
    }
}
