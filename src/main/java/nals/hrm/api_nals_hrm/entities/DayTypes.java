package nals.hrm.api_nals_hrm.entities;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "day_types")
public class DateTypes implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private int idDateType;

    @Column(name="name")
    private String nameDayType;

    @Column(name = "delete_flag")
    private int deleteFlag;

    public int getIdDateType() {
        return idDateType;
    }

    public void setIdDateType(int idDateType) {
        this.idDateType = idDateType;
    }

    public String getNameDayType() {
        return nameDayType;
    }

    public void setNameDayType(String nameDayType) {
        this.nameDayType = nameDayType;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
