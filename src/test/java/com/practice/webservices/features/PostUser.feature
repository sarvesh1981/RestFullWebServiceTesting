@PostUser
Feature: Post data to server

Scenario Outline: User detail need to post to server
Given user need to post to server with detail as <name> and <job>
When call the webservice with given user 
Then User should be posted to DataBase

Examples:
|name|job|
|"sarvesh"|"Automation Developer"|