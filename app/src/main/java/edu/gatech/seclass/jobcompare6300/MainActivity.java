package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{

    //elements in main menu
    private Button buttonCompareJobs;

    // elements in comparison setting
    private EditText comparisonTextYearlySalary;
    private EditText comparisonTextYearlyBonus;
    private EditText comparisonTextRetirementBenefits;
    private EditText comparisonTextRelocationStipend;
    private EditText comparisonTextTrainingFund;
    private TextView textSaveCompareSettings;

    // elements in current job
    private Button jobButtonAddSave;
    private Button jobButtonCompare;
    private EditText jobTextTitle;
    private EditText jobTextCompany;
    private EditText jobTextCity;
    private EditText jobTextState;
    private EditText jobTextCostOfLivingIndex;
    private EditText jobTextYearlySalary;
    private EditText jobTextYearlyBonus;
    private EditText jobTextRetirementBenefits;
    private EditText jobTextRelocationStipend;
    private EditText jobTextTrainingFund;
    private TextView jobTextSaveJobResult;
    private boolean isCurrent;

    // elements in add job offer
    private boolean isAddJobOfferClicked;

    // element in compare jobs
    private ListView jobListView;
    private TextView compareJobCurrentJob;

    // elements in compare two jobs
    private Button buttonGoBackToCompareJobs;
    private TextView compareTwoJobsTitle1;
    private TextView compareTwoJobsTitle2;
    private TextView compareTwoJobsCompany1;
    private TextView compareTwoJobsCompany2;
    private TextView compareTwoJobsLocation1;
    private TextView compareTwoJobsLocation2;
    private TextView compareTwoJobsAdjustedSalary1;
    private TextView compareTwoJobsAdjustedSalary2;
    private TextView compareTwoJobsAdjustedBonus1;
    private TextView compareTwoJobsAdjustedBonus2;
    private TextView compareTwoJobsRetirementBenefits1;
    private TextView compareTwoJobsRetirementBenefits2;
    private TextView compareTwoJobsRelocationStipend1;
    private TextView compareTwoJobsRelocationStipend2;
    private TextView compareTwoJobsTrainingFund1;
    private TextView compareTwoJobsTrainingFund2;

    //data in memory
    DatabaseHelper databaseHelper;
    private CurrentJob currentJob;
    private List<JobOffer> jobOffers;
    private ComparisonSetting comparisonSetting;

    private List<Job> rankedJobList;
    private HashSet<Job> clickedJobs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // initialize the database and load data
        databaseHelper = new DatabaseHelper(MainActivity.this);

        // load data
        comparisonSetting = DatabaseComparisonSetting.getCurrentComparisonSetting(databaseHelper.getReadableDatabase());
        currentJob = DatabaseJob.getCurrentJob(databaseHelper.getReadableDatabase());
        jobOffers = DatabaseJob.getJobOffers(databaseHelper.getReadableDatabase());

        //initialize clickedJobs
        clickedJobs = new HashSet<>();

        renderMainMenu(null);
    }

    public void renderComparisonSetting(View view) {
        setContentView(R.layout.comparison_setting);
        setTitle("Adjust Comparison Setting");

        comparisonTextYearlySalary = findViewById(R.id.entryYearlySalary);
        comparisonTextYearlyBonus = findViewById(R.id.entryYearlyBonus);
        comparisonTextRetirementBenefits = findViewById(R.id.entryRetirementBenefits);
        comparisonTextRelocationStipend = findViewById(R.id.entryRelocationStipend);
        comparisonTextTrainingFund = findViewById(R.id.entryTrainingFund);
        textSaveCompareSettings = findViewById(R.id.textSaveCompareSettings);

        comparisonTextYearlySalary.setText(String.valueOf(comparisonSetting.yearlySalary));
        comparisonTextYearlyBonus.setText(String.valueOf(comparisonSetting.yearlyBonus));
        comparisonTextRetirementBenefits.setText(String.valueOf(comparisonSetting.retirementBenefits));
        comparisonTextRelocationStipend.setText(String.valueOf(comparisonSetting.relocationStipend));
        comparisonTextTrainingFund.setText(String.valueOf(comparisonSetting.trainingFund));

    }

    public void saveComparisonSetting(View view) {
        textSaveCompareSettings.setText("");
        ComparisonSetting newComparisonSetting = new ComparisonSetting(1, 1, 1, 1,1);
        boolean hasErr = false;
        try {
            if (Integer.parseInt(comparisonTextYearlySalary.getText().toString()) <= 0){
                comparisonTextYearlySalary.setError("Weight number must be positive");
                hasErr = true;
            }
            else{
                newComparisonSetting.yearlySalary = Integer.parseInt(comparisonTextYearlySalary.getText().toString());
            }
        }
        catch (NumberFormatException ex){
            comparisonTextYearlySalary.setError("Weight should be an valid integer");
            hasErr = true;
        }

        try {
            if (Integer.parseInt(comparisonTextYearlyBonus.getText().toString()) <= 0){
                comparisonTextYearlyBonus.setError("Weight number must be positive");
                hasErr = true;
            }
            else{
                newComparisonSetting.yearlyBonus = Integer.parseInt(comparisonTextYearlyBonus.getText().toString());
            }
        }
        catch (NumberFormatException ex){
            comparisonTextYearlyBonus.setError("Weight should be an valid integer");
            hasErr = true;
        }

        try {
            if (Integer.parseInt(comparisonTextRetirementBenefits.getText().toString()) <= 0){
                comparisonTextRetirementBenefits.setError("Weight number must be positive");
                hasErr = true;
            }
            else{
                newComparisonSetting.retirementBenefits = Integer.parseInt(comparisonTextRetirementBenefits.getText().toString());
            }
        }
        catch (NumberFormatException ex){
            comparisonTextRetirementBenefits.setError("Weight should be an valid integer");
            hasErr = true;
        }

        try {
            if (Integer.parseInt(comparisonTextRelocationStipend.getText().toString()) <= 0){
                comparisonTextRelocationStipend.setError("Weight number must be positive");
                hasErr = true;
            }
            else{
                newComparisonSetting.relocationStipend = Integer.parseInt(comparisonTextRelocationStipend.getText().toString());
            }
        }
        catch (NumberFormatException ex){
            comparisonTextRelocationStipend.setError("Weight should be an valid integer");
            hasErr = true;
        }

        try {
            if (Integer.parseInt(comparisonTextTrainingFund.getText().toString()) <= 0){
                comparisonTextTrainingFund.setError("Weight number must be positive");
                hasErr = true;
            }
            else{
                newComparisonSetting.trainingFund = Integer.parseInt(comparisonTextTrainingFund.getText().toString());
            }
        }
        catch (NumberFormatException ex){
            comparisonTextTrainingFund.setError("Weight should be an valid integer");
            hasErr = true;
        }

        if (!hasErr) {
            DatabaseComparisonSetting.adjustComparisonSetting(databaseHelper.getWritableDatabase(), newComparisonSetting);
            comparisonSetting = DatabaseComparisonSetting.getCurrentComparisonSetting(databaseHelper.getReadableDatabase());
            textSaveCompareSettings.setText("Compare settings are updated");
        }
    }

    public void renderMainMenu(View view) {
        setContentView(R.layout.activity_main);
        // elements in main activity
        Button buttonGotoCurrentJob = findViewById(R.id.buttonCurrentJob);
        Button buttonCompareJobs = findViewById(R.id.buttonCompareJobs);
        if (jobOffers.size() + (currentJob == null ? 0 : 1) < 2){
            buttonCompareJobs.setAlpha(.4f);
            buttonCompareJobs.setClickable(false);
        }
        if (currentJob != null) {
            buttonGotoCurrentJob.setText("EDIT CURRENT JOB");
        } else {
            buttonGotoCurrentJob.setText("ENTER CURRENT JOB");
        }

        setTitle("Job Compare App: Main Menu");
        isAddJobOfferClicked = false;
    }

    public void renderJob(boolean isCurrent) {
        setContentView(R.layout.add_edit_job);
        this.isCurrent = isCurrent;

        jobButtonAddSave = findViewById(R.id.buttonSave);
        jobButtonCompare = findViewById(R.id.jobButtonCompare);
        jobTextTitle = findViewById(R.id.entryTitle);
        jobTextCompany = findViewById(R.id.entryCompany);
        jobTextCity = findViewById(R.id.entryCity);
        jobTextState = findViewById(R.id.entryState);
        jobTextCostOfLivingIndex = findViewById(R.id.entryCostOfLivingIndex);
        jobTextYearlySalary = findViewById(R.id.entryYearlySalary);
        jobTextYearlyBonus = findViewById(R.id.entryYearlyBonus);
        jobTextRetirementBenefits = findViewById(R.id.entryRetirementBenefits);
        jobTextRelocationStipend = findViewById(R.id.entryRelocationStipend);
        jobTextTrainingFund = findViewById(R.id.entryTrainingFund);
        jobTextSaveJobResult = findViewById(R.id.textSaveJobResult);

        if (isCurrent) {
            if (currentJob == null) {
                setTitle("Add Current Job");
            } else {
                setTitle("Edit Current Job");
                jobButtonAddSave.setText("SAVE");
            }
            jobButtonCompare.setAlpha(.0f);
            jobButtonCompare.setClickable(false);
        }
        else {
            setTitle("Add Job Offer");
            jobButtonCompare.setClickable(true);
        }
    }

    public void renderCurrentJob(View view) {
        renderJob(true);
        if (currentJob != null) {
            jobTextTitle.setText(currentJob.getTitle());
            jobTextCompany.setText(currentJob.getCompany());
            jobTextCity.setText(currentJob.getCity());
            jobTextState.setText(currentJob.getState());
            jobTextCostOfLivingIndex.setText(Integer.toString(currentJob.getCostOfLivingIndex()));
            jobTextYearlySalary.setText(Double.toString(currentJob.getYearlySalary()));
            jobTextYearlyBonus.setText(Double.toString(currentJob.getYearlyBonus()));
            jobTextRetirementBenefits.setText(Integer.toString(currentJob.getRetirementBenefits()));
            jobTextRelocationStipend.setText(Double.toString(currentJob.getRelocationStipend()));
            jobTextTrainingFund.setText(Double.toString(currentJob.getTrainingFund()));
        }
    }

    public void renderJobOffer(View view) {
        renderJob(false);
    }

    public void addSaveJob(View view) {
        jobTextSaveJobResult.setText("");
        boolean hasErr = false;
        String title = jobTextTitle.getText().toString();
        if (title.isEmpty()) {
            jobTextTitle.setError("Title must be non-empty");
            hasErr = true;
        }

        String company = jobTextCompany.getText().toString();
        if (company.isEmpty()) {
            jobTextCompany.setError("Company must be non-empty");
            hasErr = true;
        }

        String city = jobTextCity.getText().toString();
        if (city.isEmpty()) {
            jobTextCity.setError("City must be non-empty");
            hasErr = true;
        }

        String state = jobTextState.getText().toString();
        if (state.isEmpty()) {
            jobTextState.setError("State must be non-empty");
            hasErr = true;
        }

        int costOfLivingIndex = 0;
        boolean err = false;
        try {
            costOfLivingIndex = Integer.parseInt(jobTextCostOfLivingIndex.getText().toString());
            if (costOfLivingIndex < 0) {
                err = true;
            }
        }
        catch (NumberFormatException ex){
            err = true;
        }
        if (err) {
            hasErr = true;
            jobTextCostOfLivingIndex.setError("Cost of living index should be a non-negative integer");
        }

        double yearlySalary = 0;
        err = false;
        try {
            yearlySalary = Double.parseDouble(jobTextYearlySalary.getText().toString());
            if (yearlySalary < 0) {
                err = true;
            }
        }
        catch (NumberFormatException ex){
            err = true;
        }
        if (err) {
            hasErr = true;
            jobTextYearlySalary.setError("Yearly salary must be a non-negative number");
        }

        double yearlyBonus = 0;
        err = false;
        try {
            yearlyBonus = Double.parseDouble(jobTextYearlyBonus.getText().toString());
            if (yearlyBonus < 0) {
                err = true;
            }
        }
        catch (NumberFormatException ex){
            err = true;
        }
        if (err) {
            hasErr = true;
            jobTextYearlyBonus.setError("Yearly bonus must be a non-negative number");
        }

        int retirementBenefits = 0;
        err = false;
        try {
            retirementBenefits = Integer.parseInt(jobTextRetirementBenefits.getText().toString());
            if (retirementBenefits < 0 || retirementBenefits > 100) {
                err = true;
            }
        }
        catch (NumberFormatException ex){
            err = true;
        }
        if (err) {
            hasErr = true;
            jobTextRetirementBenefits.setError("retirement benefit should be an integer between 0 and 100");
        }

        double relocationStipend = 0;
        err = false;
        try {
            relocationStipend = Double.parseDouble(jobTextRelocationStipend.getText().toString());
            if (relocationStipend < 0) {
                err = true;
            }
        }
        catch (NumberFormatException ex){
            err = true;
        }
        if (err) {
            hasErr = true;
            jobTextRelocationStipend.setError("Relocation Stipend must be a non-negative number");
        }

        double trainingFund = 0;
        err = false;
        try {
            trainingFund = Double.parseDouble(jobTextTrainingFund.getText().toString());
            if (trainingFund < 0 || trainingFund > 18000) {
                err = true;
            }
        }
        catch (NumberFormatException ex){
            err = true;
        }
        if (err) {
            hasErr = true;
            jobTextTrainingFund.setError("Tracking and development fund must be a number between 0 and 18000");
        }

        if (!hasErr) {
            if (isCurrent) {
                DatabaseJob.addEditCurrentJob(
                        databaseHelper.getWritableDatabase(),
                        title,
                        company,
                        new Location(city, state),
                        costOfLivingIndex,
                        yearlySalary,
                        yearlyBonus,
                        retirementBenefits,
                        relocationStipend,
                        trainingFund
                );
                currentJob = DatabaseJob.getCurrentJob(databaseHelper.getReadableDatabase());
                jobTextSaveJobResult.setText("Current job updated");
            } else {
                DatabaseJob.addJobOffer(
                        databaseHelper.getWritableDatabase(),
                        title,
                        company,
                        new Location(city, state),
                        costOfLivingIndex,
                        yearlySalary,
                        yearlyBonus,
                        retirementBenefits,
                        relocationStipend,
                        trainingFund
                );
                isAddJobOfferClicked = true;
                jobOffers = DatabaseJob.getJobOffers(databaseHelper.getReadableDatabase());
                jobTextSaveJobResult.setText(String.format("Job offer %s-%s located at %s-%s is added",
                        company, title, city, state));

                jobTextTitle.setText("");
                jobTextCompany.setText("");
                jobTextCity.setText("");
                jobTextState.setText("");
                jobTextCostOfLivingIndex.setText("");
                jobTextYearlySalary.setText("");
                jobTextYearlyBonus.setText("");
                jobTextRetirementBenefits.setText("");
                jobTextRelocationStipend.setText("");
                jobTextTrainingFund.setText("");
            }
        }
    }

    public void renderCompareJobs(View view){
        setContentView(R.layout.compare_jobs);
        setTitle("Compare Job Offers");
        jobListView = (ListView) findViewById(R.id.sortedJobLists);
        rankJobLists();
        if (rankedJobList.size() > 0){
            JobListAdapter adapter = new JobListAdapter(rankedJobList, this);
            jobListView.setAdapter(adapter);
        }
        compareJobCurrentJob = findViewById(R.id.compareJobMessage);
        String compareJobMessage = "";

        if (rankedJobList.size() == 0){
            compareJobMessage = "No job is entered. Please go back to main menu and enter jobs";
        }
        else if (rankedJobList.size() == 1){
            compareJobMessage = "Only one job is entered. To compare two jobs, please add more jobs. Red job is the current job";
        }
        else{
            compareJobMessage = "Red job is the current job";
        }
        compareJobCurrentJob.setText(compareJobMessage);
    }

    public void validateClickedJobs(View view){
        if (clickedJobs.size() != 2 && rankedJobList.size() < 2){
            Toast.makeText(getApplicationContext(),
                    "Less Than Two Jobs Are Added. Please Add More Jobs To Proceed", Toast.LENGTH_SHORT).show();
        }
        else if (clickedJobs.size() != 2){
            Toast.makeText(getApplicationContext(),
                    "Select Two Jobs to Proceed!", Toast.LENGTH_SHORT).show();
        }

        else{
            renderCompareTwoJobs(view, true);
        }
    }

    public void renderCompareTwoJobs(View view, boolean calledFromCompareJobs){
        setContentView(R.layout.compre_two_jobs);
        setTitle("Compare Two Jobs");
        compareTwoJobsTitle1 = findViewById(R.id.compareTwoJobsTitle1);
        compareTwoJobsTitle2 = findViewById(R.id.compareTwoJobsTitle2);
        compareTwoJobsCompany1 = findViewById(R.id.compareTwoJobsCompany1);
        compareTwoJobsCompany2 = findViewById(R.id.compareTwoJobsCompany2);
        compareTwoJobsLocation1 = findViewById(R.id.compareTwoJobsLocation1);
        compareTwoJobsLocation2 = findViewById(R.id.compareTwoJobsLocation2);
        compareTwoJobsAdjustedSalary1 = findViewById(R.id.compareTwoJobsAdjustedSalary1);
        compareTwoJobsAdjustedSalary2 = findViewById(R.id.compareTwoJobsAdjustedSalary2);
        compareTwoJobsAdjustedBonus1 = findViewById(R.id.compareTwoJobsAdjustedBonus1);
        compareTwoJobsAdjustedBonus2 = findViewById(R.id.compareTwoJobsAdjustedBonus2);
        compareTwoJobsRetirementBenefits1 = findViewById(R.id.compareTwoJobsRetirementBenefits1);
        compareTwoJobsRetirementBenefits2 = findViewById(R.id.compareTwoJobsRetirementBenefits2);
        compareTwoJobsRelocationStipend1 = findViewById(R.id.compareTwoJobsRelocationStipend1);
        compareTwoJobsRelocationStipend2 = findViewById(R.id.compareTwoJobsRelocationStipend2);
        compareTwoJobsTrainingFund1 = findViewById(R.id.compareTwoJobsTrainingFund1);
        compareTwoJobsTrainingFund2 = findViewById(R.id.compareTwoJobsTrainingFund2);
        buttonGoBackToCompareJobs = findViewById(R.id.buttonGoBackToCompareJobs);

        List<Job> list = new ArrayList<>();
        if (calledFromCompareJobs){
            list.addAll(clickedJobs);
        }
        else{
            list.add(jobOffers.get(jobOffers.size() - 1));
            list.add(currentJob);
            buttonGoBackToCompareJobs.setAlpha(.0f);
            buttonGoBackToCompareJobs.setClickable(false);
        }
        Collections.sort(list, new Comparator<Job>() {
            @Override
            public int compare(Job job, Job otherJob) {
                double jobScore = computeJobScore(job);
                double otherJobScore = computeJobScore(otherJob);
                if (jobScore > otherJobScore) return -1;
                else if (jobScore < otherJobScore) return 1;
                else{
                    return job.getCompany().compareTo(otherJob.getCompany());
                }
            }
        });
        compareTwoJobsTitle1.setText(list.get(0).getTitle());
        compareTwoJobsTitle2.setText(list.get(1).getTitle());
        compareTwoJobsCompany1.setText(list.get(0).getCompany());
        compareTwoJobsCompany2.setText(list.get(1).getCompany());
        compareTwoJobsLocation1.setText(list.get(0).getLocation());
        compareTwoJobsLocation2.setText(list.get(1).getLocation());
        int adjustedSalary1 = (int)(list.get(0).getYearlySalary() / (list.get(0).getCostOfLivingIndex()/100.0));
        int adjustedBonus1 = (int)(list.get(0).getYearlyBonus() / (list.get(0).getCostOfLivingIndex()/100.0));
        int adjustedSalary2 = (int)(list.get(1).getYearlySalary() / (list.get(1).getCostOfLivingIndex()/100.0));
        int adjustedBonus2 = (int)(list.get(1).getYearlyBonus() / (list.get(1).getCostOfLivingIndex()/100.0));
        compareTwoJobsAdjustedSalary1.setText(String.valueOf(adjustedSalary1));
        compareTwoJobsAdjustedSalary2.setText(String.valueOf(adjustedSalary2));
        compareTwoJobsAdjustedBonus1.setText(String.valueOf(adjustedBonus1));
        compareTwoJobsAdjustedBonus2.setText(String.valueOf(adjustedBonus2));
        compareTwoJobsRetirementBenefits1.setText(String.valueOf(list.get(0).getRetirementBenefits()));
        compareTwoJobsRetirementBenefits2.setText(String.valueOf(list.get(1).getRetirementBenefits()));
        compareTwoJobsRelocationStipend1.setText(String.valueOf((int)list.get(0).getRelocationStipend()));
        compareTwoJobsRelocationStipend2.setText(String.valueOf((int)list.get(1).getRelocationStipend()));
        compareTwoJobsTrainingFund1.setText(String.valueOf((int)list.get(0).getTrainingFund()));
        compareTwoJobsTrainingFund2.setText(String.valueOf((int)list.get(1).getTrainingFund()));
    }

    public void renderCompareTwoJobsFromAddJobOffer(View view){
        if (isAddJobOfferClicked && currentJob != null){
            renderCompareTwoJobs(view, false);
        }
        else if (!isAddJobOfferClicked){
            Toast.makeText(getApplicationContext(),
                    "Please add an offer first!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(),
                    "Current job is not entered!", Toast.LENGTH_SHORT).show();
        }
    }

    public void renderCompareTwoJobsFromCompareJobs(View view){
        renderCompareTwoJobs(view, true);
    }

    public void rankJobLists(){
        rankedJobList = new ArrayList<>();
        if (currentJob != null){
            rankedJobList.add(currentJob);
        }
        if (jobOffers.size() > 0) {
            rankedJobList.addAll(jobOffers);
        }
        //descending order based on the weighted job score
        Collections.sort(rankedJobList, new Comparator<Job>() {
            @Override
            public int compare(Job job, Job otherJob) {
                double jobScore = computeJobScore(job);
                double otherJobScore = computeJobScore(otherJob);
                if (jobScore > otherJobScore) return -1;
                else if (jobScore < otherJobScore) return 1;
                else{
                    return job.getCompany().compareTo(otherJob.getCompany());
                }
            }
        });
    }

    public double computeJobScore(Job job){
        double yearlySalaryAdjusted = job.getYearlySalary() / (job.getCostOfLivingIndex()/100.0);
        double yearlyBonusAdjusted = job.getYearlyBonus() / (job.getCostOfLivingIndex()/100.0);
        double weightSum = comparisonSetting.getWeightSum();
        double jobScore = comparisonSetting.yearlySalary / weightSum * yearlySalaryAdjusted
                + comparisonSetting.yearlyBonus / weightSum * yearlyBonusAdjusted
                + comparisonSetting.retirementBenefits / weightSum * job.getRetirementBenefits() / 100.0 * yearlySalaryAdjusted
                + comparisonSetting.relocationStipend / weightSum * job.getRelocationStipend()
                + comparisonSetting.trainingFund / weightSum * job.getTrainingFund();
        return jobScore;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int pos = jobListView.getPositionForView(buttonView);
        if (pos != ListView.INVALID_POSITION){
            
            int clickedCount = clickedJobs.size();
            if (clickedCount == 2 && isChecked){
                buttonView.setChecked(false);
                Toast.makeText(getApplicationContext(),
                        "Select Two Only!", Toast.LENGTH_SHORT).show();
            }
            else{
                Job job = rankedJobList.get(pos);
                job.setSelected(isChecked);
                if (job.isSelected()){
                    clickedJobs.add(job);
                }
                if (!job.isSelected() && clickedJobs.contains(job)){
                    clickedJobs.remove(job);
                }
//                Toast.makeText(
//                        this,
//                        "Clicked on Job: " + job.getTitle() + "-" + job.getCompany() +
//                                ". State: is " + isChecked + ". Num of Clicked: " + clickedJobs.size(),
//                        Toast.LENGTH_SHORT
//                ).show();
            }

        }
    }
}