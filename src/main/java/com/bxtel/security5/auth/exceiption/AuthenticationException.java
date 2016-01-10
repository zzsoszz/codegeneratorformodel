package com.bxtel.security5.auth.exceiption;

import com.bxtel.security5.auth.AbsAuthenticationRequest;

public abstract class AuthenticationException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private AbsAuthenticationRequest authentication;
	
    private transient Object extraInformation;

    public AuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }
    
    public AuthenticationException(String msg) {
        super(msg);
    }
}

