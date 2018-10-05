package nals.hrm.api_nals_hrm.dto;

public class GenderDTO {
  int gender;
  String nameGender;
  //1->female
  //2->male
  //3->other

  public GenderDTO() {
  }

  public GenderDTO(int gender, String nameGender) {
    this.gender = gender;
    this.nameGender = nameGender;
  }

  public int getGender() {
    return gender;
  }

  public void setGender(int gender) {

    this.gender = gender;
  }

  public String getNameGender() {
    switch (this.gender) {
      case 1:
        this.nameGender = "Female";
        break;
      case 2:
        this.nameGender = "Male";
        break;
      default:
        this.nameGender = "Others";
    }
    return nameGender;
  }

  public void setNameGender(String nameGender) {
    switch (this.gender) {
      case 1:
        nameGender = "Female";
        break;
      case 2:
        nameGender = "Male";
        break;
      default:
        nameGender = "Others";
    }
    this.nameGender = nameGender;
  }

  @Override
  public String toString() {
    return "GenderDTO{" +
            "gender=" + gender +
            ", nameGender='" + nameGender + '\'' +
            '}';
  }
}
