package nals.hrm.api_nals_hrm.service;


import nals.hrm.api_nals_hrm.dto.ListDTO;
import nals.hrm.api_nals_hrm.entities.Notification;
import nals.hrm.api_nals_hrm.exception.CustomException;
import nals.hrm.api_nals_hrm.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class NotificationService {

  @Autowired
  NotificationRepository notificationRepository;


  public List<Notification> listNotification() {
    return notificationRepository.findNotification();
  }

  public String addNotification(Notification notification) {
    notification.setNotificationTypeId(1);
    Date now = new Date();
    String strNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(now);
    notification.setCreatedAt(strNow);
    notification.setUpdateAt(strNow);
    notificationRepository.save(notification);
    return "add success!";
  }

  public String editNotification(int id, Notification notification) {
    Notification notificationOld = notificationRepository.findByIdNotificationAndDeleteFlag(id, 0);
    if(notificationOld == null){
      throw new CustomException("notification not found!", 404);
    }

    notificationOld.setTitle(notification.getTitle());
    notificationOld.setContent(notification.getContent());
    notificationOld.setEndDate(notification.getEndDate());
    Date now = new Date();
    String strNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(now);

    notificationOld.setUpdateAt(strNow);

    notificationRepository.save(notificationOld);
    return "edit success!";
  }

  public String deleteNotification(int id) {
    Notification notification = notificationRepository.findByIdNotificationAndDeleteFlag(id, 0);
    if(notification == null){
      throw new CustomException("notification not found!", 404);
    }
    notification.setDeleteFlag(1);
    notificationRepository.save(notification);
    return "delete success!";
  }

}
