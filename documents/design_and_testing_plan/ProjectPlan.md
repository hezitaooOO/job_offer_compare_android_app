# Project Plan

**Author**: Team 138

## 1 Introduction

This is a simple, single-user job offer comparison app which can allow users to compare job offers with their benefits beyond salary.


## 2 Process Description

**Enter or edit current job details:**
- Activity name: enter or edit current job details
- Activity description: When choosing to enter current job details in the main menu, a user should see a user interface allowing them to enter current job details if it’s the first time or edit them if already having an existing one. The user will be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.
- Entrance criteria:  Title, Company, Location (state and city), Cost of living in the location, Yearly salary, Yearly bonus, Retirement benefits (as percentage matched, integer from 0 to 100 inclusive), Relocation stipend, training and development fund ($0 to $18000 inclusive annually)
- Exit Criteria: successfully save the data to the database without throwing any error and return to the main menu if choosing to save the job details. Return to the main menu if choosing cancel and exit without saving.

**Enter job offers:**
- Activity name: enter job offers
- Activity description: When choosing to enter job offers, a user will be shown a user interface to enter all the details of the offer. The user will be able to either save the job offer details or cancel. The user should also be able to enter another job offer, return to the main menu, or compare the job offer which is saved just now with the existing current job details.
- Entrance criteria:  Title, Company, Location (state and city), Cost of living in the location, Yearly salary, Yearly bonus, Retirement benefits (as percentage matched, integer from 0 to 100 inclusive), Relocation stipend, training and development fund ($0 to $18000 inclusive annually).
- Exit Criteria: successfully saved to the database without throwing any error. Do nothing if the user chooses to cancel.

**Adjust the comparison settings:**
- Activity name: adjust the comparison settings
- Activity description: When choosing to adjust the comparison settings, the user can assign integer weight to Yearly salary, Yearly bonus, Retirement benefits, Relocation stipend and Training and development fund. If no weights are assigned, all factors should be 1 which means all factors should be equally weighted.
- Entrance criteria: Weight of Yearly salary, weight of Yearly bonus, weight of Retirement benefits, weight of Relocation stipend and weight of Training and development fund.
- Exit Criteria: successfully saved to the database without throwing any error.

**Compare job offers:**
- Activity name: compare job offers
- Activity description: When choosing to compare job offers, a user will be shown a list of job offers, displayed as Title and Company, ranked from the best to worst based on job’s score which is computed as the weighted sum of : AYS + AYB + (RBP * AYS / 100) + RS + TDF.
AYS = yearly salary adjusted for cost of living 
AYB = yearly bonus adjusted for cost of living 
RBP = retirement benefits percentage
RS = relocation stipend
TDF = training and development fund
Weight is set either by adjusting the comparison settings or with initial value 1 for each factor.
If there is a current job existing, it should be shown as well with clear indication.
Users will be able to select two jobs to compare and trigger the comparison. The comparison table should display details for each job. Besides Title, Company, Location, Retirement benefits, Relocation stipend, and Training and development fund, both Yearly salary and Yearly bonus should be adjusted by cost of living. Users should also be offered to perform another comparison or go back to the main menu.
- Entrance criteria: The user clicks the button to `compare job offers` and choose two jobs from the ranked job list to do the comparison.
- Exit Criteria: successfully display the table of the compared jobs’ Title, Company, Location, Yearly salary adjusted for cost of living, Yearly bonus adjusted for cost of living, Retirement benefits, Relocation stipend, and Training and development fund. Return to the main menu if the user chooses to click `go back to the main menu`.


## 3 Team

**Team members’ names:**
- Carlos Emilio Barrera
- Zitao He
- Zhenghao Hou
- Ying Qian

**Roles**
- Project Manager: planning and allocating resources, monitoring progress, and keeping stakeholders informed throughout the job offer comparison app project lifecycle.
- UX/UI Engineer: dealing with the front end of this job offer comparison app.
- Full Stack Developer: working on both front-end and back-end of this job offer comparison app.
- QA Tester: testing and evaluating this job offer comparison app to identify and help remove bugs, glitches, and other user experience issues.

|Team Member|Role|
|---|---|
Carlos Emilio Barrera|QA Tester
Zitao He|Full Stack Developer
Zhenghao Hou|Full Stack Developer, UX/UI Engineer
Ying Qian|Project Manager, UX/UI Engineer
