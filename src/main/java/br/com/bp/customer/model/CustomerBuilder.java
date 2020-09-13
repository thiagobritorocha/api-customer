package br.com.bp.customer.model;
public class CustomerBuilder {
	
  private Customer customer = new Customer();
  
  public CustomerBuilder name(String name) {
    customer.setName(name);
    return this;
  }
  
  public CustomerBuilder cpf(String cpf) {
    customer.setCpf(cpf);
    return this;
  }
  
  public CustomerBuilder adress(Adress adress) {
    customer.setAdress(adress);
    return this;
  }
  
  public Customer build() {
    return customer;
  }
}