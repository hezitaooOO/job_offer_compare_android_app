# Design Document

**Author**: Team 138

## 1 Design Considerations

### 1.1 Assumptions

The application will be a  single system running app thus SQLite will be used and store the data on the users device

### 1.2 Constraints

No constraint on the system has a significant impact on the design of the system. 

### 1.3 System Environment

Minimum SDK of API 30: Android 11.0

## 2 Architectural Design

### 2.1 Component Diagram

For the implementation of the job comparison application, three components are used. A component for job comparisons, UI, and persistence.
![componentDiagram](./images/component-diagram.png)

### 2.2 Deployment Diagram

Because the application is designed to run on a single system the deployment diagram would only consist of a device, execution environment, and standard android artifacts. This would not deliver any useful information for our development thus making a deployment diagram unnecessary to create. 

## 3 Low-Level Design

### 3.1 Class Diagram

![classDiagram](./images/class-diagram.png)

### 3.2 Other Diagrams

![sequenceDiagram](./images/sequence_diagram.png)

## 4 User Interface Design

![mainMenu](./images/main-menu.png)
![editCurrentJob](./images/edit-current-job.png)
![adjustComparisonSettings](./images/adjust-comparison-settings.png)
![jobOffersList](./images/job-offer-lists.png)
![addNewJobOffer](./images/add-job-offer.png)
![compareJobs](./images/compare-jobs.png)
![compareTwoJobs](./images/compare-two-jobs.png)