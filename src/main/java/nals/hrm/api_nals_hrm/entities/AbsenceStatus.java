package nals.hrm.api_nals_hrm.entities;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "absence_statuses")
public class AbsenceStatus implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private int idAbsenceStatus;

    @Column(name = "name")
    private String nameAbsenceStattus;

    @Column(name = "description")
    private String description;

    public AbsenceStatus() {
    }

    public AbsenceStatus(int idAbsenceStatus, String nameAbsenceStattus, String description) {
        this.idAbsenceStatus = idAbsenceStatus;
        this.nameAbsenceStattus = nameAbsenceStattus;
        this.description = description;
    }

    public int getIdAbsenceStatus() {
        return idAbsenceStatus;
    }

    public void setIdAbsenceStatus(int idAbsenceStatus) {
        this.idAbsenceStatus = idAbsenceStatus;
    }

    public String getNameAbsenceStattus() {
        return nameAbsenceStattus;
    }

    public void setNameAbsenceStattus(String nameAbsenceStattus) {
        this.nameAbsenceStattus = nameAbsenceStattus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
