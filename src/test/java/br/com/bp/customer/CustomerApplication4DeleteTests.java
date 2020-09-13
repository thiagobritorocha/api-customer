package br.com.bp.customer;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import io.restassured.specification.RequestSpecification;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class CustomerApplication4DeleteTests {

	private RequestSpecification givenAuth = AuthenticationService.givenAuth();
	
	@Test 
	public void givenCustomerIdDeleteThenReturnsStatusNoContent() throws JSONException {
		givenAuth.log().all()
			.delete("/api/customer/v1.0/1")
		.then().log().all()
			.statusCode(HttpStatus.NO_CONTENT.value());
	}
	
	@Test 
	public void givenCustomerIdThatNotExitsReturnsNotFound() throws JSONException {
		givenAuth.log().all()
			.delete("/api/customer/v1.0/12")
		.then().log().all()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}

}
