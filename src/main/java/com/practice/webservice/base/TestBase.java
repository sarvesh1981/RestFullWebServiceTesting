package com.practice.webservice.base;

import java.util.Properties;

import org.apache.http.client.methods.CloseableHttpResponse;

import com.practice.webservices.utils.RestClients;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TestBase {

	public static CloseableHttpResponse httpResponse;
	public static RestClients rc;
	

	public static CloseableHttpResponse getHttpResponse() {
		return httpResponse;
	}

	public static void setHttpResponse(CloseableHttpResponse httpResponse) {
		TestBase.httpResponse = httpResponse;
	}

	protected Properties prop=null;
	String propPath="C:\\Users\\sarvsinh\\git\\RestFullWebServiceTesting\\WSRestfullTesting\\src\\main\\java\\com\\practice\\webservice\\config\\config.properties";
	
	public TestBase() {
		try {
			rc= new RestClients();
			prop=new Properties();
			FileInputStream io=new FileInputStream(propPath);
			prop.load(io);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
