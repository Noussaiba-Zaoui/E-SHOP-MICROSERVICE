package com.order_service.order_service;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;

import com.order_service.order_service.dto.OrderRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MySQLContainer;


import static org.awaitility.Awaitility.given;
import static org.hamcrest.MatcherAssert.assertThat;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderServiceApplicationTests {


	//mysql container:
	@ServiceConnection
	static MySQLContainer mySQLContainer = new MySQLContainer("mysql:8.3.0");
	//the port :
	@LocalServerPort
	private Integer port;

	@BeforeEach
	void startup() {
		RestAssured.baseURI = "http://localhost" ;
		RestAssured.port = port;
	}

	static{
		mySQLContainer.start();

	}

	@Test
	void WhenOrderDone() {
		String submitOrderJson= """
    				{
    				"orderName":"orderName",
					"skuCode":"skuCode",
					"price":100,
					"quantity":10
				}
				""";
				var RestAssuredBody = RestAssured.given()
				.contentType("application/json")
				.body(submitOrderJson)
				.when()
				.post("/api/order")
				.then()
				.log().all()
				.statusCode(201)
		        .extract()
				.body()
				.asString();
		assertThat(RestAssuredBody, Matchers.is("Order placed Successfully"));


	}

}
