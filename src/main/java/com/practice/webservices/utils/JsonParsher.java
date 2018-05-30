package com.practice.webservices.utils;

import java.io.File;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.webservice.data.Users;

public class JsonParsher {
	
	public static ObjectMapper mapper;
	
	public static String getJsonbyPath(JSONObject serviceResponse, String jsonAttrbute) {
		Object obj=serviceResponse;
		System.out.println("------------------"+jsonAttrbute);
		for(String temp:jsonAttrbute.split("/")) 
			if(!temp.isEmpty())	
			 if(!(temp.contains("[") || temp.contains("]"))){
				obj = ((JSONObject) obj).get(temp);
			}else if(temp.contains("[") || temp.contains("]")) {
				obj =((JSONArray) ((JSONObject) obj).get(temp.split("\\[")[0])).get(Integer.parseInt(temp.split("\\[")[1].replace("]", "")));
				
			}
		
		return obj.toString();
	}
	
	
	//using Jackson API for converting(or Marshelling) java Object to JSON object
	public static String getUserInJsonFormat(Users user,String filePath) throws JsonGenerationException, JsonMappingException, IOException {
		
		//Jackson API
		 mapper = new ObjectMapper();
		//Convert Object to JSON File
		mapper.writeValue(new File(filePath), user);
		
		//Convert Object to JSON String
		return mapper.writeValueAsString(user);
	}
	
	//JSON object to Java Object
	public static Object JsonToJavaObj(String rawString,Class<? extends Object> valueType) throws JsonParseException, JsonMappingException, IOException {
		System.out.println("json to Object ="+mapper.readValue(rawString, valueType));
		return mapper.readValue(rawString, valueType);
	}

}
