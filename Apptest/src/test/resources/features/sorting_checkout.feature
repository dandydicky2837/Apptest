Feature: Sorting and Checkout

  Scenario: Sorting items from low to high price
    Given User is on homepage
    When User sorts items by price from low to high
    Then The first item price should be less than the second item price

  Scenario: Checkout
    Given User is on homepage
    When User adds 2 different items to cart
    And User proceeds to checkout
    Then User should see the success checkout page
