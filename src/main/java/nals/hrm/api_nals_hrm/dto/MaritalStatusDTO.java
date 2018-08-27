package nals.hrm.api_nals_hrm.dto;

public class MaritalStatusDTO {
    int maritalStatus;
    String typeMaritalStatus;

    public MaritalStatusDTO() {
    }

    public MaritalStatusDTO(int maritalStatus, String typeMaritalStatus) {
        this.maritalStatus = maritalStatus;
        this.typeMaritalStatus = typeMaritalStatus;
    }

    public int getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(int maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getTypeMaritalStatus() {
        switch (this.maritalStatus){
            case 1: this.typeMaritalStatus = "Single"; break;
            case 2: this.typeMaritalStatus = "Married"; break;
            case 3: this.typeMaritalStatus = "Separated";break;
            default: this.typeMaritalStatus = "Divorced";
        }
        return typeMaritalStatus;
    }

    public void setTypeMaritalStatus(String typeMaritalStatus) {
        switch (this.maritalStatus){
            case 1: typeMaritalStatus = "Single"; break;
            case 2: typeMaritalStatus = "Married"; break;
            case 3: typeMaritalStatus = "Separated";break;
            default: typeMaritalStatus = "Divorced";
        }
        this.typeMaritalStatus = typeMaritalStatus;
    }


}
