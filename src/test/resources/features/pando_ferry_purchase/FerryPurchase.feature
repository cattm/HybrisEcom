Feature: User Login and Purchase a Ticket
  I will login and fill in the basic quote form
  I will then retrieve the Quote, make a purchase, check the purchase

  Background: 
    Given user has logged in:
      | username                     | pw        | greeting      |
      | marcus.catt58@googlemail.com | Password1 | Hello Richard |

  Scenario: From Quote to Purchase
    Given I have obtained a quote for:
      | from            | return          | on date    | coming back | vehicle | length | height | adults | promo code |
      | Dover to Calais | Calais to Dover | 20/08/2017 | 25/08/2017  | van     | 2      | 1.8    | 2      | EXPRESS    |
    When I select a quote:
      | time  | ship              | offer |
      | 13:55 | Spririt of France | gold  |
    And I add Extras:
      | RAC | WIFI | Club Lounge |
      | yes | no   | maybe       |
    Then I should be able to purchase a ticket using:
      | person             | vehicle  | voucher | card | account          | cvv |
      | Mr first passenger | FD61 VWO | SGBP    | visa | 4111111111111111 | 345 |
