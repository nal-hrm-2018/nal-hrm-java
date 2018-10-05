package nals.hrm.api_nals_hrm.controller;

import nals.hrm.api_nals_hrm.dto.APIResponseDTO;
import nals.hrm.api_nals_hrm.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ListNotificationController {

  @Autowired
  NotificationService notificationService;

  @RequestMapping(value = "/dashboard/notification", method = RequestMethod.GET)
  public APIResponseDTO getListNotification() {
    return new APIResponseDTO(200, "Success!", notificationService.listNotification());
  }

}
