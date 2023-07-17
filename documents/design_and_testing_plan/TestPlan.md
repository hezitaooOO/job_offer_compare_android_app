# Test Plan

**Author**: Team 138

## 1 Testing Strategy

### 1.1 Overall strategy

Unit - Unit testing will be implemented by creating test cases that cover independently testable features. 

Integration -  Integration testing will be covered by testing transitions between testable features. If the testable features can transition, integration has been achieved.

System - System testing will be a combination of all previous tests. Independable testable features will be tested with transitions and data persistence.

Regression - Regression testing will be handled the same as system testing due to the simplicity of this application. 


### 1.2 Test Selection

After alpha development, it was decided to utilize Black Box Testing and implement it by utilizing the systemic functional testing approach and a state machine. The priority of testing during beta was to ensure that we had a functioning implementation. Because of this, our test cases prioritized usability and correctness.  


### 1.3 Adequacy Criterion

The finite state machine has states and transitions; so the quality of the test cases will be critiqued by the percentage of states and transitions that are covered.
![finiteStateMachine](./images/finite-state-machine.png)


### 1.4 Bug Tracking

Due to the size of the application, bug and enhancement tracking will be handled with an excel sheet.

### 1.5 Technology

Finite state machine
Category partition method (to permute relevant inputs in our test cases)
 

## 2 Test Cases

||Purpose|Implementation steps|Expected result|Actual result|
|---|---|---|---|---|
Add current|verify a new current can be added|Select enter current job details and enter a new current job|A new current Job will be able to be filled out|A new current job was filled out
Add invalid current offer|verify that invalid job offers are handled correctly|Add an invalid Job offer<br />A: missing variables<br />B: invalid values|The invalid job offer will trigger error handling|Invalid job offer triggered error handling
Current Job persistence|verify that a current job is persisted|Add a current job offer and validate the job details are populate during an edit|The job details will be populated|Job details were populated after an edit
Current job edit|Verify that the current job can be edited|Enter Current Job and edit Current Job|The current job will be able to be edited|Current job was editable
Current job edit persistence|Verify that a job offer edit is persisted|Modify a current job and verify changes are persisted|The current job edits will be persisted|Current job edit was persisted
Current job discard|Verify that the user can exit without saving|Add a current job, exit, without saving. Verify changes weren’t persisted|Will be able to exit and job won’t be persisted|A current job was added, excitable, and not persisted
Current job edit discard|Verify that a user can exit without saving an edit|Edit the current job, exit, without saving. Verify changes weren’t persisted|Will be able to exit and job edits won’t be persisted|A current job was edited, excitable, and not persisted
Can return to main menu from Current job details|Verify that a user can return to main menu from Current Job details|Return to main menu|The user will be able to return to main menu|A user returns to the main menu by pressing the close button
Enter Job Offer|Verify that a user can enter a job offer|Select enter job offers and enter a new job offer|A new job offer will be entered|A new job offer was entered
Job Offer persistence|Verify that a user can save a job offer|Save a new job offer and verify its persistence|The job offer will be persisted|The new job offer was persisted
Add another Job Offer|Verify that a user can add an additional job offer|After creating an initial job offer add another one|An additional job offer will be made|An additional job offer was created
Job offer limit|To test the limit of offered jobs|Add multiple job offers|There should not be a realistic limit|After 18 job offers the application crashed
Compare job offer with Current Job|Verify that a recently added job offer can be compared with a saved curren job|After saving a job offer compare it with the current job offer|A recently created job offer will be compared with the current job|A recently created job offer was comparable with the current job
Handel job comparison when a current job offer does not  exist|Verify that a recently added job offer comparison doesn’t crash when current job doesn’t exist|Select job comparison when a current job has not been saved|Comparison won't be allowed|Comparison was not allowed
Exit without saving Job Offer|Verify that a user can return to main menu without saving job offer|Enter job offer and return to main menu|Edited job offer won't be persisted|Edited job offer was not persisted
Return to main many from Job offers|Verify that a user can return to the main menu from enter job offer|Return to main menu|Will be able to return to main menu|A user returns to the main menu by pressing the close button
Display Jobs in compare job offers #1|Verify that a saved current job and job offers can be displayed in compare job offers|Save job offers and validate they are displayed in compared job offers|Jobs are saved and display in the compared jobs UI|Saved current job and job offers are displayed in compare job offers
Display Jobs in compare job offers #2|Verify that a saved current job can be displayed in compare job offers|Compare jobs when only a current job exists|Compare job offers will display current job and won't allow comparison|Compare job offers were deactivated. This was implemented because logically you can't compare a single job
Display Jobs in compare job offers #3|Verify that a saved  job offer can be displayed in compare job offers|Compare jobs when only a single job offer exists|Compare job offers will display a saved job offer and won't allow comparison|Compare job offers were deactivated. This was implemented because logically you can't compare a single job
Display Jobs in compare job offers #4|Verify that a saved  job offer can be displayed in compare job offers when a current job is not saved|Compare job offers when multiple job offers exist and a current job isn’t saved|Compare job offers will display the saved job offer and will allow job comparisons|If two or more job offers have been saved they will be displayed else compare job offers is not accessible
Display Jobs by rank|Verify that displayed jobs are ranked|Add jobs with different detail values and verify they are displayed from best to worst|The jobs will display from best to worst|Jobs offers are displayed from Best to worst. Current job stays on top.
Ability to compare two Jobs|Verify that two jobs can be compared|Select jobs to compare job offers and compare them|Two jobs will be selected and compared|Two jobs can be selected for comparison
Compare different jobs after initial comparison|Verify that a user can select a different set of jobs to compare|After comparing a set of jobs select another set|A new set of jobs will be compared|A new set of jobs can be compared after initial comparison
Return to main menu from compare job offers|Verify that the user can return to the main menu from compare job offers.|Return to the main menu|Main menu will be accessible from compare jobs|A user returns to the main menu by pressing the close button
Adjust comparison settings|Verify that adjustments can be made to the comparison settings|Edit comparison settings|Comparison settings will be editable|Comparison settings are editable
Adjust comparison persistence|Verify that adjustments to the comparison settings are persisted|Edit comparison settings and verify persistence|Edited comparison settings will be persisted|Comparison settings edits are persisted
Adjusted comparison update Job rank|Verify that comparison setting adjustments are accounted for when ranking jobs|Edit comparison settings so that it changes the quality of displayed jobs|Edited comparison settings will correctly affect ranking|Edited comparison settings correctly affected ranking
Invalid comparison values|Verify that invalid comparison values are handled|Add negative and non number values|Invalid inputs will be handled accordingly|Negative integers were allowed.