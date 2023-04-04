@regression
Feature: purchase the order from ecom website
I want to use this template for my feature file

Background:                            
Given I landed on the ecommerse page

   @tag1        
   Scenario Outline: positive test of submitting the order
   Given Logged in with username <name> and password <password>
   When I add product <productname> to cart
   And checkout <productname> and submit the order
   Then "THANKYOU FOR THE ORDER." message is displayed on the conformation page

Examples: 
| name            | password         | productname   |
| iqa@gmail.com   | 738218@Ggvk      | ZERA COAT 3   |
