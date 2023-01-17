package com.springboot.test.targhe.dto;

public class ReturnError {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ReturnError(String message) {
		super();
		this.message = message;
	}

}
