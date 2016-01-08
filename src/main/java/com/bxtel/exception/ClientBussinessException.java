package com.bxtel.exception;

/*
 * 深圳捷旅假期,客户无法处理，需要业务层进行异常转换发送给客户
 */
public class ClientBussinessException extends ClientException{

	private static final long serialVersionUID = 1L;
	public ClientBussinessException(String msg) {
        super(msg);
    }

    /**
     * Constructs an <code>AuthorizationServiceException</code> with the
     * specified message and root cause.
     *
     * @param msg the detail message
     * @param t root cause
     */
    public ClientBussinessException(String msg, Throwable t) {
        super(msg, t);
    }
    public ClientBussinessException(Throwable t) {
        super(t);
    }
    
    
}
