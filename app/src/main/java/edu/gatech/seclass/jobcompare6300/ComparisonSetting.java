package edu.gatech.seclass.jobcompare6300;

public class ComparisonSetting {
    public int yearlySalary;
    public int yearlyBonus;
    public int retirementBenefits;
    public int relocationStipend;
    public int trainingFund;

    public ComparisonSetting(int yearlySalary, int yearlyBonus, int retirementBenefits, int relocationStipend, int trainingFund) {
        this.yearlySalary = yearlySalary;
        this.yearlyBonus = yearlyBonus;
        this.retirementBenefits = retirementBenefits;
        this.relocationStipend = relocationStipend;
        this.trainingFund = trainingFund;
    }

    public int getWeightSum(){
        return yearlySalary + yearlyBonus + retirementBenefits + relocationStipend + trainingFund;
    }
}
