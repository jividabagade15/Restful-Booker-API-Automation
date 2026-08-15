Feature: Booking Management

  Scenario: Get all booking IDs
    When a GET request is sent to the booking endpoint
    Then the booking API responds with status code 200
    And the response contains booking IDs

  Scenario: Get booking by ID
    Given a valid booking ID
    When a GET request is sent to the booking endpoint using the booking ID
    Then the booking API responds with status code 200
    And the response contains booking details

  Scenario: Create a booking
    Given a valid booking payload
    When a POST request is sent to the booking endpoint
    Then the booking API responds with status code 200
    And response contains the booking with an assigned booking ID

  Scenario: Create and retrieve a booking using the generated booking ID
    Given a valid booking payload
    When a POST request is sent to the booking endpoint
    Then the booking API responds with status code 200
    And the booking ID is captured from the response
    When a GET request is sent to the booking endpoint using the booking ID
    Then the booking API responds with status code 200
    And the response contains booking details
