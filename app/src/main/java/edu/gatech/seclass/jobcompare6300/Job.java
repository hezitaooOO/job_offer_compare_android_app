package edu.gatech.seclass.jobcompare6300;

public class Job {
    private String title;
    private String company;
    private Location location;
    private int costOfLivingIndex;
    private double yearlySalary;
    private double yearlyBonus;
    private int retirementBenefits;
    private double relocationStipend;
    private double trainingFund;
    private boolean selected;

    public Job (
            String title,
            String company,
            Location location,
            int costOfLivingIndex,
            double yearlySalary,
            double yearlyBonus,
            int retirementBenefits,
            double relocationStipend,
            double trainingFund) {
        this.title = title;
        this.company = company;
        this.location = location;
        this.costOfLivingIndex = costOfLivingIndex;
        this.yearlySalary = yearlySalary;
        this.yearlyBonus = yearlyBonus;
        this.retirementBenefits = retirementBenefits;
        this.relocationStipend = relocationStipend;
        this.trainingFund = trainingFund;
        this.selected = false;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getLocation() {
        return location.toString();
    }

    public String getState() {
        return location.getState();
    }

    public String getCity() {
        return location.getCity();
    }

    public int getCostOfLivingIndex() {
        return costOfLivingIndex;
    }

    public double getYearlySalary() {
        return yearlySalary;
    }

    public double getYearlyBonus() {
        return yearlyBonus;
    }

    public int getRetirementBenefits() {
        return retirementBenefits;
    }

    public double getRelocationStipend() {
        return relocationStipend;
    }

    public double getTrainingFund() { return trainingFund; }

    public boolean isSelected() { return selected; }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return "Job{" +
                "title='" + title + '\'' +
                ", company='" + company + '\'' +
                ", location=" + location +
                ", costOfLivingIndex=" + costOfLivingIndex +
                ", yearlySalary=" + yearlySalary +
                ", yearlyBonus=" + yearlyBonus +
                ", retirementBenefits=" + retirementBenefits +
                ", relocationStipend=" + relocationStipend +
                ", trainingFund=" + trainingFund +
                ", selected=" + selected +
                '}';
    }
}
