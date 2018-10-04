package nals.hrm.api_nals_hrm.controller;

import nals.hrm.api_nals_hrm.dto.APIResponseDTO;
import nals.hrm.api_nals_hrm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BasicEmployeeController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping( value = "/manage/employee/basic",params = { "id"}, method = RequestMethod.GET )
//    @PreAuthorize("hasAuthority('BO') or hasAuthority('PO') or hasAuthority('SM/AL') or hasAuthority('view_employee_basic')")
    public APIResponseDTO viewBasicEmployee(@RequestParam("id")  int id) {
        return new APIResponseDTO(200,"Success!",employeeService.viewBasicEmployee(id));
    }
}
