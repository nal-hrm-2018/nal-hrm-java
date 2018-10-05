package nals.hrm.api_nals_hrm.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "contractual_history")
public class ContractualHistory {
  @Id
  @SequenceGenerator(name = "seq", sequenceName = "oracle_seq")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "id")
  private int id;

  @Column(name = "employee_id")
  private int employeeId;

  @Column(name = "contractual_type_id")
  private int contractualTypeId;

  @Column(name = "start_date")
  private String startDate;

  @Column(name = "end_date")
  private String endDate;

  @Column(name = "created_at")
  private String createdAt;

  @Column(name = "updated_at")
  private String updatedAt;

  @JsonIgnore
  @Column(name = "delete_flag")
  private int deleteFlag;

  @ManyToOne
  @JoinColumn(name = "contractual_type_id", insertable = false, updatable = false)
  private ContractualTypes contractualTypes;

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

  public int getContractualTypeId() {
    return contractualTypeId;
  }

  public void setContractualTypeId(int contractualTypeId) {
    this.contractualTypeId = contractualTypeId;
  }

  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
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

  public String getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }

  public int getDeleteFlag() {
    return deleteFlag;
  }

  public void setDeleteFlag(int deleteFlag) {
    this.deleteFlag = deleteFlag;
  }

  public ContractualTypes getContractualTypes() {
    return contractualTypes;
  }

  public void setContractualTypes(ContractualTypes contractualTypes) {
    this.contractualTypes = contractualTypes;
  }
}
