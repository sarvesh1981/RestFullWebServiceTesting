@hello
Feature: Validate all data from resreq server

Scenario Outline: get all data from reqreq server without passing header
Given server detail of resreq  
When you request for for user for given page <pageNo>
Then check the server response status
And check all header
And verify data from server

Examples:
|pageNo|
|"/api/users"|

Scenario Outline: get all data from reqreq server by passing header
Given server detail of resreq  
When you request for user for given page <pageNo> using header
Then check the server response status
And check all header
And verify data from server

Examples:
|pageNo|
|"/api/users"|  
