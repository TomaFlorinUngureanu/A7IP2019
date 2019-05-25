package com.tripManagement.adresses;

import static org.junit.Assert.*;
import static io.restassured.RestAssured.given;

import org.junit.Before;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class LocationServiceTest {
	
	private final String CONTEXT_PATH = "/trip/getLocation/";	 
	@Before
	public void setUp() throws Exception {
		RestAssured.baseURI="http://localhost";
		RestAssured.port=8761;
	}

	@Test
	public void getLocationtest() {
		/*Response response = 
		given().contentType("application/json")
		.accept("application/json")
		.body(null)
		.when().post(CONTEXT_PATH + "/")
		.then()
		.statusCode(200)
		.contentType("application/json")
		.extract()
		.response();*/
	}

}
