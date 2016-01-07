package com.bxtel.exception;

public class MessageException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MessageException() {
		super();
	}
	public MessageException(String msg) {
		super(msg);
	}
	public MessageException(String msg, Throwable cause) {
		super(msg, cause);
	}
	public MessageException(Throwable cause) {
		super(cause);
	}
}
