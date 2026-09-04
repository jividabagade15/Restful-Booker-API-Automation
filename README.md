# Restful Booker API Automation

## Project Overview
This project is an API automation framework built to automate and validate REST APIs provided by the Restful Booker application.

The framework follows a BDD approach using Cucumber and uses Rest Assured for API automation, TestNG for test execution, Maven for build management, and Jenkins for CI execution.

The framework covers positive, negative, data-driven, schema validation, API chaining, reporting, and Smoke/Regression test execution.

## Project Status
Completed Portfolio Project

The framework demonstrates an end-to-end API automation setup including test data management, reusable utilities, reporting, Maven-based execution, Cucumber tagging, and Jenkins CI integration.

## Tech Stack
- Java
- Rest Assured
- Cucumber
- TestNG
- Maven
- Jackson
- Jenkins
- Git & GitHub
- ExtentReports

## Framework Structure
APIAutomation 
│ 
├── src 
│	 ├── main 
│	 │		├── java
│	 │		│	  ├── config 
│	 │		│	  │		└── ConfigReader.java
│	 │		│	  │
│	 │		│	  ├── pojo 
│	 │		│	  │		├── Booking.java 
│	 │		│	  │		├── BookingDates.java 
│	 │		│	  │		└── UserCredentials.java 
│	 │		│	  │ 
│ 	 │		│ 	  └── utils
│	 │		│ 			├── ExtentReportNG.java 
│ 	 │		│			├── JsonDataReader.java 
│	 │		│			├── ResponseValidator.java 
│	 │		│			├── SpecBuilder.java 
│	 │		│			└── TestDataPaths.java 
│	 │		│
│ 	 │ 		└── resources
│ 	 │ 				└── config.properties 
│	 │ 
│ 	 └── test 
│		  ├── java
│		  │		├── context 
│		  │		│ 		└── TestContext.java 
│		  │		│ 
│		  │		├── Hooks 
│		  │		│		└── TestNGListener.java 
│		  │		│
│		  │		├── runners 
│		  │		│ 		└── TestRunner.java 
│		  │		│
│		  │ 	└── stepdefinitions 
│		  │				 ├── AuthenticationSteps.java 
│		  │				 └── BookingSteps.java 
│		  │	
│ 		  └── resources 
│					 ├── features 
│					 ├── schemas 
│					 └── testdata 
│
├── postman
├── testng.xml 
├── pom.xml 
├── .gitignore 
└── README.md

## Framework Flow
Feature Files
	↓ 
Cucumber Step Definitions
	↓ 
Rest Assured API Requests
	↓ 
Reusable Request Specification
	↓ 
API Response
	↓ 
Response Validation
	↓ 
TestNG Assertions
	↓ 
ExtentReports

## API Operations Covered
- Authentication
- Get all booking IDs
- Get booking by ID
- Create booking
- Update booking
- Delete booking

## Test Scenarios Covered

### Positive Scenarios
- Generate authentication token using valid credentials
- Retrieve all booking IDs
- Retrieve booking details using a valid booking ID
- Create a new booking
- Capture dynamically generated booking ID
- Update an existing booking
- Delete an existing booking
- Validate booking response data

### Negative Scenarios
- Retrieve booking using an invalid booking ID
- Authenticate using invalid credentials
- Create booking using an invalid request body
- Update booking without authentication

### Additional Validations
- HTTP status code validation
- Response field validation
- JSON schema validation
- API response data validation
- Dynamic booking ID capture
- API chaining using generated booking IDs

## Test Data management
Test data is externalized into JSON files instead of hardcoding request payloads in the step definitions.

src/test/resources/testdata/
│
├── bookingData.json
├── updatedBookingData.json
├── invalidBookingData.json
└── authenticationData.json

Jackson is used to deserialize JSON test data into Java POJO classes.

The framework uses classpath-based resource loading, making test-data access platform independent and suitable for CI environments such as Jenkins.

## Reusable Components

### SpecBuilder
Provides a reusable Rest Assured request specification containing common configuration such as:

- Base URI
- Content type

### ConfigReader
Loads API configuration from:

- src/main/resources/config.properties

### JsonDataReader
Provides reusable JSON test-data loading using Jackson.

### ResponseValidator
Provides reusable validation methods for:

- Status codes
- Non-null response fields
- Non-blank response fields
- Expected response field values

### TestContext
Stores runtime authentication data such as the generated authentication token and makes it available between related Cucumber steps.

## Smoke and Regression Testing
The framework supports separate Smoke and Regression execution using Cucumber tags and Maven profiles.

- Smoke Tests
	Smoke tests cover the critical API functionality required to verify that the application is working correctly.

Run Smoke tests using:
```bash
mvn clean test -PSmoke
```

- Regression Tests
	Regression tests include the Smoke scenarios along with additional negative, validation, schema, and data-driven scenarios.

Run Regression tests using:
```bash
mvn clean test -PRegression
```
## Test Reporting
The framework uses ExtentReports to generate execution reports.

The reports provide information such as:

- Scenario name
- Test execution status
- API execution details
- Passed/failed scenarios

## Jenkins CI Integration
The project is integrated with Jenkins for Continuous Integration.

Jenkins can execute the Maven test profiles directly.

- Smoke execution
```bash
mvn clean test -PSmoke
```

Regression execution
```bash
mvn clean test -PRegression
```
This allows the API automation suite to be executed automatically as part of a CI job.

## How to run the project Locally
Prerequisites
	Make sure the following are installed:

- Java JDK
- Maven
- Git
- IDE such as Eclipse or IntelliJ IDEA

Clone the repository
```bash
	git clone https://github.com/jividabagade15/Restful-Booker-API-Automation
```

Navigate to the project
```bash
	cd APIAutomation
```

Run Smoke tests
```bash
	mvn clean test -PSmoke
```

Run Regression tests
```bash
	mvn clean test -PRegression
```

## Test execution
The project uses:

- Cucumber for BDD scenario management
- TestNG as the execution framework
- Maven Surefire Plugin for Maven-based test execution
- Maven profiles for Smoke and Regression execution

The TestNG suite is configured through:
	testng.xml

## Key Framework features
- REST API automation using Rest Assured
- BDD implementation using Cucumber
- TestNG integration
- Reusable request specification
- POJO-based request payloads
- Externalized JSON test data
- Classpath-based resource loading
- Reusable response validation
- API chaining using dynamic booking IDs
- Authentication token management
- JSON schema validation
- Negative API testing
- Data-driven authentication testing
- Smoke and Regression test execution
- ExtentReports integration
- Jenkins CI integration
- Git/GitHub version control
- Cross-platform resource handling

## Author
Jivida Bagade

QA Tester | Java | Selenium | Rest Assured | API Automation

Currently transitioning from Manual Testing to QA Automation, with hands-on experience building automation frameworks using Java, Selenium, Rest Assured, Cucumber, TestNG, Maven, and Jenkins.