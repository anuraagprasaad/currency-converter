@txn
Feature: User Registration and Login

  Scenario: Registration - successful
    Given email testuser@test.com
    And password testuser1234   
    And dob 1990-10-15
    And street Test Street
    And city Test City
    And zip 123456    
    And country GB
    When I try to register
    Then I should be registered
 
  Scenario: Login - Login failure wrong password entered
    Given email testuser@test.com
    And password testuser
    When I try to login
    Then I should fail to log in
