package com.restassured.test;

import java.util.HashMap;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class HTTPRequest {
	int id;
	@Test(priority= 1)
	public void getUser() {
		given()
		.when()
		.get("https://reqres.in/api/users/2")
		
		.then()
		.statusCode(200)
		.assertThat().header("Content-Type","application/json; charset=utf-8")
		.log().body();
		
	}
	@Test(priority = 2)
	public void createUser() {
		HashMap data = new HashMap();
		data.put("name", "Gpaudel");
		data.put("job", "QA");
		id =given()
		.contentType("application/json")
		.body(data)
		
		.when()
		.post("https://reqres.in/api/users")
		.jsonPath().getInt("id");
		
		
	}
	@Test(priority = 3,dependsOnMethods = {"createUser"})
	public void updateUser() {
		HashMap data = new HashMap();
		data.put("name", "Gpaudel1");
		data.put("job", "QA1");
		
		given()
		.contentType("application/json")
		.body(data)
		
		.when()
		.put("https://reqres.in/api/users/" + id)
		.then()
		.statusCode(200)
		.assertThat().header("Content-Type", "application/json; charset=utf-8")
		.log().body();
		
		
	}
	@Test(priority = 4)
	public void deleteUser() {
		given()
		
		.when()
		.delete("https://reqres.in/api/users/" + id)
		.then()
		.statusCode(204);
	}

}
