package nals.hrm.api_nals_hrm.controller;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import nals.hrm.api_nals_hrm.dto.APIResponseDTO;
import nals.hrm.api_nals_hrm.respository.RoleRepository;
import nals.hrm.api_nals_hrm.service.EmployeeService;
import nals.hrm.api_nals_hrm.service.EmployeeTypeService;
import nals.hrm.api_nals_hrm.service.PermissionService;
import nals.hrm.api_nals_hrm.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class BasicController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping( value = "/basic",params = { "id"}, method = RequestMethod.GET )
    public APIResponseDTO getBasic(@RequestParam("id")  int id) {
        return new APIResponseDTO(200,"Success!",employeeService.findByIdEmployeeAndIsEmployeeAndDeleteFlag(id,1,0));
    }
}
