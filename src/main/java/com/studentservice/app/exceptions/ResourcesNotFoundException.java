package com.studentservice.app.exceptions;


public class ResourcesNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String resource;
	private String fieldName;
	
	
	public ResourcesNotFoundException(Object student) {
		super("There is an Invalid " + student + " field");
		
	}


	public String getResource() {
		return resource;
	}


	public void setResource(String resource) {
		this.resource = resource;
	}


	public String getFieldName() {
		return fieldName;
	}


	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
	
	
	

}
