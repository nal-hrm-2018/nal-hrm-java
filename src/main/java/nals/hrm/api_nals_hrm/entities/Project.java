package nals.hrm.api_nals_hrm.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "projects")
public class Project implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    String idProject;

    @Column(name = "name")
    String nameProject;

    @Column(name = "income")
    double income;

    @Column(name = "real_cost")
    double realCost;

    @Column(name = "description")
    String description;

    @JsonIgnore
    @Column(name = "status_id")
    int statusId;

    @Column(name = "estimate_start_date")
    String estimateStartDate;

    @Column(name = "start_date")
    String startDate;

    @Column(name = "estimate_end_date")
    String estimateEndDate;

    @Column(name = "end_date")
    String endDate;

    @JsonIgnore
    @Column(name = "updated_at")
    String updatedAt;

    @JsonIgnore
    @Column(name = "updated_by_employee")
    Integer updatedByEmployee;

    @JsonIgnore
    @Column(name = "created_at")
    String createdAt;

    @JsonIgnore
    @Column(name = "created_by_employee")
    Integer createdByEmployee;

    @JsonIgnore
    @Column(name = "delete_flag")
    int deleteFlag;



    @ManyToOne
    @JoinColumn(name="status_id",insertable=false, updatable=false)
    Status status;

    public Project() {
    }

    public Project(String idProject, String nameProject, double income, double realCost,
                   String description, int statusId, String estimateStartDate, String startDate,
                   String estimateEndDate, String endDate, String updatedAt, Integer updatedByEmployee,
                   String createdAt, Integer createdByEmployee, int deleteFlag, Status status) {
        this.idProject = idProject;
        this.nameProject = nameProject;
        this.income = income;
        this.realCost = realCost;
        this.description = description;
        this.statusId = statusId;
        this.estimateStartDate = estimateStartDate;
        this.startDate = startDate;
        this.estimateEndDate = estimateEndDate;
        this.endDate = endDate;
        this.updatedAt = updatedAt;
        this.updatedByEmployee = updatedByEmployee;
        this.createdAt = createdAt;
        this.createdByEmployee = createdByEmployee;
        this.deleteFlag = deleteFlag;
        this.status = status;
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

