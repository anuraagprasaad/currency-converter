Feature: Users

  Scenario: Registration - successful
    Given email testuser@test.com
    And password testuser    
    And dob 1990-10-15
    And street Test Street
    And city Test City
    And zip 123456    
    And country GB
    When I try to register
    Then I should be registered

  Scenario: Registration - same email twice
    Given email testuser@test.com
    And password testuser
    And dob 1990-10-15
    And street Test Street
    And city Test City
    And zip 123456
    And country GB
    When I try to register twice
    Then I should get error for field email

  Scenario: Registration - no data
    When I try to register
    Then I should get error for field email
    And I should get error for field dob
    And I should get error for field city
    And I should get error for field street
    And I should get error for field zip
    And I should get error for field password	