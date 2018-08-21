package com.reclameaqui.api.documents;

public class Locale {

	private String city;
	private String state;

	public Locale(String city, String state) {

		this.city = city;
		this.state = state;
	}

	public Locale() {
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
