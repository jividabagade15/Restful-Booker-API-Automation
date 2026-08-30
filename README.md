# API Automation

An API automation framework built using Java, Rest Assured, Cucumber, TestNG, and Maven to automate end-to-end test scenarios for the Restful-Booker API.

## Tech Stack
- Java
- Rest Assured
- Cucumber
- TestNG
- Maven
- Jenkins
- Git & GitHub
- ExtentReports

## Project Status

In Progress

## Jenkins CI/CD Integration

The API automation framework is integrated with Jenkins for Continuous Integration and automated test execution.

### Jenkins Configuration
- Connected the GitHub repository to Jenkins.
- Configured Maven for build execution.
- Configured separate Smoke and Regression test execution.
- Configured Jenkins to execute the API automation test suite.
- Integrated test execution with ExtentReports for test reporting.

### Test Execution

Smoke tests can be executed using:
```bash
mvn test -Dcucumber.filter.tags="@Smoke"
```
Regression tests can be executed using:
```bash
mvn test -Dcucumber.filter.tags="@Regression"
```
Jenkins executes the selected Maven profile and generates the test execution report after completion.

### Test Reporting
- ExtentReports is used for detailed test execution reporting.
- Reports are generated after the test execution.
- Jenkins can be used to execute the tests and review the execution results.

## Source Code

The project is maintained using Git and hosted on GitHub.