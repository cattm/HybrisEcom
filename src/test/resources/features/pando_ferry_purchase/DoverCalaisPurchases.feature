@purchase
Feature: User Login and Purchase a Ticket for Dover Calais Route
  I will login and fill in the basic quote form
  I will then retrieve the Quote, make a purchase, check the purchase

  Background: 
    Given user has logged in:
      | username                     | pw        | greeting      |
      | marcus.catt58@googlemail.com | Password1 | Hello Richard |

  Scenario Outline: From Quote to Purchase
    Given <adults> are able to select an outbound ferry <from> <on date> with a <vehicle> of <length> and <height> with a <ship> sailing <time> and <offer>
    And They are <return> on <back date> using <back ship> sailing at <back time>
    And Have <promo code> and passenger details <person> and car details <registration> and <voucher>
    And Have Added Extras <RAC> <WIFI> <Club Lounge>
    And Have Card Details <card> <account> <cvv>
    Then My Purchase will succeed

    Examples: 
      | from            | return          | on date    | back date  | vehicle    | length | height | adults | promo code | time  | ship              | back time | back ship | offer  | RAC | WIFI | Club Lounge | person             | registration | voucher | card   | account                   | cvv       |
      | Dover to Calais | Calais to Dover | 20/06/2018 | 28/07/2018 | van        | 2      | 1.8    | 2      | EXPRESS    | 09:25 | Spririt of France |           |           | bronze | yes | no   | maybe       | Mr first passenger | FD61 VWO     | SGBP    | paypal | marcus.catt@poferries.com | Password1 |
      | Dover to Calais | Calais to Dover | 23/05/2018 | 24/06/2018 | motorcycle |        |        | 1      |            | 09:25 | Spririt of France |           |           | bronze | yes | no   | maybe       |                    | FD61 VWO     | SGBP    | visa   | 4111111111111111          | 345       |
