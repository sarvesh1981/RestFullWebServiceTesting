package com.practice.webservices.stepDefinition;

import java.util.Map;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.json.JSONObject;

import com.practice.webservice.base.TestBase;
import com.practice.webservices.utils.RestClients;
import java.util.HashMap;

import cucumber.api.java.en.When;
public class GetUserListByPassingHeader extends TestBase{
	
	RestClients rc;
	String url;
	public CloseableHttpResponse httpResponse;
	Map<String,String> 	headerToMap;
	JSONObject serverREsponse;
	GetUsersList guObj=new GetUsersList();
	
	@When("^you request for user for given page \"([^\"]*)\" using header$")
	public void callingRestServiceUsingHeader(String url) throws Throwable {
		String uri = prop.getProperty("URL")+url;
		System.out.println("2nd file="+uri);
		System.out.println("GetUserListByPassingHeader.callingRestServiceUsingHeader()>>>>>>>>"+guObj.rc);
		Map<String,String> headerMap= new HashMap();
		
		headerMap.put("userName", "sarvesh81");
		headerMap.put("password", "1234565");
		
		httpResponse = guObj.rc.getResponse(uri, headerMap);
		TestBase.setHttpResponse(httpResponse);
		System.out.println("********************************"+httpResponse.toString());
	}

}
