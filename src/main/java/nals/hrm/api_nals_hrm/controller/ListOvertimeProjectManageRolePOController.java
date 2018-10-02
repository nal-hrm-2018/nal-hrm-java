package nals.hrm.api_nals_hrm.controller;

import nals.hrm.api_nals_hrm.dto.APIResponseDTO;
import nals.hrm.api_nals_hrm.service.OvertimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ListOvertimeProjectManageRolePOController {

    @Autowired
    OvertimeService overtimeService;

    @RequestMapping( value = "/manage/overtime/po/list",params = {"id","page","pageSize"}, method = RequestMethod.GET )
//    @PreAuthorize("hasAuthority('HR')")
    public APIResponseDTO getListOvertimeProjectManageRolePO(@RequestParam("id")  String id, HttpServletRequest req, @RequestParam("page") Optional<Integer> page, @RequestParam("pageSize")  Optional<Integer> pageSize) {
        return new APIResponseDTO(200,"Success!", overtimeService.getListOvertimeProjectManageRolePO(id, req, page, pageSize));
    }
}
