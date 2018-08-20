package nals.hrm.api_nals_hrm.controller;

import nals.hrm.api_nals_hrm.dto.*;
import nals.hrm.api_nals_hrm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api")
public class ProfileController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping(value = "/profile")
//    @PreAuthorize("hasAuthority('view_profile')")
    public APIResponseDTO getProfile(HttpServletRequest req) {
        return new APIResponseDTO(200,"Success!",employeeService.getProfile(req));
    }

}

