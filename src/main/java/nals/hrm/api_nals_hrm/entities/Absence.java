package nals.hrm.api_nals_hrm.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "absences")
public class Absence {

    @Id
    @GeneratedValue
    @Column(name = "id")
    int idAbsences;

    @Column(name = "employee_id")
    int employeeId;

    @Column(name = "absence_type_id")
    int absenceTypeId;

    @Column(name = "absence_status_id")
    int absenceStatusId;

    @Column(name = "from_date")
    String fromDate;

    @Column(name = "to_date")
    String toDate;

    @Column(name = "reason")
    String reason;

    @Column(name = "description")
    String description;

    @JsonIgnore
    @Column(name = "delete_flag")
    int deleteFlag;


    public Absence() {
    }

    public Absence(int idAbsences, int employeeId, int absenceTypeId, int absenceStatusId,
                   String fromDate, String toDate, String reason,
                   String description, int deleteFlag) {
        this.idAbsences = idAbsences;
        this.employeeId = employeeId;
        this.absenceTypeId = absenceTypeId;
        this.absenceStatusId = absenceStatusId;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.reason = reason;
        this.description = description;
        this.deleteFlag = deleteFlag;
    }

    public int getIdAbsences() {
        return idAbsences;
    }

    public void setIdAbsences(int idAbsences) {
        this.idAbsences = idAbsences;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getAbsenceTypeId() {
        return absenceTypeId;
    }

    public void setAbsenceTypeId(int absenceTypeId) {
        this.absenceTypeId = absenceTypeId;
    }

    public int getAbsenceStatusId() {
        return absenceStatusId;
    }

    public void setAbsenceStatusId(int absenceStatusId) {
        this.absenceStatusId = absenceStatusId;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
