Feature: Exchange Rates

  Scenario: Get current USD/EUR rate - mock
    Given from currency USD
    And to currency EUR
    And mock currency data provider
    And exchange rate 0.926956
    When I ask for current exchange rate
    Then I should get 0.926956

  Scenario: Get current USD/EUR rate - Open ExchangeRate API
    Given from currency USD
    And to currency EUR
    And Open ExchangeRate currency data provider
    When I ask for current exchange rate
    Then I should get a reasonable result

  Scenario: Get current USD/EUR rate - Open ExchangeRate API
    Given from currency USD
    And to currency EUR
    And Open ExchangeRate currency data provider
    When I ask for exchange rate on 2010-06-15
    Then I should get 0.811825