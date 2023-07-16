package edu.gatech.seclass.jobcompare6300;

public class JobOffer extends Job {
    public JobOffer(
            String title,
            String company,
            Location location,
            int costOfLivingIndex,
            double yearlySalary,
            double yearlyBonus,
            int retirementBenefits,
            double relocationStipend,
            double trainingFund) {
        super(title, company, location, costOfLivingIndex, yearlySalary, yearlyBonus, retirementBenefits, relocationStipend, trainingFund);
    }
}
