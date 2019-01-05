package com.example.springmicroservices.beans;

public class ExceptionResponse {

	private String statusCode;
	private String descriptiom;
	
	public ExceptionResponse(String statusCode, String descriptiom) {
		super();
		this.statusCode = statusCode;
		this.descriptiom = descriptiom;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getDescriptiom() {
		return descriptiom;
	}

	public void setDescriptiom(String descriptiom) {
		this.descriptiom = descriptiom;
	}
	
	
	
	
}
