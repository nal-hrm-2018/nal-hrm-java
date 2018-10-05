package nals.hrm.api_nals_hrm.repository;

import nals.hrm.api_nals_hrm.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {

  @Query(value = "SELECT * FROM notification\n" +
          "WHERE delete_flag = 0 AND (end_date IS NULL OR DATE(end_date) >= DATE(NOW()))\n" +
          "ORDER BY updated_at DESC", nativeQuery = true)
  List<Notification> findNotification();
}
