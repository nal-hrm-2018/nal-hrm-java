package nals.hrm.api_nals_hrm.dto;

import nals.hrm.api_nals_hrm.entities.Status;


public class ProjectDTO {

  private int totalMember;

  private String idProject;

  private String nameProject;

  private String startDate;

  private String endDate;

  private String namePO;

  private Status status;

  public int getTotalMember() {
    return totalMember;
  }

  public void setTotalMember(int totalMember) {
    this.totalMember = totalMember;
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

  public String getNamePO() {
    return namePO;
  }

  public void setNamePO(String namePO) {
    this.namePO = namePO;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }
}
