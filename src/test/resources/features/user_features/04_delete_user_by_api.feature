@End2End
Feature: Delete User By Api

  @Delete @Api
  Scenario: Delete Updated User
    Given set the url for delete request
    When send the delete request and get the response
    Then do assertion for delete request
    And body should be empty

