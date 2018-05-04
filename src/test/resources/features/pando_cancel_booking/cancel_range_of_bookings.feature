
Feature: cancel a range of bookings based on booking id

  Background: 
    Given user has logged in:
      | username                     | pw        | greeting      |
      | marcus.catt59@googlemail.com | Password1 | Hello Richard |

  Scenario Outline: 
    Given I have bookings to delete
    And I select from <bookings>
    When I hit cancel and accept the cancelation pop up
    Then I will see a confirmation page

    Examples: 
      | bookings |
      | 58220864 |
      | 58220855 |
