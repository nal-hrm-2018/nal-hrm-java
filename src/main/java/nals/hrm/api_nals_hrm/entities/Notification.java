package nals.hrm.api_nals_hrm.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "notification")
public class Notification implements Serializable {

    @Id
    @SequenceGenerator(name="seq",sequenceName="oracle_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private int idNotification;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @JsonIgnore
    @Column(name = "notification_type_id")
    private int notificationTypeId;

    @Column(name = "end_date")
    private String endDate;

//    @JsonIgnore
    @Column(name = "create_at")
    private String createdAt;

//    @JsonIgnore
    @Column(name = "update_at")
    private String updateAt;

    @JsonIgnore
    @Column(name = "delete_flag")
    private int deleteFlag;

    @ManyToOne
    @JoinColumn(name = "notification_type_id", insertable = false, updatable = false)
    private NotificationTypes notificationTypes;



    public int getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(int idNotification) {
        this.idNotification = idNotification;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getNotificationTypeId() {
        return notificationTypeId;
    }

    public void setNotificationTypeId(int notificationTypeId) {
        this.notificationTypeId = notificationTypeId;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public NotificationTypes getNotificationTypes() {
        return notificationTypes;
    }

    public void setNotificationTypes(NotificationTypes notificationTypes) {
        this.notificationTypes = notificationTypes;
    }
}
