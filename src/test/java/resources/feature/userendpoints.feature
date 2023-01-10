Feature: Testing different request on the gorest application

  Scenario: Check if the gorest application can be accessed by User
    When User sends a GET request to userID endpoint
    Then User must get back a valid status code 200

  Scenario: verify if user is created in gorest application
    When I create new user by providing the information
    Then I must get a valid status code 201

  Scenario: verify if user is updated in gorest application
    When I update new user by providing the information
    Then I must get back a valid status code 200

  Scenario: verify if user is Deleted in gorest application
    When I delete a DELETE request by providing information
    Then I verify that the status code 204