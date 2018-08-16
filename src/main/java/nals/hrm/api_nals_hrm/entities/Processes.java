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
    int idProcesses;

    @Column(name = "title")
    String title;

    @Column(name = "employee_id")
    int employeeId;

    @Column(name = "project_id")
    String projectId;

    @Column(name = "role_id")
    int roleId;

    @Column(name = "check_project_exit")
    int checkProjectExit;

    @Column(name = "man_power")
    double man_power;

    @Column(name = "start_date")
    String startDate;

    @Column(name = "end_date")
    String endDate;

    @JsonIgnore
    @Column(name = "updated_at")
    String updatedAt;

    @JsonIgnore
    @Column(name = "updated_by_employee")
    int updatedByEmployee;

    @JsonIgnore
    @Column(name = "created_at")
    String createdAt;

    @JsonIgnore
    @Column(name = "created_by_employee")
    int createdByEmployee;

    @JsonIgnore
    @Column(name = "delete_flag")
    int deleteFlag;











}
