package nals.hrm.api_nals_hrm.entities;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "holiday_statuses")
public class HolidayStatus implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private int idHolidayStatus;

    @Column(name="name")
    private String nameHolidayStatus;

    @Column(name = "delete_flag")
    private int deleteFlag;

    public int getIdHolidayStatus() {
        return idHolidayStatus;
    }

    public void setIdHolidayStatus(int idHolidayStatus) {
        this.idHolidayStatus = idHolidayStatus;
    }

    public String getNameHolidayStatus() {
        return nameHolidayStatus;
    }

    public void setNameHolidayStatus(String nameHolidayStatus) {
        this.nameHolidayStatus = nameHolidayStatus;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
