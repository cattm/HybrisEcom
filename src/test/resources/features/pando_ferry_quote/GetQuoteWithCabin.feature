@quote
Feature: Get A Quote With Cabin
  To get a Quote for a ferry
  I will login and fill in the basic quote form

  Background: 
    Given user has logged in:
      | username                     | pw        | greeting      |
      | marcus.catt58@googlemail.com | Password1 | Hello Richard |

  Scenario: Get a Quote With Cabin
    Given the user has selected:
      | from             | return           | on date    | coming back | vehicle | length | height | adults | cabins | promo code |
      | Hull to Zeebrugge | Zeebrugge to Hull | 20/02/2018 | 21/02/2018  | van     | 2      | 1.8    | 2      | 2      |            |
    When the user asks for a quote
    Then they should see a quote page:
      | greeting                           |
      | PICK YOUR CROSSING AND TICKET TYPE |
