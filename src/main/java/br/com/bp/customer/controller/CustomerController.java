package br.com.bp.customer.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bp.customer.event.ResourceCreatedEvent;
import br.com.bp.customer.model.Customer;
import br.com.bp.customer.service.CustomerService;
import io.swagger.annotations.Api;

/**
 * @author <a href="mailto:thiagobritorocha@yahoo.com.br">thiago.rocha</a>
 * @date 11 de sep de 2020 10:34:45
 */
@CrossOrigin()
@RestController
@RequestMapping(value = "/api/customer")
@Api(value = "Customer Resource REST Endpoint", description = "Shows infos of Customer REST API")
public class CustomerController {	
	
	@Autowired
	private CustomerService customerService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping({"/v1.0"})
	public ResponseEntity<List<Customer>> list() {
		return ResponseEntity.status(HttpStatus.OK).body(customerService.list());
	}
	
	@PostMapping({"/v1.0"})
	public ResponseEntity<?> create(@Valid @RequestBody Customer customer, HttpServletResponse response) {
		Customer savedCustomer = customerService.save(customer);
		publisher.publishEvent(new ResourceCreatedEvent(this, response, savedCustomer.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
	}

	@GetMapping("/v1.0/{id}")
	public ResponseEntity<?> find(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(customerService.find(id));
	}

	@PutMapping("/v1.0/{id}")
	public ResponseEntity<Customer> update(@Valid @RequestBody Customer customer, @PathVariable Long id) {
		Customer clientSaved = customerService.update(customer, id);
		return ResponseEntity.ok(clientSaved);
	}

	@DeleteMapping("/v1.0/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		customerService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
