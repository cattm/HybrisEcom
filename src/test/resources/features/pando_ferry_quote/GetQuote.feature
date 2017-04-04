Feature: Login to P and O Web Site
  In order to access the booking features
  As an English customer
  I want to log in to the English version web site

  Background: 
    Given user has logged in

  Scenario: Get a Quote
    Given the user has selected:
      | from  | return | on date    | coming back | vehicle | length | height | adults |
      | Dover to Calais | Calais to Dover | 20/04/2017 | 21/04/2017  | van     | 2      | 1.8    | 2      |
    When the user asks for a quote
    Then they should see a quote page
