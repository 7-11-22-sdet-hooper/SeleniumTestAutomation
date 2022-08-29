@tag
Feature: Display cards_by_catagory navagation bar functionality

  @tag1
  Scenario: Cards from the correct catagory are displayed
    Given Open Chrome and navigate to the Review site
    When Clicking a button from the card catagory navegation bar
    Then Only cards from that category should be displayed