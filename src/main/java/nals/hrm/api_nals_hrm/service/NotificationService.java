package nals.hrm.api_nals_hrm.service;


import nals.hrm.api_nals_hrm.entities.Notification;
import nals.hrm.api_nals_hrm.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NotificationService {

  @Autowired
  NotificationRepository notificationRepository;


  public List<Notification> listNotification() {
    return notificationRepository.findNotification();
  }
}
