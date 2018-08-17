package nals.hrm.api_nals_hrm.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "processes")
public class Processes implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    int idProcesses;

    @Column(name = "employee_id")
    int employeeId;

    @Column(name = "project_id")
    String projectId;

    @Column(name = "role_id")
    int roleId;

    @Column(name = "check_project_exit")
    Integer checkProjectExit;

    @Column(name = "man_power")
    double man_power;

    @Column(name = "start_date")
    String startDate;

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



    public Processes() {
    }

    public Processes(int idProcesses, int employeeId, String projectId, int roleId, Integer checkProjectExit,
                     double man_power, String startDate, String endDate, String updatedAt,
                     int updatedByEmployee, String createdAt,
                     int createdByEmployee, int deleteFlag) {
        this.idProcesses = idProcesses;
        this.employeeId = employeeId;
        this.projectId = projectId;
        this.roleId = roleId;
        this.checkProjectExit = checkProjectExit;
        this.man_power = man_power;
        this.startDate = startDate;
        this.endDate = endDate;
        this.updatedAt = updatedAt;
        this.updatedByEmployee = updatedByEmployee;
        this.createdAt = createdAt;
        this.createdByEmployee = createdByEmployee;
        this.deleteFlag = deleteFlag;
    }

    public int getIdProcesses() {
        return idProcesses;
    }

    public void setIdProcesses(int idProcesses) {
        this.idProcesses = idProcesses;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public Integer getCheckProjectExit() {
        return checkProjectExit;
    }

    public void setCheckProjectExit(Integer checkProjectExit) {
        this.checkProjectExit = checkProjectExit;
    }

    public double getMan_power() {
        return man_power;
    }

    public void setMan_power(double man_power) {
        this.man_power = man_power;
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


}