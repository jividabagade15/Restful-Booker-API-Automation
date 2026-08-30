Feature: Negative Scenarios

  @Regression
  Scenario: Get booking using an invalid booking ID
    Given an invalid booking ID
    When a GET request is sent to the booking endpoint using the booking ID
    Then the booking API responds with status code 404

  @Regression
  Scenario Outline: Create token using invalid credentials
    Given request payload contains "<username>" and "<password>"
    When a POST request is sent to the authentication endpoint
    Then API responds with status code 200
    And the response indicates authentication failure

    Examples:
      | username   | password      |
      | admin      | wrongpassword |
      | wrounguser | passwordwrong |

  @Regression
  Scenario: Create booking with invalid request body
    Given an invalid body payload
    When a POST request is sent to the booking endpoint
    Then the booking API responds with status code 500

  @Regression
  Scenario: Update the existing booking data without authorization
    Given a valid booking payload
    When a POST request is sent to the booking endpoint
    Then the booking API responds with status code 200
    And the booking ID is captured from the response
    Given an updated booking payload
    When a PUT request is sent to the booking endpoint without authentication using the booking ID
    Then the booking API responds with status code 403
