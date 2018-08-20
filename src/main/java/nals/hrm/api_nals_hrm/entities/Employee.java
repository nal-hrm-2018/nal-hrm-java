package nals.hrm.api_nals_hrm.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    int idEmployee;

    @Column(name = "email")
    String email;

    @JsonIgnore
    @Column(name = "password")
    String password;

    @Column(name = "name")
    String nameEmployee;

    @Column(name = "birthday")
    String birthday;

    @JsonIgnore
    @Column(name = "gender")
    Integer gender;

    @Column(name = "mobile")
    String mobile;

    @Column(name = "address")
    String address;

    @JsonIgnore
    @Column(name = "marital_status")
    int maritalStatus;

    @Column(name = "startwork_date")
    String startWorkDate;

    @Column(name = "endwork_date")
    String endWorkDate;

    @Column(name = "curriculum_vitae")
    String curriculum_vitae;

    @JsonIgnore
    @Column(name = "is_employee")
    int isEmployee;

    @Column(name = "company")
    String company;

    @Column(name = "avatar")
    String avatar;

    @JsonIgnore
    @Column(name = "employee_type_id")
    Integer employeeTypeId;

    @JsonIgnore
    @Column(name = "role_id")
    int idRole;

    @JsonIgnore
    @Column(name = "is_manager")
    int isManager;

    @JsonIgnore
    @Column(name = "salary_id")
    Integer salaryId;

    @JsonIgnore
    @Column(name = "work_status")
    boolean workStatus;

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


    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "permission_employee",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private List<Permission> permissions;

    @JsonIgnore
    @ManyToOne
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

//    @JsonIgnore
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "role_id", insertable = false, updatable = false)
//    private Role role;

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

}
