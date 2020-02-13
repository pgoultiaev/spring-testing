Feature: Homepage greeting

  As a user
  I want to be greeted
  So that I feel happy

  Scenario: Should greet user
    Given My last name is world
    When I visit the hello page
    Then I should see hello World