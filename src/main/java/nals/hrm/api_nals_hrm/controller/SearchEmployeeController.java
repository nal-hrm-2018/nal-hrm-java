package nals.hrm.api_nals_hrm.controller;

import nals.hrm.api_nals_hrm.dto.APIResponseDTO;
import nals.hrm.api_nals_hrm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class SearchEmployeeController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping( value = "/manage/employee/search",params = { "keyword","page", "pageSize"}, method = RequestMethod.GET )
    @PreAuthorize("hasAuthority('search_employee')")
    public APIResponseDTO getBasic(@RequestParam("keyword")  String keyword, @RequestParam("page") Optional<Integer> page, @RequestParam("pageSize")  Optional<Integer> pageSize) {
        return new APIResponseDTO(200,"Success!",employeeService.findByEmailContainingOrNameEmployeeContainingAndIsEmployeeAndDeleteFlag(keyword,keyword,1,1,page,pageSize));
    }

}
