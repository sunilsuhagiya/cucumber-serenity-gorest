Feature: User Application

  As a user I want to test User Application

  Scenario Outline: Crud Test
    Given Gorest application is running
    When When I create a new user by providing the information name "<name>" email "<email>" gender "<gender>" status "<status>"token"<token>"
    Then I verify that the user with "<name>" is created
    And I update the user with information name "<name>" email "<email>" gender "<gender>" status "<status>"
    And The user is updated successfully
    And I delete the user with userId
    Then The user deleted successfully from the application
    Examples:
      | name  | email            | gender | status |
      | Hello | Hello1@gmail.com | Male   | Active |


