package br.com.bp.customer;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.equalTo;

import javax.annotation.PostConstruct;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import io.restassured.specification.RequestSpecification;


@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT)
class CustomerApplication1PostTests {

	private RequestSpecification givenAuth = AuthenticationService.givenAuth();
	
	private JSONObject customer;

	@PostConstruct
	public void init() throws JSONException {
		
		customer = new JSONObject();
		customer.put("name", "Thiago");
		customer.put("cpf", "22653299836");
		JSONObject adress = new JSONObject();
		adress.put("street", "Rua Adriano Cintra");
		adress.put("city", "São Paulo");
		adress.put("neighborhood", "Jardim Maringá");
		adress.put("number", "15");
		adress.put("states", "São Paulo");
		adress.put("zipCode", "063574100");
		adress.put("complement", "Apartamento 6");
		customer.put("adress", adress);
	}
		
	@Test
	public void givenCustormerCreateThenReturnsCustomer() {
		givenAuth
			.log().all()
			.contentType("application/json")
			.body(customer.toString())
		.when()
			.post("/api/customer/v1.0/")
		.then()
			.log().all()
			.statusCode(HttpStatus.CREATED.value())
			.body("name", equalTo("Thiago"))
			.body("cpf", equalTo("22653299836"))
			.body("adress.street", equalTo("Rua Adriano Cintra"))
			.body("adress.city", equalTo("São Paulo"))
			.body("adress.neighborhood", equalTo("Jardim Maringá"))
			.body("adress.number", equalTo("15"))
			.body("adress.states", equalTo("São Paulo"))
			.body("adress.zipCode", equalTo("063574100"))
			.body("adress.complement", equalTo("Apartamento 6"));

	}
	
	@Test
	public void givenCustomerWithOutNameReturnsBadRequest() throws JSONException {
		customer.remove("name");
		givenAuth
			.log().all()
			.contentType("application/json")
			.body(customer.toString())
		.when()
			.post("/api/customer/v1.0/")
		.then().log().all()
			.statusCode(HttpStatus.BAD_REQUEST.value())
			.body(containsString("Name is mandatory"));
	}
	
	@Test
	public void givenCustomerWithCPFLagerReturnsBadRequest() throws JSONException {
		customer.put("cpf", "1234567896541236");
		givenAuth
			.log().all()
			.contentType("application/json")
			.body(customer.toString())
		.when()
			.post("/api/customer/v1.0/")
		.then().log().all()
			.statusCode(HttpStatus.BAD_REQUEST.value())
			.body(containsString("cpf must be the size between 0 and 11"));
	}

}
