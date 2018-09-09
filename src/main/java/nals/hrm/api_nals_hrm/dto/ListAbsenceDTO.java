package nals.hrm.api_nals_hrm.dto;

public class ListAbsenceDTO {

    private int allowAbsence; //number annualLeave allow
    private int remainingAbsenceDays;//số ngày phép năm ngoái còn lại
    private int annualLeave;
    private int unpaidLeave; //số ngày nghỉ không trả lương
    private int marriageLeave; //nghỉ cưới
    private int bereavementLeave; //nghỉ tang
    private int maternityLeave;//nghỉ thai sản
    private int sickLeave;//nghi om


    private ListDTO listAbsence;

    public ListAbsenceDTO(int allowAbsence, int remainingAbsenceDays, int absence, int unpaidLeave,
                          int marriageLeave, int bereavementLeave,
                          int maternityLeave, int sickLeave, ListDTO listAbsence) {
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

    public int getAllowAbsence() {
        return allowAbsence;
    }

    public void setAllowAbsence(int allowAbsence) {
        this.allowAbsence = allowAbsence;
    }

    public int getRemainingAbsenceDays() {
        return remainingAbsenceDays;
    }

    public void setRemainingAbsenceDays(int remainingAbsenceDays) {
        this.remainingAbsenceDays = remainingAbsenceDays;
    }

    public int getAnnualLeave() {
        return annualLeave;
    }

    public void setAnnualLeave(int annualLeave) {
        this.annualLeave = annualLeave;
    }

    public int getUnpaidLeave() {
        return unpaidLeave;
    }

    public void setUnpaidLeave(int unpaidLeave) {
        this.unpaidLeave = unpaidLeave;
    }

    public int getMarriageLeave() {
        return marriageLeave;
    }

    public void setMarriageLeave(int marriageLeave) {
        this.marriageLeave = marriageLeave;
    }

    public int getBereavementLeave() {
        return bereavementLeave;
    }

    public void setBereavementLeave(int bereavementLeave) {
        this.bereavementLeave = bereavementLeave;
    }

    public int getMaternityLeave() {
        return maternityLeave;
    }

    public void setMaternityLeave(int maternityLeave) {
        this.maternityLeave = maternityLeave;
    }

    public ListDTO getListAbsence() {
        return listAbsence;
    }

    public void setListAbsence(ListDTO listAbsence) {
        this.listAbsence = listAbsence;
    }

    public int getSickLeave() {
        return sickLeave;
    }

    public void setSickLeave(int sickLeave) {
        this.sickLeave = sickLeave;
    }
}
