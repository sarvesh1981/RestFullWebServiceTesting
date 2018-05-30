package com.practice.webservices.stepDefinition;

import com.practice.webservice.base.TestBase;
import com.practice.webservice.data.Users;
import com.practice.webservices.utils.JsonParsher;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

import java.util.Map;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;


public class SaveUserDetails extends TestBase{
 
	String JsonString;
	String uri;
	CloseableHttpResponse httpResponse;
	
	public SaveUserDetails() {
		super();
		
	}
	@Given("^user need to post to server with detail as \"([^\"]*)\" and \"([^\"]*)\"$")
	public void getUserinJSONString(String name, String job) throws Throwable { 
		String jsonFilePath="C:\\Users\\sarvsinh\\git\\RestFullWebServiceTesting\\WSRestfullTesting\\src\\main\\java\\com\\practice\\webservice\\data\\Users.json";
		Users user=new Users(name,job);
		JsonString=JsonParsher.getUserInJsonFormat(user, jsonFilePath);
	}

	@When("^call the webservice with given user$")
	public void callWebServiceswithUserInfo() throws Throwable {
		uri = prop.getProperty("URL")+"/api/users";
		
		Map<String,String> headerMap=new HashMap();
		headerMap.put("Content-Type", "application/json");
		
		
		httpResponse = rc.saveUserData(uri, headerMap, JsonString);
	}

	@Then("^User should be posted to DataBase$")
	public void user_should_be_posted_to_DataBase() throws Throwable {
		System.out.println("Status code of post req ="+httpResponse.getStatusLine().getStatusCode());
		assertEquals("201", new Integer(httpResponse.getStatusLine().getStatusCode()).toString());
		
		String responseString=EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
		
		JSONObject jsonObject=new JSONObject(responseString);
		System.out.println("jsonObject= "+jsonObject);
		
		//JSONObject to Java Object
		Users userData=(Users) JsonParsher.JsonToJavaObj(responseString, Users.class);
		System.out.println(userData.getName()+", "+userData.getJob());
		
	}
}

