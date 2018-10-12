package nals.hrm.api_nals_hrm.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "day_types")
public class DayTypes implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "id")
  private int idDayType;

  @Column(name = "name")
  private String nameDayType;

  @JsonIgnore
  @Column(name = "delete_flag")
  private int deleteFlag;

  public int getIdDayType() {
    return idDayType;
  }

  public void setIdDayType(int idDayType) {
    this.idDayType = idDayType;
  }

  public String getNameDayType() {
    return nameDayType;
  }

  public void setNameDayType(String nameDayType) {
    this.nameDayType = nameDayType;
  }

  public int getDeleteFlag() {
    return deleteFlag;
  }

  public void setDeleteFlag(int deleteFlag) {
    this.deleteFlag = deleteFlag;
  }
}
