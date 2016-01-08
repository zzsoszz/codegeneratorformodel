package com.bxtel.exception;

/*
 * 同城客户端严重的异常，客户无法处理，需要后台系统或者管理员处理
 */
public class ClientSeriousException extends ClientException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ClientSeriousException(String msg) {
        super(msg);
    }

    /**
     * Constructs an <code>AuthorizationServiceException</code> with the
     * specified message and root cause.
     *
     * @param msg the detail message
     * @param t root cause
     */
    public ClientSeriousException(String msg, Throwable t) {
        super(msg, t);
    }
    public ClientSeriousException(Throwable t) {
        super(t);
    }
}
