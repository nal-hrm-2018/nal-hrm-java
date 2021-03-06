package nals.hrm.api_nals_hrm.dto;

import nals.hrm.api_nals_hrm.entities.EmployeeType;
import nals.hrm.api_nals_hrm.entities.Permission;
import nals.hrm.api_nals_hrm.entities.Role;
import nals.hrm.api_nals_hrm.entities.Team;

import java.util.List;

public class ProfileDTO {
  int idEmployee;

  String email;

  String nameEmployee;

  String birthday;

  GenderDTO genderDTO;

  String mobile;

  String address;

  MaritalStatusDTO maritalStatusDTO;

  String startWorkDate;

  String endWorkDate;

  String curriculum_vitae;

  boolean isEmployee;

  String company;

  String avatar;

  EmployeeType employeeType;

  Role role;

  int isManager;

  int salaryId;

  String updatedAt;

  String createdAt;

  List<Permission> permissions;

  List<Team> teams;

  public ProfileDTO() {
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

  public GenderDTO getGenderDTO() {
    return genderDTO;
  }

  public void setGenderDTO(GenderDTO genderDTO) {
    this.genderDTO = genderDTO;
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

  public MaritalStatusDTO getMaritalStatusDTO() {
    return maritalStatusDTO;
  }

  public void setMaritalStatusDTO(MaritalStatusDTO maritalStatusDTO) {
    this.maritalStatusDTO = maritalStatusDTO;
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

  public EmployeeType getEmployeeType() {
    return employeeType;
  }

  public void setEmployeeType(EmployeeType employeeType) {
    this.employeeType = employeeType;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public int getIsManager() {
    return isManager;
  }

  public void setIsManager(int isManager) {
    this.isManager = isManager;
  }

  public int getSalaryId() {
    return salaryId;
  }

  public void setSalaryId(int salaryId) {
    this.salaryId = salaryId;
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

  public List<Permission> getPermissions() {
    return permissions;
  }

  public void setPermissions(List<Permission> permissions) {
    this.permissions = permissions;
  }

  public List<Team> getTeams() {
    return teams;
  }

  public void setTeams(List<Team> teams) {
    this.teams = teams;
  }

  @Override
  public String toString() {
    return "ProfileDTO{" +
            "idEmployee=" + idEmployee +
            ", email='" + email + '\'' +
            ", nameEmployee='" + nameEmployee + '\'' +
            ", birthday='" + birthday + '\'' +
            ", genderDTO=" + genderDTO +
            ", mobile='" + mobile + '\'' +
            ", address='" + address + '\'' +
            ", maritalStatusDTO=" + maritalStatusDTO +
            ", startWorkDate='" + startWorkDate + '\'' +
            ", endWorkDate='" + endWorkDate + '\'' +
            ", curriculum_vitae='" + curriculum_vitae + '\'' +
            ", isEmployee=" + isEmployee +
            ", company='" + company + '\'' +
            ", avatar='" + avatar + '\'' +
            ", employeeType=" + employeeType +
            ", roleId=" + role +
            ", isManager=" + isManager +
            ", salaryId=" + salaryId +
            ", updatedAt='" + updatedAt + '\'' +
            ", createdAt='" + createdAt + '\'' +
            ", permissions=" + permissions +
            ", teams=" + teams +
            '}';
  }
}