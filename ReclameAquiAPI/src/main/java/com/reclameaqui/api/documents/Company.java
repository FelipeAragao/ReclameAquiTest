package com.reclameaqui.api.documents;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "company")
public class Company {

	@Id
	private String id;

	@NotEmpty(message = "Nome não pode ser vazio")
	private String name;

	@CNPJ(message = "CNPJ inválido")
	private String cnpj;

	public Company(String id) {
		this.id = id;
	}

	public Company(String id, String name, String cnpj) {
		this.id = id;
		this.name = name;
		this.cnpj = cnpj;
	}

	public Company(String name, String cnpj) {
		this.name = name;
		this.cnpj = cnpj;
	}

	public Company() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

}
