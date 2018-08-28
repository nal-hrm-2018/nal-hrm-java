package nals.hrm.api_nals_hrm.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "absence_time")
public class AbsenceTime implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private int idAbsenceTime;

    @Column(name = "name")
    private String nameAbsenceTime;

    @Column(name = "description")
    private String description;

    public AbsenceTime() {
    }

    public AbsenceTime(int idAbsenceType, String nameAbsenceTime, String description) {
        this.idAbsenceTime = idAbsenceType;
        this.nameAbsenceTime = nameAbsenceTime;
        this.description = description;
    }

    public int getIdAbsenceTime() {
        return idAbsenceTime;
    }

    public void setIdAbsenceTime(int idAbsenceTime) {
        this.idAbsenceTime = idAbsenceTime;
    }

    public String getNameAbsenceTime() {
        return nameAbsenceTime;
    }

    public void setNameAbsenceTime(String nameAbsenceTime) {
        this.nameAbsenceTime = nameAbsenceTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
