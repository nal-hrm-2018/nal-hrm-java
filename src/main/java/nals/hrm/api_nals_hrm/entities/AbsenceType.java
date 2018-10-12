package nals.hrm.api_nals_hrm.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "absence_types")
public class AbsenceType implements Serializable {

  @Id
  @GeneratedValue
  @Column(name = "id", nullable = false)
  private int idAbsenceType;

  @Column(name = "name")
  private String nameAbsenceType;

  @Column(name = "description")
  private String description;

  public AbsenceType() {
  }

  public AbsenceType(int idAbsenceType, String nameAbsenceType, String description) {
    this.idAbsenceType = idAbsenceType;
    this.nameAbsenceType = nameAbsenceType;
    this.description = description;
  }

  public int getIdAbsenceType() {
    return idAbsenceType;
  }

  public void setIdAbsenceType(int idAbsenceType) {
    this.idAbsenceType = idAbsenceType;
  }

  public String getNameAbsenceType() {
    return nameAbsenceType;
  }

  public void setNameAbsenceType(String nameAbsenceType) {
    this.nameAbsenceType = nameAbsenceType;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
