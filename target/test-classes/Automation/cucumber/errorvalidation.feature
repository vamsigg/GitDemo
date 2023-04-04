@error
Feature: errorvalidation
I want to use this template for my feature file

   
    Scenario Outline: Title of your scenario outline
    Given I landed on the ecommerse page
    When Logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed

 Examples: 
| name            | password   |
| iqa@gmail.com   | 73821@Ggvk |