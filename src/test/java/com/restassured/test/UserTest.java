package com.restassured.test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class UserTest {
	@Test(priority = 1)
	public void verifyUserExistInDtabase() {
		int requestId = 2;
		given()
			.baseUri("https://reqres.in/")
			.basePath("/api/users")
			.contentType(ContentType.JSON)
			
		.when()
		 	.get("/2")   //user id  or we can directly  use  https://reqres.in//api/users/2
		
		.then()
			.log().all()
			.assertThat().statusCode(200) // verify the ersponse code
			.assertThat().header("Content-Type", equalTo("application/json; charset=utf-8")) // compare response header content type
			.assertThat().body("data.id", equalTo(requestId))
			.assertThat().body("data.email", equalTo("janet.weaver@reqres.in"));
	}
	@Test(priority = 2)
	public void verifyUserNotExistInDatabase() {
		int requestId = 2000;
		given()
		.contentType(ContentType.JSON)
			
		.when()
		 	.get("https://reqres.in/api/users/2000")   //user id  or we can directly  use  https://reqres.in//api/users/2
		
		.then()
			.log().all()
			.assertThat().statusCode(404) // verify the ersponse code
			.assertThat().header("Content-Type", equalTo("application/json; charset=utf-8")) // compare response header content type
			.assertThat().body("size()", Matchers.is(0));
			
	}
	@Test(priority =4)
	public void updateUser() {
		String name1 = "Hari1";
		String job = "leader";
		String requestBody = "{\r\n"
				+ "    \"name\": \""+name1+"\",\r\n"
				+ "    \"job\": \""+job+"\"\r\n"
				+ "}";
		given()
		
		.contentType(ContentType.JSON)
		.body("requestBody")
		
		.when()
		.put("https://reqres.in//api/users/2")
		
		.then()
		.log().all()
		.assertThat().statusCode(200)
		.assertThat().body("name", equalTo(name1))
		.assertThat().body("job",equalTo(job))
		.assertThat().header("Content-Type", equalTo("application/json; charset=utf-8"));
		
				
	}
	@Test(priority = 3)
	public void createUser() {
		String name = "Ramesh";
		String job = "leader";
		String requestBody = "{\r\n"
				+ "    \"name\": \""+name+"\",\r\n"
				+ "    \"job\": \""+job+"\"\r\n"
				+ "}";
		given()
		
		.contentType(ContentType.JSON)
		.body("requestBody")
		
		.when()
		.post("https://reqres.in//api/users")
		
		.then()
		.log().all()
		.assertThat().statusCode(201)
		.assertThat().body("name", equalTo(name))
		.assertThat().body("job",equalTo(job))
		.assertThat().header("Content-Type", equalTo("application/json; charset=utf-8"));
		
				
	}
	@Test(priority =6)
	public void deleteUser() {
		String userId = "2";
		given()
		
		.contentType(ContentType.JSON)
		.body("requestBody")
		
		.when()
		.delete("https://reqres.in//api/users/"  + userId)
		
		.then()
		.log().all()
		.assertThat().statusCode(400);
		
		
				
		
	}
	

}
