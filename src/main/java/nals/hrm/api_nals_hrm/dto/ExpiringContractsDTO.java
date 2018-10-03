package nals.hrm.api_nals_hrm.dto;

public class ExpiringContractsDTO {
    private int internship;
    private int probation;
    private int oneYear;
    private int threeYear;

    public ExpiringContractsDTO(int internship, int probation, int oneYear, int threeYear) {
        this.internship = internship;
        this.probation = probation;
        this.oneYear = oneYear;
        this.threeYear = threeYear;
    }

    public int getInternship() {
        return internship;
    }

    public void setInternship(int internship) {
        this.internship = internship;
    }

    public int getProbation() {
        return probation;
    }

    public void setProbation(int probation) {
        this.probation = probation;
    }

    public int getOneYear() {
        return oneYear;
    }

    public void setOneYear(int oneYear) {
        this.oneYear = oneYear;
    }

    public int getThreeYear() {
        return threeYear;
    }

    public void setThreeYear(int threeYear) {
        this.threeYear = threeYear;
    }
}
