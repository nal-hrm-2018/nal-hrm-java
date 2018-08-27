package nals.hrm.api_nals_hrm.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "absences")
public class Absence {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int idAbsences;

    @Column(name = "employee_id")
    private int employeeId;

    @JsonIgnore
    @Column(name = "absence_type_id")
    private int absenceTypeId;

    @Column(name = "from_date")
    private String fromDate;

    @Column(name = "to_date")
    private String toDate;

    @Column(name = "reason")
    private String reason;

    @Column(name = "description")
    private String description;

    @JsonIgnore
    @Column(name = "delete_flag")
    private int deleteFlag;

    @JsonIgnore
    @Column(name = "absence_time_id")
    private int absenceTimeId;


    @ManyToOne
    @JoinColumn(name = "absence_type_id", insertable = false, updatable = false)
    private AbsenceType absenceType;

    @ManyToOne
    @JoinColumn(name = "absence_time_id", insertable = false, updatable = false)
    private AbsenceTime absenceTime;

    public Absence() {
    }

    public Absence(int idAbsences, int employeeId, int absenceTypeId, String fromDate, String toDate, String reason,
                   String description, int deleteFlag) {
        this.idAbsences = idAbsences;
        this.employeeId = employeeId;
        this.absenceTypeId = absenceTypeId;
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

    public AbsenceType getAbsenceType() {
        return absenceType;
    }

    public void setAbsenceType(AbsenceType absenceType) {
        this.absenceType = absenceType;
    }

    public AbsenceTime getAbsenceTime() {
        return absenceTime;
    }

    public void setAbsenceTime(AbsenceTime absenceTime) {
        this.absenceTime = absenceTime;
    }

    public int getAbsenceTimeId() {
        return absenceTimeId;
    }

    public void setAbsenceTimeId(int absenceTimeId) {
        this.absenceTimeId = absenceTimeId;
    }
}
