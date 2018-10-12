package nals.hrm.api_nals_hrm.dto;

public class ListOvertimeDTO {

  private float normal;
  private float dayOff;
  private float holiday;

  private ListDTO listDTO;


  public ListOvertimeDTO(float normal, float dayOff, float holiday, ListDTO listDTO) {
    this.normal = normal;
    this.dayOff = dayOff;
    this.holiday = holiday;
    this.listDTO = listDTO;
  }

  public float getNormal() {
    return normal;
  }

  public void setNormal(float normal) {
    this.normal = normal;
  }

  public float getDayOff() {
    return dayOff;
  }

  public void setDayOff(float dayOff) {
    this.dayOff = dayOff;
  }

  public float getHoliday() {
    return holiday;
  }

  public void setHoliday(float holiday) {
    this.holiday = holiday;
  }

  public ListDTO getListDTO() {
    return listDTO;
  }

  public void setListDTO(ListDTO listDTO) {
    this.listDTO = listDTO;
  }
}
