package nals.hrm.api_nals_hrm.controller;


import io.swagger.annotations.ApiParam;
import nals.hrm.api_nals_hrm.dto.APIResponseDTO;
import nals.hrm.api_nals_hrm.entities.Notification;
import nals.hrm.api_nals_hrm.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AddNotificationController {

  @Autowired
  NotificationService notificationService;

  @RequestMapping(value = "/manage/notification/add", method = RequestMethod.POST)
  @PreAuthorize("hasAuthority('BO')")
  public APIResponseDTO addNotification(@ApiParam @RequestBody Notification notification) {
    return new APIResponseDTO(200, "Success!", notificationService.addNotification(notification));
  }
}
