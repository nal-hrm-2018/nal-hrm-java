package nals.hrm.api_nals_hrm.entities;


import javax.persistence.*;

@Entity
@Table(name = "absence_statuses")
public class AbsenceStatus {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    int idAbsenceStatus;

    @Column(name = "name")
    String nameAbsenceStattus;

    @Column(name = "description")
    String description;

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
