package nals.hrm.api_nals_hrm.controller;


import io.swagger.annotations.ApiParam;
import nals.hrm.api_nals_hrm.dto.APIResponseDTO;
import nals.hrm.api_nals_hrm.entities.Notification;
import nals.hrm.api_nals_hrm.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class EditNotificationController {

  @Autowired
  NotificationService notificationService;

  @RequestMapping(value = "/manage/notification/edit/{id}", method = RequestMethod.PUT)
  @PreAuthorize("hasAuthority('BO')")
  public APIResponseDTO addNotification(@PathVariable("id") int id, @ApiParam @RequestBody Notification notification) {
    return new APIResponseDTO(200, "Success!", notificationService.editNotification(id, notification));
  }
}
