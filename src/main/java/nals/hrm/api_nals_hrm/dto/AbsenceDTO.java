package nals.hrm.api_nals_hrm.dto;


import nals.hrm.api_nals_hrm.entities.AbsenceTime;
import nals.hrm.api_nals_hrm.entities.AbsenceType;

public class AbsenceDTO {
  private int idAbsences;
  private int idEmployee;
  private String nameEmployee;
  private String idProject;
  private String nameProject;
  private String fromDate;
  private String toDate;
  private String reason;
  private String description;
  private String createdAt;
  private AbsenceType absenceType;
  private AbsenceTime absenceTime;
  private double numberDayAbsence;

  public AbsenceDTO() {
  }

  public int getIdEmployee() {
    return idEmployee;
  }

  public void setIdEmployee(int idEmployee) {
    this.idEmployee = idEmployee;
  }

  public String getNameEmployee() {
    return nameEmployee;
  }

  public void setNameEmployee(String nameEmployee) {
    this.nameEmployee = nameEmployee;
  }

  public String getNameProject() {
    return nameProject;
  }

  public void setNameProject(String nameProject) {
    this.nameProject = nameProject;
  }

  public int getIdAbsences() {
    return idAbsences;
  }

  public void setIdAbsences(int idAbsences) {
    this.idAbsences = idAbsences;
  }

  public String getFromDate() {
    return fromDate;
  }

  public void setFromDate(String fromDate) {
    this.fromDate = fromDate;
  }

  public String getToDate() {
    return toDate;
  }

  public void setToDate(String toDate) {
    this.toDate = toDate;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public AbsenceType getAbsenceType() {
    return absenceType;
  }

  public void setAbsenceType(AbsenceType absenceType) {
    this.absenceType = absenceType;
  }

  public AbsenceTime getAbsenceTime() {
    return absenceTime;
  }

  public void setAbsenceTime(AbsenceTime absenceTime) {
    this.absenceTime = absenceTime;
  }

  public String getIdProject() {
    return idProject;
  }

  public void setIdProject(String idProject) {
    this.idProject = idProject;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public double getNumberDayAbsence() {
    return numberDayAbsence;
  }

  public void setNumberDayAbsence(double numberDayAbsence) {
    this.numberDayAbsence = numberDayAbsence;
  }
}
