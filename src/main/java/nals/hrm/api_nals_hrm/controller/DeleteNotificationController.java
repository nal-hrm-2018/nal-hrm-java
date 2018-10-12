package nals.hrm.api_nals_hrm.controller;


import nals.hrm.api_nals_hrm.dto.APIResponseDTO;
import nals.hrm.api_nals_hrm.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DeleteNotificationController {
  @Autowired
  NotificationService notificationService;

  @RequestMapping(value = "/manage/notification/delete/{id}", method = RequestMethod.DELETE)
  @PreAuthorize("hasAuthority('BO')")
  public APIResponseDTO deleteNotification(@PathVariable("id") int id) {
    return new APIResponseDTO(200, "Success!", notificationService.deleteNotification(id));
  }
}
