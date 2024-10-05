package com.shop.microservices.shop_service;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MongoDBContainer;

import static org.hamcrest.Matchers.notNullValue;

//RestAssured : a popular java library for testign restful apis
//assurting = affirming that something is true
@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ShopServiceApplicationTests {

	// by adding this annoation spring boot will automatically add the necessary info about the url defined in the application.propoerties
	@ServiceConnection
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.5");

	//@LocalServerPort annotation injects the actual web server port
	@LocalServerPort
	private Integer port;

	//@BeforeEach is used to signal that the annotated method should be executed before each @Test method in the current test class.
	@BeforeEach
	void setup(){
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

/*
The static block is executed when the class is loaded into memory,
 before any instances of the class are created.
 This means that the MongoDB container will be started before
  any test methods are executed.

  The mongoDBContainer.start() method is called,
   which initializes the Docker container running MongoDB.
   If the specified MongoDB image (in your case, mongo:4.0.10) is not available locally,
    Docker will pull it from Docker Hub.
Once the container is running, it will be ready for your tests to connect to it.
* */

	static{
		mongoDBContainer.start();
	}
	@Test
	void shouldCreateItem() {
		String requestBody= """
    				{
					"name": "item1",
					"description": "item1 description",
					"price": 100
				}
				""";
		RestAssured.given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("/api/shop")
				.then()
				.statusCode(201)
				.body("id", Matchers.notNullValue())
				.body("name", Matchers.equalTo("item1"))
				.body("description", Matchers.equalTo("item1 description"))
				.body("price", Matchers.equalTo(100));


	}

}
