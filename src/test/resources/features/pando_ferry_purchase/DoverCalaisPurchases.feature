Feature: User Login and Purchase a Ticket for Dover Calais Route
  I will login and fill in the basic quote form
  I will then retrieve the Quote, make a purchase, check the purchase

  Background: 
    Given user has logged in:
      | username                     | pw        | greeting      |
      | marcus.catt58@googlemail.com | Password1 | Hello Richard |

  Scenario Outline: From Quote to Purchase
    Given <adults> are able to select an outbound ferry <from> <on date> with a <vehicle> of <length> and <height> with a <ship> sailing <time> and <offer>
    And They are <return> on <coming back>
    And Have <promo code> and passenger details <person> and car details <registration> and <voucher>
    And Have Added Extras <RAC> <WIFI> <Club Lounge>
    And Have Card Details <card> <account> <cvv>
    Then My Purchase will succeed

    Examples: 
      | from            | return          | on date    | coming back | vehicle    | length | height | adults | promo code | time  | ship              | offer | RAC | WIFI | Club Lounge | person             | registration | voucher | card | account          | cvv |
      | Dover to Calais | Calais to Dover | 20/08/2017 | 25/08/2017  | van        | 2      | 1.8    | 2      | EXPRESS    | 13:55 | Spririt of France | gold  | yes | no   | maybe       | Mr first passenger | FD61 VWO     | SGBP    | visa | 4111111111111111 | 345 |
      | Dover to Calais | Calais to Dover | 21/08/2017 | 26/08/2017  | motorcycle |        |        | 1      |            | 13:55 | Spririt of France | gold  | yes | no   | maybe       |                    | FD61 VWO     | SGBP    | visa | 4111111111111111 | 345 |