Feature: Login

  Scenario: Successfully login
    Given User is on login page
    When User enters valid username and password
    And User clicks on login button
    Then User should be redirected to homepage
    And User should see the items listed

  Scenario: Failed login with wrong password
    Given User is on login page
    When User enters invalid username or password
    And User clicks on login button
    Then Error message "Username and password do not match any user in this service" should be displayed
