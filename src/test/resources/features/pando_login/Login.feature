Feature: Login to P and O Web Site
  In order to access the booking features
  As an English customer
  I want to log in to the English version web site

  Scenario: Log in to UK web site
    Given the user is on the Home page
    When the user attempts to login
    Then they should see a message "Hello marcus.catt58@googlemail...."
