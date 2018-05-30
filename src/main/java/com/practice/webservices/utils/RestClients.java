package com.practice.webservices.utils;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import org.apache.http.Header;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClients {

	CloseableHttpResponse httpResponse;
	CloseableHttpClient httpClient;
	HttpGet getReq;
	JSONObject jsonResponse;
	
	//GET Call rest service without header
	public CloseableHttpResponse getResponse(String uri) {
		 httpClient = HttpClients.createDefault();
		 getReq = new HttpGet(uri);
		try {
			 httpResponse = httpClient.execute(getReq);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return httpResponse;
	}
	
	//GET Call rest service with header
	public CloseableHttpResponse getResponse(String uri,Map<String,String> headerMap) {
		System.out.println("RestClients.getResponse() with header");
		httpClient = HttpClients.createDefault();
		getReq = new HttpGet(uri);
		 Stream<Entry<String, String>> headerStream = headerMap.entrySet().stream();
		 headerStream.forEach(temp -> getReq.addHeader(temp.getKey(), temp.getValue()));
		 
		 try {
			httpResponse= httpClient.execute(getReq);
			System.out.println("RestClients.getResponse(>>)"+httpResponse);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return httpResponse;
	}
	
	//POST call to add entity
	public CloseableHttpResponse saveUserData(String uri,Map<String,String> headerMap,String entityString) {
		
		httpClient=HttpClients.createDefault();
		HttpPost httpPost=new HttpPost(uri);
		
		Stream<Entry<String,String>> payloadMap = headerMap.entrySet().stream();
		payloadMap.forEach(temp -> httpPost.addHeader(temp.getKey(), temp.getValue()));
		
		try {
			httpPost.setEntity(new StringEntity(entityString));
			httpResponse = httpClient.execute(httpPost);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
				 
		 return httpResponse;
		
	}
	
	
	
	public int getStatus(CloseableHttpResponse httpResponse) {
		return httpResponse.getStatusLine().getStatusCode();
	}
	
	public JSONObject getResponseBody(CloseableHttpResponse httpResponse) {
		try {
			String response = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
			 jsonResponse = new JSONObject(response);
			
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonResponse;
	}
	
	public Header[] getHeaders(CloseableHttpResponse httpResponse) {
		Header[] header = httpResponse.getAllHeaders();
		return header;
	}
}
