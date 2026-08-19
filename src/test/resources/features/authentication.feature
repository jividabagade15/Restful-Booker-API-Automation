Feature: Authentication Token

  Scenario: Create authentication token with valid credentials
    Given valid user credentials
    When a POST request is sent to the authentication endpoint
    Then API responds with status code 200
    And the response matches the authentication schema
    And the response body includes a valid authentication token
