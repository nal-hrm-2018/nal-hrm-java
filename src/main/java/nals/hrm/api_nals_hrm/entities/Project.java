package nals.hrm.api_nals_hrm.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "projects")
public class Project implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    String idProject;

    @Column(name = "name")
    String nameProject;

    @Column(name = "income")
    double income;

    @Column(name = "real_cost")
    double realCost;

    @Column(name = "description")
    String description;

    @Column(name = "status_id")
    int statusId;

    @Column(name = "estimate_start_date")
    String estimateStartDate;

    @Column(name = "start_date")
    String startDate;

    @Column(name = "estimate_end_date")
    String estimateEndDate;

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


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="status_id",insertable=false, updatable=false)
    Status status;




}

