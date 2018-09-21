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

    @Column(name = "day_type_id")
    private int dayTypeId;

    @JsonIgnore
    @Column(name = "delete_flag")
    private int deleteFlag;

//    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "day_type_id", insertable = false, updatable = false)
    private DayTypes dateTypes;

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

    public int getDayTypeId() {
        return dayTypeId;
    }

    public void setDayTypeId(int dayTypeId) {
        this.dayTypeId = dayTypeId;
    }

    public DayTypes getDateTypes() {
        return dateTypes;
    }

    public void setDateTypes(DayTypes dateTypes) {
        this.dateTypes = dateTypes;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

}
