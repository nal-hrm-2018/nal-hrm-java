package nals.hrm.api_nals_hrm.dto;

import nals.hrm.api_nals_hrm.entities.Processes;
import nals.hrm.api_nals_hrm.entities.Project;

public class EmployeeProjectDTO {
  Project project;
  Processes processes;

  public EmployeeProjectDTO() {
  }

  public EmployeeProjectDTO(Project project, Processes processes) {
    this.project = project;
    this.processes = processes;
  }

  public Project getProject() {
    return project;
  }

  public void setProject(Project project) {
    this.project = project;
  }

  public Processes getProcesses() {
    return processes;
  }

  public void setProcesses(Processes processes) {
    this.processes = processes;
  }
}
