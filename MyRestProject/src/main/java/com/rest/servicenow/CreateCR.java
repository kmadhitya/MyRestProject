package com.rest.servicenow;

import java.io.File;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.BasicAuthScheme;
import io.restassured.response.Response;

public class CreateCR {
	
	@DataProvider(name = "getData" /*,parallel=true, indices {1}*/)
	public String[] getData()
	{
		String[] data = new String[2];
		data[0] = "data1.json";
		data[1] = "data2.json";
		return data;
	}
	
	@Test(dataProvider="getData")
	public void creatingCR(String filePath)
	{
		RestAssured.baseURI = "https://dev68763.service-now.com/api/now/table/change_request";
		
		BasicAuthScheme basic = new BasicAuthScheme();
		basic.setUserName("admin");
		basic.setPassword("Chennai@#$1");
		RestAssured.authentication = basic;
		
		File file = new File("./data/"+filePath);
		
		Response response = RestAssured.given().contentType("application/json").body(file).post();
		System.out.println(response.statusCode());
		
		response.prettyPrint();
		
		
		
		
	}

}
