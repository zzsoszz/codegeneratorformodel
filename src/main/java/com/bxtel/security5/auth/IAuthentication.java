package com.bxtel.security5.auth;

import com.bxtel.security5.auth.exceiption.AuthenticationException;
public interface IAuthentication{
	public IAuthenticationResponse authenticate(AbsAuthenticationRequest authentication) throws AuthenticationException;
	boolean supported(Class myclass);
}