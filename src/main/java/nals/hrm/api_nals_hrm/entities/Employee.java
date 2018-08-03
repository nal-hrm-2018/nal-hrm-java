package nals.hrm.api_nals_hrm.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "employees")
public class Employee implements Serializable {
  @Id
  @GeneratedValue
  @Column(name = "id", nullable = false)
  int idEmployee;

  @Column(name = "email")
  String email;

  @Column(name = "remember_token")
  String rememberToken;

  @Column(name = "password")
  String password;

  @Column(name = "role_id")
  int idRole;

  @Column(name = "name")
  String nameEmployee;

  @Column(name = "birthday")
  String birthday;

  @Column(name = "gender")
  int gender;

  @Column(name = "mobile")
  int mobile;

  @Column(name = "address")
   String address;

  @Column(name = "marital_status")
   int maritalStatus;

  @Column(name = "startwork_date")
   String startworkDate;

  @Column(name = "endworkDate")
   String endworkDate;

  @Column(name = "curriculum_vitae")
   String curriculumVitae;

  @Column(name = "is_employee")
   boolean isEmployee;

  @Column(name = "company")
   String company;

  @Column(name = "avatar")
  String avatar;

  @Column(name = "employee_type_id")
  int employeeTypeId;

  @Column(name = "team_id")
  int teamId;

  @Column(name = "is_manager")
  boolean isManager;

  @Column(name = "salary_id")
  int salaryId;

  @Column(name = "work_status")
   boolean workStatus ;

  @Column(name = "updated_at")
  String updatedAt;

  @Column(name = "updated_by_employee", nullable = true)
   int updatedByEmployee;

  @Column(name = "created_at")
   String createdAt;

  @Column(name = "created_by_employee")
  int createdByEmployee;

  @Column(name = "delete_flag")
   boolean deleteFlag;


  Role role;

  public Employee() {
    super();
  }

  public Employee(int idEmployee, String email, Role role) {
    this.idEmployee = idEmployee;
    this.email = email;
    this.role = role;
  }

  public Employee(String email, String rememberToken, String password, int idRole, String nameEmployee, String birthday, int gender, int mobile, String address, int maritalStatus, String startworkDate, String endworkDate, String curriculumVitae, boolean isEmployee, String company, String avatar, int employeeTypeId, int teamId, boolean isManager, int salaryId, boolean workStatus, String updatedAt, int updatedByEmployee, String createdAt, int createdByEmployee, boolean deleteFlag, Role role) {
    this.email = email;
    this.rememberToken = rememberToken;
    this.password = password;
    this.idRole = idRole;
    this.nameEmployee = nameEmployee;
    this.birthday = birthday;
    this.gender = gender;
    this.mobile = mobile;
    this.address = address;
    this.maritalStatus = maritalStatus;
    this.startworkDate = startworkDate;
    this.endworkDate = endworkDate;
    this.curriculumVitae = curriculumVitae;
    this.isEmployee = isEmployee;
    this.company = company;
    this.avatar = avatar;
    this.employeeTypeId = employeeTypeId;
    this.teamId = teamId;
    this.isManager = isManager;
    this.salaryId = salaryId;
    this.workStatus = workStatus;
    this.updatedAt = updatedAt;
    this.updatedByEmployee = updatedByEmployee;
    this.createdAt = createdAt;
    this.createdByEmployee = createdByEmployee;
    this.deleteFlag = deleteFlag;
    this.role = role;
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

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public String getRememberToken() {
    return rememberToken;
  }

  public void setRememberToken(String rememberToken) {
    this.rememberToken = rememberToken;
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

  public int getGender() {
    return gender;
  }

  public void setGender(int gender) {
    this.gender = gender;
  }

  public int getMobile() {
    return mobile;
  }

  public void setMobile(int mobile) {
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

  public String getStartworkDate() {
    return startworkDate;
  }

  public void setStartworkDate(String startworkDate) {
    this.startworkDate = startworkDate;
  }

  public String getEndworkDate() {
    return endworkDate;
  }

  public void setEndworkDate(String endworkDate) {
    this.endworkDate = endworkDate;
  }

  public String getCurriculumVitae() {
    return curriculumVitae;
  }

  public void setCurriculumVitae(String curriculumVitae) {
    this.curriculumVitae = curriculumVitae;
  }

  public boolean isEmployee() {
    return isEmployee;
  }

  public void setEmployee(boolean employee) {
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

  public int getEmployeeTypeId() {
    return employeeTypeId;
  }

  public void setEmployeeTypeId(int employeeTypeId) {
    this.employeeTypeId = employeeTypeId;
  }

  public int getTeamId() {
    return teamId;
  }

  public void setTeamId(int teamId) {
    this.teamId = teamId;
  }

  public boolean isManager() {
    return isManager;
  }

  public void setManager(boolean manager) {
    isManager = manager;
  }

  public int getSalaryId() {
    return salaryId;
  }

  public void setSalaryId(int salaryId) {
    this.salaryId = salaryId;
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

  public int getUpdatedByEmployee() {
    return updatedByEmployee;
  }

  public void setUpdatedByEmployee(int updatedByEmployee) {
    this.updatedByEmployee = updatedByEmployee;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public int getCreatedByEmployee() {
    return createdByEmployee;
  }

  public void setCreatedByEmployee(int createdByEmployee) {
    this.createdByEmployee = createdByEmployee;
  }

  public boolean isDeleteFlag() {
    return deleteFlag;
  }

  public void setDeleteFlag(boolean deleteFlag) {
    this.deleteFlag = deleteFlag;
  }

  @Override
  public String toString() {
    return "Employee{" +
            "idEmployee=" + idEmployee +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            ", idRole=" + idRole +
            ", role=" + role +
            '}';
  }
}
