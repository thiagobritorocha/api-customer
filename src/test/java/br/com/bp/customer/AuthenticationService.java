package br.com.bp.customer;

import org.springframework.boot.test.context.SpringBootTest;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AuthenticationService {
	
	public static RequestSpecification givenAuth() { //TODO passar credenciais para service config
		return RestAssured.given()
				.auth()
				.preemptive()
				.basic("user", "userPass")
				.baseUri("http://localhost")
				.port(8080);
	}

}
