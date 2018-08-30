package nals.hrm.api_nals_hrm.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "projects")
public class Project implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    private String idProject;

    @Column(name = "name")
    private String nameProject;

    @Column(name = "income")
    private double income;

    @Column(name = "real_cost")
    private double realCost;

    @Column(name = "description")
    private String description;

    @JsonIgnore
    @Column(name = "status_id")
    private int statusId;

    @Column(name = "estimate_start_date")
    private String estimateStartDate;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "estimate_end_date")
    private String estimateEndDate;

    @Column(name = "end_date")
    private String endDate;

    @JsonIgnore
    @Column(name = "updated_at")
    private String updatedAt;

    @JsonIgnore
    @Column(name = "updated_by_employee")
    private Integer updatedByEmployee;

    @JsonIgnore
    @Column(name = "created_at")
    private String createdAt;

    @JsonIgnore
    @Column(name = "created_by_employee")
    private Integer createdByEmployee;

    @JsonIgnore
    @Column(name = "delete_flag")
    private int deleteFlag;

    @ManyToOne
    @JoinColumn(name="status_id",insertable=false, updatable=false)
    private Status status;

    @JsonIgnore
    @ManyToMany(mappedBy = "projects", fetch = FetchType.LAZY)
    private List<Employee> employeeList;

    public Project() {
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

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getRealCost() {
        return realCost;
    }

    public void setRealCost(double realCost) {
        this.realCost = realCost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getEstimateStartDate() {
        return estimateStartDate;
    }

    public void setEstimateStartDate(String estimateStartDate) {
        this.estimateStartDate = estimateStartDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEstimateEndDate() {
        return estimateEndDate;
    }

    public void setEstimateEndDate(String estimateEndDate) {
        this.estimateEndDate = estimateEndDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @Override
    public String toString() {
        return "Project{" +
                "idProject='" + idProject + '\'' +
                ", nameProject='" + nameProject + '\'' +
                ", income=" + income +
                ", realCost=" + realCost +
                ", description='" + description + '\'' +
                ", statusId=" + statusId +
                ", estimateStartDate='" + estimateStartDate + '\'' +
                ", startDate='" + startDate + '\'' +
                ", estimateEndDate='" + estimateEndDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", updatedByEmployee=" + updatedByEmployee +
                ", createdAt='" + createdAt + '\'' +
                ", createdByEmployee=" + createdByEmployee +
                ", deleteFlag=" + deleteFlag +
                ", status=" + status +
                '}';
    }
}

