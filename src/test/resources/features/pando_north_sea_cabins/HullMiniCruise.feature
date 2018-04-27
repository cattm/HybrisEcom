@minicruise
Feature: User Login and Purchase a Ticket for Hull Zebrugge Route With Cabins
  I will login, Select MiniCruise and select Where, When, Who and Cabins
  I will then retrieve the Quote, select a fare class, make a purchase, check the purchase

  Background: 
    Given user has logged in:
      | username                     | pw        | greeting      |
      | marcus.catt58@googlemail.com | Password1 | Hello Richard |
    Given payment details are:
      | card   | account | cvv |
      | paypal |         |     |

  Scenario Outline: Book a MiniCruise
    Given <adults> are able to select a minicruise <from> <on date> <to>
    And they are using a <vehicle> of <length> and <height> identified by <registration>
    And <cabins> are required for <passengers> of <type> and <class>
    Given they have <promo code> and <voucher> valid for passenger details <person>
    Then They Will Book a MiniCruise

    Examples: 
      | from | to              | on date    | vehicle   | length | height | adults | cabins | class  | promo code | person             | passengers | type | registration | voucher |
      | Hull | RotterdamCentre |13/05/2018 | novehicle |        |        | 2      | 1      | bronze |            | Mr first passenger |            |      |              |         |
      | Hull | RotterdamCentre |13/05/2018 | novehicle |        |        | 2      | 1      | bronze |            | Mr first passenger |            |      |              |         |
      | Hull | RotterdamCentre |13/05/2018 | novehicle |        |        | 2      | 1      | bronze |            | Mr first passenger |            |      |              |         |
      | Hull | RotterdamCentre |13/05/2018 | novehicle |        |        | 2      | 1      | bronze |            | Mr first passenger |            |      |              |         |
      | Hull | RotterdamCentre |13/05/2018 | novehicle |        |        | 2      | 1      | bronze |            | Mr first passenger |            |      |              |         |
      | Hull | RotterdamCentre |13/05/2018 | novehicle |        |        | 2      | 1      | bronze |            | Mr first passenger |            |      |              |         |
      | Hull | RotterdamCentre |13/05/2018 | novehicle |        |        | 2      | 1      | bronze |            | Mr first passenger |            |      |              |         |
      | Hull | RotterdamCentre |13/05/2018 | novehicle |        |        | 2      | 1      | bronze |            | Mr first passenger |            |      |              |         |
      | Hull | RotterdamCentre |13/05/2018 | novehicle |        |        | 2      | 1      | bronze |            | Mr first passenger |            |      |              |         |
      | Hull | RotterdamCentre |13/05/2018 | novehicle |        |        | 2      | 1      | bronze |            | Mr first passenger |            |      |              |         |
      | Hull | RotterdamCentre |13/05/2018 | novehicle |        |        | 2      | 1      | bronze |            | Mr first passenger |            |      |              |         |
      | Hull | RotterdamCentre |13/05/2018 | novehicle |        |        | 2      | 1      | bronze |            | Mr first passenger |            |      |              |         |
      | Hull | RotterdamCentre |13/05/2018 | novehicle |        |        | 2      | 1      | bronze |            | Mr first passenger |            |      |              |         |
      | Hull | RotterdamCentre |13/05/2018 | novehicle |        |        | 2      | 1      | bronze |            | Mr first passenger |            |      |              |         |
      | Hull | RotterdamCentre |13/05/2018 | novehicle |        |        | 2      | 1      | bronze |            | Mr first passenger |            |      |              |         |
      | Hull | RotterdamCentre |13/05/2018 | novehicle |        |        | 2      | 1      | bronze |            | Mr first passenger |            |      |              |         |
