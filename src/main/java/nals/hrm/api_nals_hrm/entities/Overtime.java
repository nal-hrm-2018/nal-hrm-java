package nals.hrm.api_nals_hrm.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "overtime")
public class Overtime implements Serializable {

  @Id
  @SequenceGenerator(name = "seq", sequenceName = "oracle_seq")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
  @Column(name = "id", nullable = false)
  private int id;

  @Column(name = "employee_id")
  private int employeeId;

  @Column(name = "process_id")
  private Integer processId;

  @Column(name = "reason")
  private String reason;

  @Column(name = "date")
  private String date;

  @Column(name = "start_time")
  private String startTime;

  @Column(name = "end_time")
  private String endTime;

  @Column(name = "day_type_id")
  private int dayTypeId;

  @Column(name = "overtime_status_id")
  private int overtimeStatusId;

  @Column(name = "total_time")
  private float totalTime;

  @Column(name = "correct_total_time")
  private Float correctTotalTime;

  @Column(name = "reason_reject")
  private String reasonReject;

  //    @JsonIgnore
  @Column(name = "updated_at")
  private String updatedAt;

  @JsonIgnore
  @Column(name = "updated_by_employee")
  private Integer updatedByEmployee;

  //    @JsonIgnore
  @Column(name = "created_at")
  private String createdAt;

  @JsonIgnore
  @Column(name = "created_by_employee")
  private Integer createdByEmployee;

  @JsonIgnore
  @Column(name = "delete_flag")
  private int deleteFlag;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "day_type_id", insertable = false, updatable = false)
  private DayTypes dateTypes;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "overtime_status_id", insertable = false, updatable = false)
  private OvertimeStatuses overtimeStatuses;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "employee_id", insertable = false, updatable = false)
  private Employee employee;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "process_id", insertable = false, updatable = false)
  private Processes processes;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(int employeeId) {
    this.employeeId = employeeId;
  }

  public Integer getProcessId() {
    return processId;
  }

  public void setProcessId(Integer processId) {
    this.processId = processId;
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

  public int getDayTypeId() {
    return dayTypeId;
  }

  public void setDayTypeId(int dayTypeId) {
    this.dayTypeId = dayTypeId;
  }

  public int getOvertimeStatusId() {
    return overtimeStatusId;
  }

  public void setOvertimeStatusId(int overtimeStatusId) {
    this.overtimeStatusId = overtimeStatusId;
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

  public Integer getUpdatedByEmployee() {
    return updatedByEmployee;
  }

  public void setUpdatedByEmployee(Integer updatedByEmployee) {
    this.updatedByEmployee = updatedByEmployee;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public Integer getCreatedByEmployee() {
    return createdByEmployee;
  }

  public void setCreatedByEmployee(Integer createdByEmployee) {
    this.createdByEmployee = createdByEmployee;
  }

  public int getDeleteFlag() {
    return deleteFlag;
  }

  public void setDeleteFlag(int deleteFlag) {
    this.deleteFlag = deleteFlag;
  }

  public DayTypes getDateTypes() {
    return dateTypes;
  }

  public void setDateTypes(DayTypes dateTypes) {
    this.dateTypes = dateTypes;
  }

  public OvertimeStatuses getOvertimeStatuses() {
    return overtimeStatuses;
  }

  public void setOvertimeStatuses(OvertimeStatuses overtimeStatuses) {
    this.overtimeStatuses = overtimeStatuses;
  }

  public Employee getEmployee() {
    return employee;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }

  public Processes getProcesses() {
    return processes;
  }

  public void setProcesses(Processes processes) {
    this.processes = processes;
  }
}
