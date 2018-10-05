package nals.hrm.api_nals_hrm.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "notification_types")
public class NotificationTypes {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "id")
  private int id;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @JsonIgnore
  @Column(name = "delete_flag")
  private int deleteFlag;


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getDeleteFlag() {
    return deleteFlag;
  }

  public void setDeleteFlag(int deleteFlag) {
    this.deleteFlag = deleteFlag;
  }
}
