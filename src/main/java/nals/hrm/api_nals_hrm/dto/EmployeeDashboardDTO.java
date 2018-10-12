package nals.hrm.api_nals_hrm.dto;

public class EmployeeDashboardDTO {

  private int totalEmployee;
  private int official;
  private int probation;
  private int internship;
  private int partTime;

  public EmployeeDashboardDTO(int totalEmployee, int official, int probation, int internship, int partTime) {
    this.totalEmployee = totalEmployee;
    this.official = official;
    this.probation = probation;
    this.internship = internship;
    this.partTime = partTime;
  }

  public int getTotalEmployee() {
    return totalEmployee;
  }

  public void setTotalEmployee(int totalEmployee) {
    this.totalEmployee = totalEmployee;
  }

  public int getOfficial() {
    return official;
  }

  public void setOfficial(int official) {
    this.official = official;
  }

  public int getProbation() {
    return probation;
  }

  public void setProbation(int probation) {
    this.probation = probation;
  }

  public int getInternship() {
    return internship;
  }

  public void setInternship(int internship) {
    this.internship = internship;
  }

  public int getPartTime() {
    return partTime;
  }

  public void setPartTime(int partTime) {
    this.partTime = partTime;
  }
}
