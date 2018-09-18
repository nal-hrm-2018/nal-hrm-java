package nals.hrm.api_nals_hrm.dto;


import nals.hrm.api_nals_hrm.entities.Employee;
import nals.hrm.api_nals_hrm.entities.OvertimeStatuses;
import nals.hrm.api_nals_hrm.entities.OvertimeTypes;
import nals.hrm.api_nals_hrm.entities.Project;



public class OverTimeDTO {

    private int id;

    private String reason;

    private String date;

    private String startTime;

    private String endTime;

    private float totalTime;

    private String correctTotalTime;

    private String updatedAt;

    private String createdAt;

    private OvertimeTypes overtimeTypes;

    private OvertimeStatuses overtimeStatuses;

    private Employee employee;

    private Project project;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public float getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(float totalTime) {
        this.totalTime = totalTime;
    }

    public String getCorrectTotalTime() {
        return correctTotalTime;
    }

    public void setCorrectTotalTime(String correctTotalTime) {
        this.correctTotalTime = correctTotalTime;
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

    public OvertimeTypes getOvertimeTypes() {
        return overtimeTypes;
    }

    public void setOvertimeTypes(OvertimeTypes overtimeTypes) {
        this.overtimeTypes = overtimeTypes;
    }

    public OvertimeStatuses getOvertimeStatuses() {
        return overtimeStatuses;
    }

    public void setOvertimeStatuses(OvertimeStatuses overtimeStatuses) {
        this.overtimeStatuses = overtimeStatuses;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
