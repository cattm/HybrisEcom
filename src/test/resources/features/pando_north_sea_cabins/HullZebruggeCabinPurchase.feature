Feature: User Login and Purchase a Ticket for Hull Zebrugge Route With Cabins
  I will login and fill in the basic quote form and specify a number of cabins
  I will then retrieve the Quote, select a fare class, make a purchase, check the purchase
  Extras on North sea are: WIFI/MEAL/CABIN UPGRADE/RAC

  Background: 
    Given user has logged in:
      | username                     | pw        | greeting      |
      | marcus.catt58@googlemail.com | Password1 | Hello Richard |

  Scenario Outline: From Quote to Purchase
    Given <adults> are able to select an outbound ferry <from> <on date> with a <vehicle> of <length> and <height> with a <ship> sailing <time> and <offer>
    And They have selected <cabins>
    And They are <return> on <back date> using <back ship> sailing at <back time>
    And Have <promo code> and passenger details <person> and car details <registration> and <voucher>
    And Have Card Details <card> <account> <cvv>
    Then My Purchase will succeed

    Examples: 
      | from              | return            | on date    | back date  | vehicle   | length | height | adults | cabins | promo code | time | ship               | back time | back ship | offer | person             | registration | voucher | card | account          | cvv |
      | Hull to Europoort | Europoort to Hull | 25/05/2018 | 26/05/2018 | small_car |        |        | 2      | 1      |            |      | Pride of Rotterdam |           |           | gold  | Mr first passenger | LC02 TJZ     |         | visa | 4111111111111111 | 345 |
      | Hull to Europoort | Europoort to Hull | 25/05/2018 | 26/05/2018 | small_car |        |        | 2      | 1      |            |      | Pride of Rotterdam |           |           | gold  | Mr first passenger | LC02 TJZ     |         | visa | 4111111111111111 | 345 |
      | Hull to Europoort | Europoort to Hull | 25/05/2018 | 26/05/2018 | small_car |        |        | 2      | 1      |            |      | Pride of Rotterdam |           |           | gold  | Mr first passenger | LC02 TJZ     |         | visa | 4111111111111111 | 345 |
      | Hull to Europoort | Europoort to Hull | 25/05/2018 | 26/05/2018 | small_car |        |        | 2      | 1      |            |      | Pride of Rotterdam |           |           | gold  | Mr first passenger | LC02 TJZ     |         | visa | 4111111111111111 | 345 |
      | Hull to Europoort | Europoort to Hull | 25/05/2018 | 26/05/2018 | small_car |        |        | 2      | 1      |            |      | Pride of Rotterdam |           |           | gold  | Mr first passenger | LC02 TJZ     |         | visa | 4111111111111111 | 345 |
      | Hull to Europoort | Europoort to Hull | 25/05/2018 | 26/05/2018 | small_car |        |        | 2      | 1      |            |      | Pride of Rotterdam |           |           | gold  | Mr first passenger | LC02 TJZ     |         | visa | 4111111111111111 | 345 |
      | Hull to Europoort | Europoort to Hull | 25/05/2018 | 26/05/2018 | small_car |        |        | 2      | 1      |            |      | Pride of Rotterdam |           |           | gold  | Mr first passenger | LC02 TJZ     |         | visa | 4111111111111111 | 345 |
      | Hull to Europoort | Europoort to Hull | 25/05/2018 | 26/05/2018 | small_car |        |        | 2      | 1      |            |      | Pride of Rotterdam |           |           | gold  | Mr first passenger | LC02 TJZ     |         | visa | 4111111111111111 | 345 |
      | Hull to Europoort | Europoort to Hull | 25/05/2018 | 26/05/2018 | small_car |        |        | 2      | 1      |            |      | Pride of Rotterdam |           |           | gold  | Mr first passenger | LC02 TJZ     |         | visa | 4111111111111111 | 345 |
      | Hull to Europoort | Europoort to Hull | 25/05/2018 | 26/05/2018 | small_car |        |        | 2      | 1      |            |      | Pride of Rotterdam |           |           | gold  | Mr first passenger | LC02 TJZ     |         | visa | 4111111111111111 | 345 |
      | Hull to Europoort | Europoort to Hull | 25/05/2018 | 26/05/2018 | small_car |        |        | 2      | 1      |            |      | Pride of Rotterdam |           |           | gold  | Mr first passenger | LC02 TJZ     |         | visa | 4111111111111111 | 345 |
      | Hull to Europoort | Europoort to Hull | 25/05/2018 | 26/05/2018 | small_car |        |        | 2      | 1      |            |      | Pride of Rotterdam |           |           | gold  | Mr first passenger | LC02 TJZ     |         | visa | 4111111111111111 | 345 |
      | Hull to Europoort | Europoort to Hull | 25/05/2018 | 26/05/2018 | small_car |        |        | 2      | 1      |            |      | Pride of Rotterdam |           |           | gold  | Mr first passenger | LC02 TJZ     |         | visa | 4111111111111111 | 345 |
      | Hull to Europoort | Europoort to Hull | 25/05/2018 | 26/05/2018 | small_car |        |        | 2      | 1      |            |      | Pride of Rotterdam |           |           | gold  | Mr first passenger | LC02 TJZ     |         | visa | 4111111111111111 | 345 |
      | Hull to Europoort | Europoort to Hull | 25/05/2018 | 26/05/2018 | small_car |        |        | 2      | 1      |            |      | Pride of Rotterdam |           |           | gold  | Mr first passenger | LC02 TJZ     |         | visa | 4111111111111111 | 345 |
