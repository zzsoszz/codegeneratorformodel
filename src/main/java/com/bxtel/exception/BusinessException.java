package com.bxtel.exception;

public class BusinessException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BusinessException() {
		super();
	}
	public BusinessException(String msg) {
		super(msg);
	}
	/*
	 * 保留上一个异常信息
	 */
	public BusinessException(String msg, Throwable cause) {
		super(msg, cause);
	}
	public BusinessException(Throwable cause) {
		super(cause);
	}
}
