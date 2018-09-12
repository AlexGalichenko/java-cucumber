Feature: Test Scenario

  Background: User should be able to login
    Given User logins

  Scenario: Passed Test
    When User types "Empl" text to "Search" element
    Then Text of "Footer > Copyright Label" element should be equal to "10"