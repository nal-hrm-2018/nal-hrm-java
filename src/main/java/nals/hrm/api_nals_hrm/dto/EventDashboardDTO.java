package nals.hrm.api_nals_hrm.dto;

public class EventDashboardDTO {

  private int newEmployees;
  private int birthdays;
  private int employeesQuit;

  public EventDashboardDTO(int newEmployees, int birthdays, int employeesQuit) {
    this.newEmployees = newEmployees;
    this.birthdays = birthdays;
    this.employeesQuit = employeesQuit;
  }

  public int getNewEmployees() {
    return newEmployees;
  }

  public void setNewEmployees(int newEmployees) {
    this.newEmployees = newEmployees;
  }

  public int getBirthdays() {
    return birthdays;
  }

  public void setBirthdays(int birthdays) {
    this.birthdays = birthdays;
  }

  public int getEmployeesQuit() {
    return employeesQuit;
  }

  public void setEmployeesQuit(int employeesQuit) {
    this.employeesQuit = employeesQuit;
  }
}
