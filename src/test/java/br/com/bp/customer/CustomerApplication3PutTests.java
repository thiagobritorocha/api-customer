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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class CustomerApplication3PutTests {

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
	public void givenCustomerThanUpdateAndRespondWhithOk() throws JSONException {
		customer.put("name", "João");
		givenAuth.log().all()
			.contentType("application/json")
			.body(customer.toString())
		.when()
			.put("/api/customer/v1.0/1")
		.then().log().all()
			.statusCode(HttpStatus.OK.value())
			.body("name", equalTo("João"));
	}
	
	@Test
	public void givenCustomerWithNameLagerReturnsBadRequest() throws JSONException{
		customer.put("name", "Pedro de Alcântara Francisco Antônio João Carlos Xavier de Paula Miguel Rafael Joaquim José Gonzaga Pascoal Cipriano Serafim de Bragança e Bourbon");
		givenAuth.log().all()
			.contentType("application/json")
			.body(customer.toString())
		.when()
			.put("/api/customer/v1.0/1")
		.then().log().all()
			.statusCode(HttpStatus.BAD_REQUEST.value())
			.body(containsString("name must be the size between 0 and 50"));
	}
}
