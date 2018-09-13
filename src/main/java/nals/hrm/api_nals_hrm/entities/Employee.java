package nals.hrm.api_nals_hrm.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "employees")
public class Employee implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private int idEmployee;

    @Column(name = "email")
    private String email;

    @JsonIgnore
    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String nameEmployee;

    @Column(name = "birthday")
    private String birthday;

    @JsonIgnore
    @Column(name = "gender")
    private Integer gender;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "address")
    private String address;

    @JsonIgnore
    @Column(name = "marital_status")
    private int maritalStatus;

    @Column(name = "startwork_date")
    private String startWorkDate;

    @Column(name = "endwork_date")
    private String endWorkDate;

    @Column(name = "curriculum_vitae")
    private String curriculum_vitae;

    @JsonIgnore
    @Column(name = "is_employee")
    private int isEmployee;

    @Column(name = "company")
    private String company;

    @Column(name = "avatar")
    private String avatar;

    @JsonIgnore
    @Column(name = "employee_type_id")
    private Integer employeeTypeId;

    @JsonIgnore
    @Column(name = "role_id")
    private int idRole;

    @JsonIgnore
    @Column(name = "is_manager")
    private int isManager;

    @JsonIgnore
    @Column(name = "salary_id")
    private Integer salaryId;

    @JsonIgnore
    @Column(name = "work_status")
    private boolean workStatus;

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

    @JsonIgnore
    @Column(name = "remaining_absence_days")
    private int remainingAbsenceDays;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "permission_employee",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private List<Permission> permissions;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_type_id", insertable = false, updatable = false)
    private EmployeeType employeeType;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "employee_team",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id"))
    private List<Team> teams;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "processes",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    private List<Project> projects;

    @JsonIgnore
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", insertable = false, updatable = false)
    private Role role;

    public Employee() {
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        this.password = password.replace("$2y$", "$2a$");
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public String getNameEmployee() {
        return nameEmployee;
    }

    public void setNameEmployee(String nameEmployee) {
        this.nameEmployee = nameEmployee;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(int maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getStartWorkDate() {
        return startWorkDate;
    }

    public void setStartWorkDate(String startWorkDate) {
        this.startWorkDate = startWorkDate;
    }

    public String getEndWorkDate() {
        return endWorkDate;
    }

    public void setEndWorkDate(String endWorkDate) {
        this.endWorkDate = endWorkDate;
    }

    public String getCurriculum_vitae() {
        return curriculum_vitae;
    }

    public void setCurriculum_vitae(String curriculum_vitae) {
        this.curriculum_vitae = curriculum_vitae;
    }

    public int isEmployee() {
        return isEmployee;
    }

    public void setEmployee(int employee) {
        isEmployee = employee;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getEmployeeTypeId() {
        return employeeTypeId;
    }

    public void setEmployeeTypeId(Integer employeeTypeId) {
        this.employeeTypeId = employeeTypeId;
    }

    public int getIsManager() {
        return isManager;
    }

    public void setIsManager(int isManager) {
        this.isManager = isManager;
    }

    public boolean isWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(boolean workStatus) {
        this.workStatus = workStatus;
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

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public int getIsEmployee() {
        return isEmployee;
    }

    public void setIsEmployee(int isEmployee) {
        this.isEmployee = isEmployee;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public Integer getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(Integer salaryId) {
        this.salaryId = salaryId;
    }

    public Integer getUpdatedByEmployee() {
        return updatedByEmployee;

    }

    public void setUpdatedByEmployee(Integer updatedByEmployee) {
        this.updatedByEmployee = updatedByEmployee;
    }

    public Integer getCreatedByEmployee() {
        return createdByEmployee;
    }

    public void setCreatedByEmployee(Integer createdByEmployee) {
        this.createdByEmployee = createdByEmployee;
    }

    public int getRemainingAbsenceDays() {
        return remainingAbsenceDays;
    }

    public void setRemainingAbsenceDays(int remainingAbsenceDays) {
        this.remainingAbsenceDays = remainingAbsenceDays;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "idEmployee=" + idEmployee +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", nameEmployee='" + nameEmployee + '\'' +
                ", birthday='" + birthday + '\'' +
                ", gender=" + gender +
                ", mobile='" + mobile + '\'' +
                ", address='" + address + '\'' +
                ", maritalStatus=" + maritalStatus +
                ", startWorkDate='" + startWorkDate + '\'' +
                ", endWorkDate='" + endWorkDate + '\'' +
                ", curriculum_vitae='" + curriculum_vitae + '\'' +
                ", isEmployee=" + isEmployee +
                ", company='" + company + '\'' +
                ", avatar='" + avatar + '\'' +
                ", employeeTypeId=" + employeeTypeId +
                ", idRole=" + idRole +
                ", isManager=" + isManager +
                ", salaryId=" + salaryId +
                ", workStatus=" + workStatus +
                ", updatedAt='" + updatedAt + '\'' +
                ", updatedByEmployee=" + updatedByEmployee +
                ", createdAt='" + createdAt + '\'' +
                ", createdByEmployee=" + createdByEmployee +
                ", deleteFlag=" + deleteFlag +
                ", remainingAbsenceDays=" + remainingAbsenceDays +
                ", permissions=" + permissions +
                ", employeeType=" + employeeType +
                ", teams=" + teams +
                ", projects=" + projects +
                ", role=" + role +
                '}';
    }
}
