package nals.hrm.api_nals_hrm.controller;

import nals.hrm.api_nals_hrm.dto.APIResponseDTO;
import nals.hrm.api_nals_hrm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping("/api")
public class ListEmployeesController {

  @Autowired
  EmployeeService employeeService;

  @RequestMapping(value = "/manage/employee/list", params = {"page", "pageSize"}, method = RequestMethod.GET)
//    @PreAuthorize("hasAuthority('BO') or hasAuthority('PO') and hasAuthority('view_list_employee')")
  public APIResponseDTO getListEmployees(@RequestParam("page") Optional<Integer> page, @RequestParam("pageSize") Optional<Integer> pageSize) {
    return new APIResponseDTO(200, "Success!", employeeService.getListEmployees(page, pageSize));
  }

}

