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
    private int idProcesses;

    @Column(name = "employee_id")
    private int employeeId;

    @Column(name = "project_id")
    private String projectId;

    @Column(name = "role_id")
    private int roleId;

    @Column(name = "check_project_exit")
    private Integer checkProjectExit;

    @Column(name = "man_power")
    private Double man_power = 0.0;

    @Column(name = "start_date")
    private String startDate;

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
    @JoinColumn(name = "role_id", insertable = false, updatable = false)
    private Role role;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="project_id", insertable = false, updatable = false)
    private Project project;

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

    public Double getMan_power() {
        return man_power;
    }

    public void setMan_power(Double man_power) {
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
