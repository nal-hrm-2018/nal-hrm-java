package nals.hrm.api_nals_hrm.dto;

public class ListAbsenceDTO {

    private double allowAbsence; //number annualLeave allow
    private double remainingAbsenceDays;//số ngày phép năm ngoái còn lại
    private double annualLeave;
    private double unpaidLeave; //số ngày nghỉ không trả lương
    private double marriageLeave; //nghỉ cưới
    private double bereavementLeave; //nghỉ tang
    private double maternityLeave;//nghỉ thai sản
    private double sickLeave;//nghi om


    private ListDTO listAbsence;

    public ListAbsenceDTO(double allowAbsence, double remainingAbsenceDays, double absence, double unpaidLeave,
                          double marriageLeave, double bereavementLeave,
                          double maternityLeave, double sickLeave, ListDTO listAbsence) {
        this.allowAbsence = allowAbsence;
        this.remainingAbsenceDays = remainingAbsenceDays;
        this.annualLeave = absence;
        this.unpaidLeave = unpaidLeave;
        this.marriageLeave = marriageLeave;
        this.bereavementLeave = bereavementLeave;
        this.maternityLeave = maternityLeave;
        this.sickLeave = sickLeave;
        this.listAbsence = listAbsence;
    }

    public double getAllowAbsence() {
        return allowAbsence;
    }

    public void setAllowAbsence(double allowAbsence) {
        this.allowAbsence = allowAbsence;
    }

    public double getRemainingAbsenceDays() {
        return remainingAbsenceDays;
    }

    public void setRemainingAbsenceDays(double remainingAbsenceDays) {
        this.remainingAbsenceDays = remainingAbsenceDays;
    }

    public double getAnnualLeave() {
        return annualLeave;
    }

    public void setAnnualLeave(double annualLeave) {
        this.annualLeave = annualLeave;
    }

    public double getUnpaidLeave() {
        return unpaidLeave;
    }

    public void setUnpaidLeave(double unpaidLeave) {
        this.unpaidLeave = unpaidLeave;
    }

    public double getMarriageLeave() {
        return marriageLeave;
    }

    public void setMarriageLeave(double marriageLeave) {
        this.marriageLeave = marriageLeave;
    }

    public double getBereavementLeave() {
        return bereavementLeave;
    }

    public void setBereavementLeave(double bereavementLeave) {
        this.bereavementLeave = bereavementLeave;
    }

    public double getMaternityLeave() {
        return maternityLeave;
    }

    public void setMaternityLeave(double maternityLeave) {
        this.maternityLeave = maternityLeave;
    }

    public ListDTO getListAbsence() {
        return listAbsence;
    }

    public void setListAbsence(ListDTO listAbsence) {
        this.listAbsence = listAbsence;
    }

    public double getSickLeave() {
        return sickLeave;
    }

    public void setSickLeave(double sickLeave) {
        this.sickLeave = sickLeave;
    }
}
