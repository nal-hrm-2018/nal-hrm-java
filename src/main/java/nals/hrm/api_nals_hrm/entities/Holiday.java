package nals.hrm.api_nals_hrm.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "holidays")
public class Holiday implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private int idHoliday;

    @Column(name = "date")
    private String dateHoliday;

    @Column(name = "name")
    private String nameHoliday;

    @Column(name = "description")
    private String description;

    @Column(name = "holiday_status_id")
    private int holidayStatusId;

    @Column(name = "delete_flag")
    private int deleteFlag;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "holiday_status_id", insertable = false, updatable = false)
    private HolidayStatus holidayStatus;

    public int getIdHoliday() {
        return idHoliday;
    }

    public void setIdHoliday(int idHoliday) {
        this.idHoliday = idHoliday;
    }

    public String getDateHoliday() {
        return dateHoliday;
    }

    public void setDateHoliday(String dateHoliday) {
        this.dateHoliday = dateHoliday;
    }

    public String getNameHoliday() {
        return nameHoliday;
    }

    public void setNameHoliday(String nameHoliday) {
        this.nameHoliday = nameHoliday;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getHolidayStatusId() {
        return holidayStatusId;
    }

    public void setHolidayStatusId(int holidayStatusId) {
        this.holidayStatusId = holidayStatusId;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public HolidayStatus getHolidayStatus() {
        return holidayStatus;
    }

    public void setHolidayStatus(HolidayStatus holidayStatus) {
        this.holidayStatus = holidayStatus;
    }
}
