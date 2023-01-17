package com.springboot.test.targhe.customexception;

public class VeicoloException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 362825795563514369L;

	public VeicoloException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}
	
	public VeicoloException(String errorMessage) {
		super(errorMessage);
	}

}
