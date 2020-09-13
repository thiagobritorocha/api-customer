package br.com.bp.customer;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import io.restassured.specification.RequestSpecification;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT)
class CustomerApplication2GetTests {

	private RequestSpecification givenAuth = AuthenticationService.givenAuth();
	
	@Test
	public void givenCustomerIdThatNotExistsThenRespondWithNotFount() throws JSONException {
		givenAuth.log().all()
			.get("/api/customer/v1.0/0")
		.then().log().all()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	@Test
	public void givenCustomerIdThenReturnsStatusOk() {
		givenAuth.log().all()
			.get("/api/customer/v1.0/1")
		.then().log().all()
			.statusCode(HttpStatus.OK.value())
			.body("id",is(1));
	}
	
	@Test
	public void givenNothingThenReturnsAllCustomersAndStatusOk() {
		givenAuth.log().all()
			.get("/api/customer/v1.0/")
		.then().log().all()
			.statusCode(HttpStatus.OK.value())
			.body(notNullValue());
	}

}
