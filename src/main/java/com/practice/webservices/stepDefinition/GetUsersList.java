package com.practice.webservices.stepDefinition;



import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.json.JSONObject;

import com.practice.webservice.base.TestBase;
import com.practice.webservices.utils.JsonParsher;
import com.practice.webservices.utils.RestClients;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GetUsersList extends TestBase{
	public RestClients rc;
	String url;
	//public CloseableHttpResponse httpResponse;
	Map<String,String> 	headerToMap;
	JSONObject serverREsponse;
	
	public GetUsersList() {
		super();
		System.out.println("GetUsersList.GetUsersList()>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		 rc=new RestClients();
		 System.out.println("GetUsersList.GetUsersList()************"+rc);
	}
	
	@Given("^server detail of resreq$")
	public void connectionDetail() throws Throwable {
		url = prop.getProperty("URL");
		System.out.println("GetUsersList.server_detail_of_resreq(1)"+url);
	}
	
	@When("^you request for for user for given page \"([^\"]*)\"$")
	public void getUserByPageNumber(String queryParameter) throws Throwable {
		String uri = url+queryParameter;
		System.out.println("Generated URI="+uri);
		httpResponse=rc.getResponse(uri);	
		TestBase.setHttpResponse(httpResponse);
		//System.out.println("GetUsersList.you_request_for_for_user_for_given_page(2)"+httpResponse);
	}

	@Then("^check the server response status$")
	public void requestStatus() throws Throwable {
		System.out.println("GetUsersList.requestStatus(httpResponse)"+httpResponse);
		assertEquals(Integer.parseInt(prop.getProperty("SUCCESS_SERVER_STATUS")), rc.getStatus(getHttpResponse()));
	}

	@Then("^check all header$")
	public void getHeaders() throws Throwable {
		Header[] responseHeader=rc.getHeaders(getHttpResponse());
		headerToMap=new HashMap();
		Stream<Header> headerStream=Stream.of(responseHeader);
		headerStream.forEach(temp -> headerToMap.put(temp.getName(), temp.getValue()));	
		
		
		System.out.println("---------------------Map iteration start--------------------------------");
		for(Map.Entry<String,String> temp:headerToMap.entrySet()) {
			System.out.println(temp.getKey()+", "+temp.getValue());
		}
		System.out.println("---------------------Map iteration end--------------------------------");
		
		
	}

	@Then("^verify data from server$")
	public void verifyResponse() throws Throwable {
		serverREsponse = rc.getResponseBody(getHttpResponse());
		System.out.println("Rest API Response ="+serverREsponse);		
		// way to fetch single attribute
		String perPage=JsonParsher.getJsonbyPath(serverREsponse, "/per_page");
		System.out.println("per_page="+perPage);
		//way to fetch data from array
		String lastName=JsonParsher.getJsonbyPath(serverREsponse, "/data[0]/last_name");
		System.out.println("last_name: "+lastName);
	}

}
