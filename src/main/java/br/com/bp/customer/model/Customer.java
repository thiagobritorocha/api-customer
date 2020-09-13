package br.com.bp.customer.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

@Entity
@Table(name = "customer")
public class Customer {
	
	public Customer() {
		
	}
	
	public Customer(@NotBlank(message = "Name is mandatory") String name,
			@NotBlank(message = "CPF is mandatory") String cpf, Adress adress) {
		super();
		this.name = name;
		this.cpf = cpf;
		this.adress = adress;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@NotBlank(message = "Name is mandatory")
	@Size(max = 50)
	private String name;
	@NotNull
	@NotBlank(message = "CPF is mandatory")
	@Size(max = 11)
	private String cpf;
	@Embedded
	private Adress adress;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Adress getAdress() {
		return adress;
	}
	public void setAdress(Adress adress) {
		this.adress = adress;
	}
	
	
	
}
