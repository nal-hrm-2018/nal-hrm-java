package nals.hrm.api_nals_hrm.controller;


import nals.hrm.api_nals_hrm.dto.APIResponseDTO;
import nals.hrm.api_nals_hrm.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/list")
public class CommonAPIController {
    @Autowired
    EmployeeService employeeService;

    @Autowired
    RoleService roleService;

    @Autowired
    TeamService teamService;

    @Autowired
    EmployeeTypeService employeeTypeService;

    @Autowired
    AbsenceTypeService absenceTypeService;

    @Autowired
    DayTypesService dateTypesService;

    @Autowired
    OvertimeStatusesService overtimeStatusesService;

    @RequestMapping( value = "/role",method = RequestMethod.GET )
    @PreAuthorize("hasAuthority('HR') or hasAuthority('PO')")
    public APIResponseDTO getRole() {
        return new APIResponseDTO(200,"Success!",roleService.findAll());
    }

    @RequestMapping( value = "/team",method = RequestMethod.GET )
    @PreAuthorize("hasAuthority('HR') or hasAuthority('PO')")
    public APIResponseDTO getTeam() {
        return new APIResponseDTO(200,"Success!",teamService.findAll());
    }

    @RequestMapping( value = "/type/employee",method = RequestMethod.GET )
    @PreAuthorize("hasAuthority('HR') or hasAuthority('PO')")
    public APIResponseDTO getEmployeeType() {
        return new APIResponseDTO(200,"Success!",employeeTypeService.findAll());
    }

    @RequestMapping( value = "/type/absence",method = RequestMethod.GET )
    public APIResponseDTO getAbsenceType() {
        return new APIResponseDTO(200,"Success!",absenceTypeService.findAll());
    }

    @RequestMapping( value = "/type/day",method = RequestMethod.GET )
    public APIResponseDTO getOvertimeTypes() {
        return new APIResponseDTO(200,"Success!",dateTypesService.findAll());
    }

    @RequestMapping( value = "/overtime/statuses",method = RequestMethod.GET )
    public APIResponseDTO getOvertimeStatuses() {
        return new APIResponseDTO(200,"Success!",overtimeStatusesService.findAll());
    }

}
