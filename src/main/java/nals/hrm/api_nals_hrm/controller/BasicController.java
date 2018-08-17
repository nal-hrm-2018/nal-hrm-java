package nals.hrm.api_nals_hrm.controller;

import nals.hrm.api_nals_hrm.dto.APIResponseDTO;
import nals.hrm.api_nals_hrm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BasicController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping( value = "/basic",params = { "id"}, method = RequestMethod.GET )
    @PreAuthorize("hasAuthority('view_employee_basic')")
    public APIResponseDTO getBasic(@RequestParam("id")  int id) {
        return new APIResponseDTO(200,"Success!",employeeService.findByIdEmployeeAndIsEmployeeAndDeleteFlag(id,1,0));
    }
}