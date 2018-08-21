package com.reclameaqui.api.documents;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "complain")
public class Complain {

	@Id
	private String id;

	@NotEmpty(message = "O titulo não pode ser vazio")
	private String title;
	private Locale locale;

	@DBRef
	private Company company;

	private String description;

	public Complain(String id, String title, Locale locale, Company company, String description) {
		super();
		this.id = id;
		this.title = title;
		this.locale = locale;
		this.company = company;
		this.description = description;
	}

	public Complain() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
