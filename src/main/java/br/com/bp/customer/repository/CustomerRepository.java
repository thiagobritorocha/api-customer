package br.com.bp.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bp.customer.model.Customer;

public interface CustomerRepository  extends JpaRepository<Customer, Long>{

	
	public Customer findByName(String name);
}
