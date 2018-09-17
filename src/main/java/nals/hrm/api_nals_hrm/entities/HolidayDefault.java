package nals.hrm.api_nals_hrm.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "holidays_default")
public class HolidayDefault implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private int idHolidayDefault;

    @Column(name = "date")
    private String dateHolidayDefault;

    @Column(name = "name")
    private String nameHolidayDefault;

    @Column(name = "description")
    private String description;

    @Column(name = "holiday_status_id")
    private int holidayStatusId;

    @JsonIgnore
    @Column(name = "delete_flag")
    private int deleteFlag;

//    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "holiday_status_id", insertable = false, updatable = false)
    private HolidayStatus holidayStatus;

    public int getIdHolidayDefault() {
        return idHolidayDefault;
    }

    public void setIdHolidayDefault(int idHolidayDefault) {
        this.idHolidayDefault = idHolidayDefault;
    }

    public String getDateHolidayDefault() {
        return dateHolidayDefault;
    }

    public void setDateHolidayDefault(String dateHolidayDefault) {
        this.dateHolidayDefault = dateHolidayDefault;
    }

    public String getNameHolidayDefault() {
        return nameHolidayDefault;
    }

    public void setNameHolidayDefault(String nameHolidayDefault) {
        this.nameHolidayDefault = nameHolidayDefault;
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
