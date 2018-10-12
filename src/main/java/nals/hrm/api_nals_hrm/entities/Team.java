package nals.hrm.api_nals_hrm.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "teams")
public class Team implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue
  @Column(name = "id", nullable = false)
  int idTeam;

  @Column(name = "name", nullable = false)
  String nameTeam;

  @JsonIgnore
  @Column(name = "description")
  String descriptionTeam;

  @JsonIgnore
  @Column(name = "updated_at")
  String updatedAtTeam;

  @JsonIgnore
  @Column(name = "created_at")
  String createdAtTeam;

  @JsonIgnore
  @Column(name = "delete_flag")
  int deleteFlagTeam;

  public Team() {
  }

  public Team(String nameTeam, String descriptionTeam, String updatedAtTeam, String createdAtTeam, int deleteFlagTeam) {
    this.nameTeam = nameTeam;
    this.descriptionTeam = descriptionTeam;
    this.updatedAtTeam = updatedAtTeam;
    this.createdAtTeam = createdAtTeam;
    this.deleteFlagTeam = deleteFlagTeam;
  }

  public int getIdTeam() {
    return idTeam;
  }

  public void setIdTeam(int idTeam) {
    this.idTeam = idTeam;
  }

  public String getNameTeam() {
    return nameTeam;
  }

  public void setNameTeam(String nameTeam) {
    this.nameTeam = nameTeam;
  }

  public String getDescriptionTeam() {
    return descriptionTeam;
  }

  public void setDescriptionTeam(String descriptionTeam) {
    this.descriptionTeam = descriptionTeam;
  }

  public String getUpdatedAtTeam() {
    return updatedAtTeam;
  }

  public void setUpdatedAtTeam(String updatedAtTeam) {
    this.updatedAtTeam = updatedAtTeam;
  }

  public String getCreatedAtTeam() {
    return createdAtTeam;
  }

  public void setCreatedAtTeam(String createdAtTeam) {
    this.createdAtTeam = createdAtTeam;
  }

  public int getDeleteFlagTeam() {
    return deleteFlagTeam;
  }

  public void setDeleteFlagTeam(int deleteFlagTeam) {
    this.deleteFlagTeam = deleteFlagTeam;
  }


}
