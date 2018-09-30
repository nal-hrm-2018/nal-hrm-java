package nals.hrm.api_nals_hrm.controller;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import nals.hrm.api_nals_hrm.dto.APIResponseDTO;
import nals.hrm.api_nals_hrm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api")
public class ListEmployeesController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping( value = "/manage/employee/list",params = { "page", "pageSize" }, method = RequestMethod.GET )
    @PreAuthorize("hasAuthority('BO') or hasAuthority('PO') and hasAuthority('view_list_employee')")
    public APIResponseDTO getListEmployees( @RequestParam("page")  Optional<Integer> page, @RequestParam("pageSize")  Optional<Integer> pageSize) {
        return new APIResponseDTO(200,"Success!",employeeService.getListEmployees(page,pageSize));
    }

}

