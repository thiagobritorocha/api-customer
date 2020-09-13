package br.com.bp.customer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.bp.customer.exception.CustomerNotFoundException;
import br.com.bp.customer.model.Customer;
import br.com.bp.customer.repository.CustomerRepository;

/**
 * @author <a href="mailto:thiagobritorocha@yahoo.com.br">thiago.rocha</a>
 * @date 11 de sep de 2020 10:34:45
 */
@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	public List<Customer> list(){
		return customerRepository.findAll();
	}
	
	public Customer find(Long id){
		Optional<Customer> customer = customerRepository.findById(id);
		if(!customer.isPresent()){
			throw new EmptyResultDataAccessException(1);
		}
		return customer.get();
	}
	
	public Customer save(Customer customer){
		customer.setId(null);
		return (Customer) customerRepository.save(customer);
	}
	
	public void delete(Long id){
		try {
			customerRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new CustomerNotFoundException("The customer could not be found!");
		}
	}
	
	public Customer update(Customer customer, Long id){
		Customer customerSaved = find(id);
		BeanUtils.copyProperties(customer, customerSaved, "id");
		customerRepository.save(customerSaved);
		return customerSaved;
	}

}	
