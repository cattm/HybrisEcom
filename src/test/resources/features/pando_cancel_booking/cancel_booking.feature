Feature: cancel a booking based on poisition in list
  or booking id?
     Background: 
    Given user has logged in:
      | username                     | pw        | greeting      |
      | marcus.catt59@googlemail.com | Password1 | Hello Richard |
    
    
  Scenario:   
  Given I have bookings to delete
  #And I can select the required booking
  And I can select "58220845" booking
  When I hit cancel and accept the cancelation pop up
  Then I will see a confirmation page
