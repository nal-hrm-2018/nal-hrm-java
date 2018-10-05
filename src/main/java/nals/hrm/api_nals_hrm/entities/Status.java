package nals.hrm.api_nals_hrm.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "statuses")
public class Status implements Serializable {

  @Id
  @GeneratedValue
  @Column(name = "id", nullable = false)
  int idStatus;

  @Column(name = "name")
  String nameStatus;

  public Status() {
  }

  public Status(int idStatus, String nameStatus) {
    this.idStatus = idStatus;
    this.nameStatus = nameStatus;
  }

  public int getIdStatus() {
    return idStatus;
  }

  public void setIdStatus(int idStatus) {
    this.idStatus = idStatus;
  }

  public String getNameStatus() {
    return nameStatus;
  }

  public void setNameStatus(String nameStatus) {
    this.nameStatus = nameStatus;
  }

  @Override
  public String toString() {
    return "Status{" +
            "idStatus=" + idStatus +
            ", nameStatus='" + nameStatus + '\'' +
            '}';
  }
}
