package nals.hrm.api_nals_hrm.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "employee_types")
public class EmployeeType implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue
  @Column(name = "id", nullable = false)
  private int idEmployeeType;

  @Column(name = "name", nullable = false)
  private String nameEmployeeType;

  @JsonIgnore
  @Column(name = "description")
  private String descriptionEmployeeType;

  @JsonIgnore
  @Column(name = "updated_at")
  private String updatedAtEmployeeType;

  @JsonIgnore
  @Column(name = "created_at")
  private String createdAtEmployeeType;

  @JsonIgnore
  @Column(name = "delete_flag")
  private int deleteFlagEmployeeType;


  public EmployeeType() {
  }

  public EmployeeType(int idEmployeeType, String nameEmployeeType, String descriptionEmployeeType, String updatedAtEmployeeType, String createdAtEmployeeType, int deleteFlagEmployeeType) {
    this.idEmployeeType = idEmployeeType;
    this.nameEmployeeType = nameEmployeeType;
    this.descriptionEmployeeType = descriptionEmployeeType;
    this.updatedAtEmployeeType = updatedAtEmployeeType;
    this.createdAtEmployeeType = createdAtEmployeeType;
    this.deleteFlagEmployeeType = deleteFlagEmployeeType;
  }

  public int getIdEmployeeType() {
    return idEmployeeType;
  }

  public void setIdEmployeeType(int idEmployeeType) {
    this.idEmployeeType = idEmployeeType;
  }

  public String getNameEmployeeType() {
    return nameEmployeeType;
  }

  public void setNameEmployeeType(String nameEmployeeType) {
    this.nameEmployeeType = nameEmployeeType;
  }

  public String getDescriptionEmployeeType() {
    return descriptionEmployeeType;
  }

  public void setDescriptionEmployeeType(String descriptionEmployeeType) {
    this.descriptionEmployeeType = descriptionEmployeeType;
  }

  public String getUpdatedAtEmployeeType() {
    return updatedAtEmployeeType;
  }

  public void setUpdatedAtEmployeeType(String updatedAtEmployeeType) {
    this.updatedAtEmployeeType = updatedAtEmployeeType;
  }

  public String getCreatedAtEmployeeType() {
    return createdAtEmployeeType;
  }

  public void setCreatedAtEmployeeType(String createdAtEmployeeType) {
    this.createdAtEmployeeType = createdAtEmployeeType;
  }

  public int getDeleteFlagEmployeeType() {
    return deleteFlagEmployeeType;
  }

  public void setDeleteFlagEmployeeType(int deleteFlagEmployeeType) {
    this.deleteFlagEmployeeType = deleteFlagEmployeeType;
  }

  @Override
  public String toString() {
    return "EmployeeType{" +
            "idEmployeeType=" + idEmployeeType +
            ", nameEmployeeType='" + nameEmployeeType + '\'' +
            ", descriptionEmployeeType='" + descriptionEmployeeType + '\'' +
            ", updatedAtEmployeeType='" + updatedAtEmployeeType + '\'' +
            ", createdAtEmployeeType='" + createdAtEmployeeType + '\'' +
            ", deleteFlagEmployeeType=" + deleteFlagEmployeeType +
            '}';
  }


}
