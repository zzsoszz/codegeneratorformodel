package com.bxtel.exception;

public class DAOException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DAOException() {
		super();
	}
	public DAOException(String msg) {
		super(msg);
	}
	/*
	 * 保留上一个异常信息
	 */
	public DAOException(String msg, Throwable cause) {
		super(msg, cause);
	}
	public DAOException(Throwable cause) {
		super(cause);
	}
}
