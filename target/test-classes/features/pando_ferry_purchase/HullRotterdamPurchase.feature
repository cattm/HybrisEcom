@purchase
Feature: User Login and Purchase a Ticket for Larne Cairnryan Route
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
      | from              | return            | on date    | back date  | vehicle    | length | height | adults | promo code | time | ship               | back time | back ship | offer | RAC | WIFI | Club Lounge | person             | registration | voucher | card | account          | cvv |
      | Hull to Europoort | Europoort to Hull | 15/01/2018 | 20/01/2018 | motorhome  | 6      | 2      | 2      |            |      | Pride of Rotterdam |           |           | gold  | no  | no   | maybe       | Mr first passenger | FD61 VWO     | 5GBP    | visa | 4111111111111111 | 345 |
      | Europoort to Hull | Hull to Europoort | 20/10/2017 | 18/11/2017 | motorcycle |        |        | 1      |            |      | Pride of Rotterdam |           |           | gold  | no  | no   | maybe       |                    | FD61 VWO     | 5GBP    | visa | 4111111111111111 | 345 |
