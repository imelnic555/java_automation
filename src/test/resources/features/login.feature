Feature: Login Feature
  Scenario: Successful login
    Given I open the browser
    When I enter valid credentials
    Then I should see the dashboard