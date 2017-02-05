Feature: Login
  
  Scenario: Login - successful
    Given email testuser@test.com
    And password testuser
    When I try to login
    Then I should be logged in

  Scenario: Login - wrong password
    Given email testuser@test.com
    And password testuser1234
    When I try to login
    Then I should fail to log in