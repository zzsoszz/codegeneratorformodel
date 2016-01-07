package com.bxtel.exception;


public class ParameterException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ParameterException() {
		super();
	}
	public ParameterException(String msg) {
		super(msg);
	}
	public ParameterException(String msg, Throwable cause) {
		super(msg, cause);
	}
	public ParameterException(Throwable cause) {
		super(cause);
	}
}