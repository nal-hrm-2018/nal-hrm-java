package nals.hrm.api_nals_hrm.dto;


import nals.hrm.api_nals_hrm.entities.DayTypes;
import nals.hrm.api_nals_hrm.entities.OvertimeStatuses;


public class OvertimeDTO {

  private int id;

  private String reason;

  private String date;

  private String startTime;

  private String endTime;

  private float totalTime;

  private Float correctTotalTime;

  private String reasonReject;

  private String updatedAt;

  private String createdAt;

  private DayTypes dayTypes;

  private OvertimeStatuses overtimeStatuses;

  private int employeeId;

  private String nameEmployee;

  private String idProject;

  private String nameProject;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  public float getTotalTime() {
    return totalTime;
  }

  public void setTotalTime(float totalTime) {
    this.totalTime = totalTime;
  }

  public Float getCorrectTotalTime() {
    return correctTotalTime;
  }

  public void setCorrectTotalTime(Float correctTotalTime) {
    this.correctTotalTime = correctTotalTime;
  }

  public String getReasonReject() {
    return reasonReject;
  }

  public void setReasonReject(String reasonReject) {
    this.reasonReject = reasonReject;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public DayTypes getDayTypes() {
    return dayTypes;
  }

  public void setDayTypes(DayTypes dayTypes) {
    this.dayTypes = dayTypes;
  }

  public OvertimeStatuses getOvertimeStatuses() {
    return overtimeStatuses;
  }

  public void setOvertimeStatuses(OvertimeStatuses overtimeStatuses) {
    this.overtimeStatuses = overtimeStatuses;
  }

  public int getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(int employeeId) {
    this.employeeId = employeeId;
  }

  public String getNameEmployee() {
    return nameEmployee;
  }

  public void setNameEmployee(String nameEmployee) {
    this.nameEmployee = nameEmployee;
  }

  public String getIdProject() {
    return idProject;
  }

  public void setIdProject(String idProject) {
    this.idProject = idProject;
  }

  public String getNameProject() {
    return nameProject;
  }

  public void setNameProject(String nameProject) {
    this.nameProject = nameProject;
  }
}
