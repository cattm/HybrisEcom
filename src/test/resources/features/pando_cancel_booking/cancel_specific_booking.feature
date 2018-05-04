Feature: cancel a booking based on booking id
     Background: 
    Given user has logged in:
      | username                     | pw        | greeting      |
      | marcus.catt59@googlemail.com | Password1 | Hello Richard |
    
    
  Scenario:   
  Given I have bookings to delete
  And I can select "58220865" booking
  When I hit cancel and accept the cancelation pop up
  Then I will see a confirmation page
