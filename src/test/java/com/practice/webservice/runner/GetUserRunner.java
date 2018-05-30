package com.practice.webservice.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/test/java/com/practice/webservices/features"},
		glue = {"com.practice.webservices.stepDefinition"},
		tags = {"@PostUser"}
		
		)
public class GetUserRunner {

}
