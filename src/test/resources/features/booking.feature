Feature: Booking Retrieval

  Scenario: Get all the Booking Ids
    When a GET request is sent to the booking endpoint
    Then the booking API responds with status code 200
    And the response contains booking IDs
