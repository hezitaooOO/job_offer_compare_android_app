# Use Case Model

**Author**: Team 138

## 1 Use Case Diagram

![usecase-diagram](./images/usecase-diagram.png)

## 2 Use Case Descriptions

**Enter or edit current job details**

Requirements: Must allow the user to enter current job details if it’s the first time or edit them if already have an existing one. Must allow users either to save the job details or to cancel and exit without saving, returning in both cases to the main menu.

Pre-conditions: The user should be on the main menu and choose to enter or edit current job details.

Post-conditions: At the end of the use case, the job details should be saved to the database without throwing any error if the user chooses to save the job details and return to the main menu. If the user chooses to cancel without saving, the interface should return to the main menu directly without saving anything into the database.

Scenarios: 
1. The User enters current job details if it’s the first time. If there is a current job existing, users edit the current job details.
2. System identifies the data type of job details. 
3. If data types of job details are correct and the user chooses to save the job details, data will be saved to the database and the app returns to the main menu.
4. If the user chooses to cancel and exit without saving, the app returns to the main menu.

**Enter job offers**

Requirements: Must allow the user to enter all the details of the  job offer. Must allow the user to either save the job offer detail or cancel. The user should be able to enter another job offer, return to the main menu or compare the job offer (the saved one) with the current job details if present.

Pre-conditions: The user should be on the main menu and choose to enter job offers.

Post-conditions: At the end of the use case, if the user chooses to save the job offer details, the job offer details should be saved to the database without throwing an error. If the user chooses to cancel, no data will be saved to the database. Under both cases, the user will always be allowed to enter another job offer, or return to the main menu. If the user chooses to save this offer and there is an existing current job, this user could have another choice to compare this job offer with the current job.

Scenarios: 
1. The user enters job offer details.
2. System identifies the data type of job details.
3. If the data type of job details are correct and the user chooses to save the job offer details, data will be saved to the database. The user will be always allowed to enter another job offer, and return to the main menu. If there is an existing current job, this user could have another choice to compare this job offer with the current job.
4. If the user chooses to cancel, the user will be allowed to enter another job offer, or return to the main menu.

**Adjust the comparison settings**

Requirements: Must allow the user to adjust the comparison settings. If no weights are assigned, all factors are considered equal.

Pre-conditions: The user should be on the main menu and choose to adjust the comparison settings.

Post-conditions: The comparison settings should be saved to the database without throwing an error.

Scenarios: 
1. The user enters the weights of comparison factors. 
2. System identifies the data type of comparison factors and saves to the database
3. If the user does not assign any weights for comparison factors, all factors considered equal which is 1.

**Compare job offers**

Requirements: Must allow the user to select two jobs to compare and trigger the comparison. The user should also be able to perform another comparison or go back to the main menu.

Pre-conditions: The user should be on the main menu and choose to compare job offers.

Post-conditions: At the end of the use case, as long as the user chooses any of two jobs to compare, the comparison should be triggered with a table comparing the two selected jobs and displaying the detail of each job.

Scenarios:
1. The list of jobs are displayed as Title and Company, ranked from best to worst computed as the weighted sum of AYS + AYB + (RBP * AYS / 100) + RS + TDF and clearly indicates the current job if present.

    AYS = yearly salary adjusted for cost of living

    AYB = yearly bonus adjusted for cost of living

    RBP = retirement benefits percentage

    RS = relocation stipend

    TDF = training and development fund

2. The user selects two jobs to compare and triggers the comparison.
3. Two comparison jobs will be shown in a table with details.
