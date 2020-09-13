package br.com.bp.customer.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

@Embeddable
public class Adress {
	@Size(max = 50)
	private String street;
	@Size(max = 50)
	private String number;
	@Size(max = 50)
	private String complement;
	@Size(max = 50)
	private String neighborhood;
	@Size(max = 50)
	private String zipCode;
	@Size(max = 50)
	private String city;
	@Size(max = 50)
	private String states;
	
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getComplement() {
		return complement;
	}
	public void setComplement(String complement) {
		this.complement = complement;
	}
	public String getNeighborhood() {
		return neighborhood;
	}
	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStates() {
		return states;
	}
	public void setStates(String states) {
		this.states = states;
	}
	
}
