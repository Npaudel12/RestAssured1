package com.restassured.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.data.store.UserData;
import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GoRestUserTest {
	String token = "ea876ec4b6d43a90c6d0a9914a3f79297e98a41e7a281d027aaf99b4e056b623";
	@Test(priority =1)
	public void testCreateUser() {
		Faker faker = new Faker();
		String reqName = faker.name().fullName();
		String reqGender = faker.demographic().sex().toLowerCase();
		String reqEmail = faker.internet().emailAddress();
		String reqStatus = "active";
		
		String requestBody = "{\r\n"
				+ "	\"name\": \""+reqName+"\",\r\n"
				+ "	\"gender\": \""+reqGender+"\",\r\n"
				+ "	\"email\": \""+reqEmail+"\",\r\n"
				+ "	\"status\": \""+reqStatus+"\"\r\n"
				+ "}";
		
	Response rs =  RestAssured.given()
				.contentType("application/json")
				.header("Authorization", "Bearer "+ token)
				.body(requestBody)
			.when()
				.post("https://gorest.co.in/public/v2/users");
		   
			   rs.then().log().body();
			   String actResponseName = rs.getBody().jsonPath().getString("name");
			   String actResponseEmail = rs.getBody().jsonPath().getString("email");
			   String actResponseGender = rs.getBody().jsonPath().getString("gender");
			   String actResponseStatus = rs.getBody().jsonPath().getString("status");
			   int actResponseID = rs.getBody().jsonPath().getInt("id");
			   int statusCode = rs.getStatusCode();
			 
			   
			   
			   Assert.assertEquals(statusCode, 201, " Response code not as expected");
			   Assert.assertEquals(actResponseName, reqName, "Name is not as expected");
			   Assert.assertEquals(actResponseEmail, reqEmail, "Email is not as expected");
			   Assert.assertEquals(actResponseGender, reqGender, "Gender is not as expected");
			   Assert.assertEquals(actResponseStatus, reqStatus, "Status is not as expected");
			   
		}
	
}
