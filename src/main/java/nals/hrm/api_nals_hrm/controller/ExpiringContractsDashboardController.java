package nals.hrm.api_nals_hrm.controller;

import io.swagger.annotations.ApiParam;
import nals.hrm.api_nals_hrm.dto.APIResponseDTO;
import nals.hrm.api_nals_hrm.entities.Absence;
import nals.hrm.api_nals_hrm.service.AbsenceService;
import nals.hrm.api_nals_hrm.service.ContractualHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class ExpiringContractsDashboardController {

    @Autowired
    ContractualHistoryService contractualHistoryService;

    @RequestMapping( value = "/dashboard/expiring-contracts", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('BO')")
    public APIResponseDTO ExpiringContracts() {
        return new APIResponseDTO(200,"Success!",contractualHistoryService.expiringContracts());
    }
}
