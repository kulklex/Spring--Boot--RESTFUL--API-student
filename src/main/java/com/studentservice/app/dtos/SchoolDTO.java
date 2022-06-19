package com.studentservice.app.dtos;

import javax.validation.constraints.NotNull;

public class SchoolDTO {
	private String name;
	private String city;
	
	
	@NotNull
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	
	public SchoolDTO(String name, String city) {
		super();
		this.name = name;
		this.city = city;
	}
	
	public SchoolDTO() {
	}

	
}
