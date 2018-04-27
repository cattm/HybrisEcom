
Feature: User Login and Purchase a Ticket for Hull Zebrugge Route With Cabins
  I will login and fill in the basic quote form and specify a number of cabins
  I will then retrieve the Quote, select a fare class, make a purchase, check the purchase
  Extras on North sea are: WIFI/MEAL/CABIN UPGRADE/RAC
  Small people(0...1 years) and more passengers to check allocation

  Background: 
    Given user has logged in:
      | username                     | pw        | greeting      |
      | marcus.catt58@googlemail.com | Password1 | Hello Richard |
    Given payment model is:
      | card | account          | cvv |
      | visa | 4111111111111111 | 345 |

  Scenario Outline: From Quote to Purchase
    Given <adults> are able to select an outbound ferry <from> <on date> and <time> on <ship>
    And <adults> are able to <return> on <back date> using <back ship> sailing at <back time>
    And The Adults are <names>
    Given There are <other passengers> of <type>
    Given They require <cabins> for accommodation
    And require a <class> offering
    Given They have a <vehicle> with a size of <length> by <height>
    And identification <registration>
    Then if they have  <promo code> and <voucher>
    Then They will succeed

    Examples: 
      | from              | return            | on date    | back date  | vehicle   | length | height | cabins | promo code | time | ship          | back time | back ship | class | adults | names                                                       | other passengers | type | registration | voucher |
      | Hull to Europoort | Europoort to Hull | 30/05/2018 | 03/06/2018 | small_car | 0      | 0      | 1      |            |      | Pride of Hull |           |           | silver  | 4      | Mr first passenger,Miss third passenger,Mr second passenger |                  |      | LC02 TJZ     |         |
      | Hull to Europoort | Europoort to Hull | 30/05/2018 | 03/06/2018 | small_car | 0      | 0      | 1      |            |      | Pride of Hull |           |           | silver  | 4      | Mr first passenger,Miss third passenger,Mr second passenger |                  |      | LC02 TJZ     |         |
      | Hull to Europoort | Europoort to Hull | 30/05/2018 | 03/06/2018 | small_car | 0      | 0      | 1      |            |      | Pride of Hull |           |           | silver  | 4      | Mr first passenger,Miss third passenger,Mr second passenger |                  |      | LC02 TJZ     |         |
      | Hull to Europoort | Europoort to Hull | 30/05/2018 | 03/06/2018 | small_car | 0      | 0      | 1      |            |      | Pride of Hull |           |           | silver  | 4      | Mr first passenger,Miss third passenger,Mr second passenger |                  |      | LC02 TJZ     |         |
      | Hull to Europoort | Europoort to Hull | 30/05/2018 | 03/06/2018 | small_car | 0      | 0      | 1      |            |      | Pride of Hull |           |           | silver  | 4      | Mr first passenger,Miss third passenger,Mr second passenger |                  |      | LC02 TJZ     |         |
      | Hull to Europoort | Europoort to Hull | 30/05/2018 | 03/06/2018 | small_car | 0      | 0      | 1      |            |      | Pride of Hull |           |           | silver  | 4      | Mr first passenger,Miss third passenger,Mr second passenger |                  |      | LC02 TJZ     |         |
      | Hull to Europoort | Europoort to Hull | 30/05/2018 | 03/06/2018 | small_car | 0      | 0      | 1      |            |      | Pride of Hull |           |           | silver  | 4      | Mr first passenger,Miss third passenger,Mr second passenger |                  |      | LC02 TJZ     |         |
      | Hull to Europoort | Europoort to Hull | 30/05/2018 | 03/06/2018 | small_car | 0      | 0      | 1      |            |      | Pride of Hull |           |           | silver  | 4      | Mr first passenger,Miss third passenger,Mr second passenger |                  |      | LC02 TJZ     |         |
      | Hull to Europoort | Europoort to Hull | 30/05/2018 | 03/06/2018 | small_car | 0      | 0      | 1      |            |      | Pride of Hull |           |           | silver  | 4      | Mr first passenger,Miss third passenger,Mr second passenger |                  |      | LC02 TJZ     |         |
      | Hull to Europoort | Europoort to Hull | 30/05/2018 | 03/06/2018 | small_car | 0      | 0      | 1      |            |      | Pride of Hull |           |           | silver  | 4      | Mr first passenger,Miss third passenger,Mr second passenger |                  |      | LC02 TJZ     |         |
      | Hull to Europoort | Europoort to Hull | 30/05/2018 | 03/06/2018 | small_car | 0      | 0      | 1      |            |      | Pride of Hull |           |           | silver  | 4      | Mr first passenger,Miss third passenger,Mr second passenger |                  |      | LC02 TJZ     |         |
      | Hull to Europoort | Europoort to Hull | 30/05/2018 | 03/06/2018 | small_car | 0      | 0      | 1      |            |      | Pride of Hull |           |           | silver  | 4      | Mr first passenger,Miss third passenger,Mr second passenger |                  |      | LC02 TJZ     |         |
      | Hull to Europoort | Europoort to Hull | 30/05/2018 | 03/06/2018 | small_car | 0      | 0      | 1      |            |      | Pride of Hull |           |           | silver  | 4      | Mr first passenger,Miss third passenger,Mr second passenger |                  |      | LC02 TJZ     |         |
      | Hull to Europoort | Europoort to Hull | 30/05/2018 | 03/06/2018 | small_car | 0      | 0      | 1      |            |      | Pride of Hull |           |           | silver  | 4      | Mr first passenger,Miss third passenger,Mr second passenger |                  |      | LC02 TJZ     |         |
      | Hull to Europoort | Europoort to Hull | 30/05/2018 | 03/06/2018 | small_car | 0      | 0      | 1      |            |      | Pride of Hull |           |           | silver  | 4      | Mr first passenger,Miss third passenger,Mr second passenger |                  |      | LC02 TJZ     |         |
      | Hull to Europoort | Europoort to Hull | 30/05/2018 | 03/06/2018 | small_car | 0      | 0      | 1      |            |      | Pride of Hull |           |           | silver  | 4      | Mr first passenger,Miss third passenger,Mr second passenger |                  |      | LC02 TJZ     |         |
      | Hull to Europoort | Europoort to Hull | 30/05/2018 | 03/06/2018 | small_car | 0      | 0      | 1      |            |      | Pride of Hull |           |           | silver  | 4      | Mr first passenger,Miss third passenger,Mr second passenger |                  |      | LC02 TJZ     |         |
      | Hull to Europoort | Europoort to Hull | 30/05/2018 | 03/06/2018 | small_car | 0      | 0      | 1      |            |      | Pride of Hull |           |           | silver  | 4      | Mr first passenger,Miss third passenger,Mr second passenger |                  |      | LC02 TJZ     |         |
      | Hull to Europoort | Europoort to Hull | 30/05/2018 | 03/06/2018 | small_car | 0      | 0      | 1      |            |      | Pride of Hull |           |           | silver  | 4      | Mr first passenger,Miss third passenger,Mr second passenger |                  |      | LC02 TJZ     |         |
      | Hull to Europoort | Europoort to Hull | 30/05/2018 | 03/06/2018 | small_car | 0      | 0      | 1      |            |      | Pride of Hull |           |           | silver  | 4      | Mr first passenger,Miss third passenger,Mr second passenger |                  |      | LC02 TJZ     |         |
      | Hull to Europoort | Europoort to Hull | 30/05/2018 | 03/06/2018 | small_car | 0      | 0      | 1      |            |      | Pride of Hull |           |           | silver  | 4      | Mr first passenger,Miss third passenger,Mr second passenger |                  |      | LC02 TJZ     |         |
      | Hull to Europoort | Europoort to Hull | 30/05/2018 | 03/06/2018 | small_car | 0      | 0      | 1      |            |      | Pride of Hull |           |           | silver  | 4      | Mr first passenger,Miss third passenger,Mr second passenger |                  |      | LC02 TJZ     |         |
      | Hull to Europoort | Europoort to Hull | 30/05/2018 | 03/06/2018 | small_car | 0      | 0      | 1      |            |      | Pride of Hull |           |           | silver  | 4      | Mr first passenger,Miss third passenger,Mr second passenger |                  |      | LC02 TJZ     |         |
      | Hull to Europoort | Europoort to Hull | 30/05/2018 | 03/06/2018 | small_car | 0      | 0      | 1      |            |      | Pride of Hull |           |           | silver  | 4      | Mr first passenger,Miss third passenger,Mr second passenger |                  |      | LC02 TJZ     |         |
      | Hull to Europoort | Europoort to Hull | 30/05/2018 | 03/06/2018 | small_car | 0      | 0      | 1      |            |      | Pride of Hull |           |           | silver  | 4      | Mr first passenger,Miss third passenger,Mr second passenger |                  |      | LC02 TJZ     |         |
      | Hull to Europoort | Europoort to Hull | 30/05/2018 | 03/06/2018 | small_car | 0      | 0      | 1      |            |      | Pride of Hull |           |           | silver | 4      | Mr first passenger,Miss third passenger,Mr second passenger |                  |      | LC02 TJZ     |         |
      | Hull to Europoort | Europoort to Hull | 30/05/2018 | 03/06/2018 | small_car | 0      | 0      | 1      |            |      | Pride of Hull |           |           | silver  | 4      | Mr first passenger,Miss third passenger,Mr second passenger |                  |      | LC02 TJZ     |         |
