package com.bxtel.security5.auth.impl;

import java.util.List;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import com.bxtel.security5.auth.IAuthentication;
import com.bxtel.security5.auth.IAuthenticationManager;
import com.bxtel.security5.auth.AbsAuthenticationRequest;
import com.bxtel.security5.auth.IAuthenticationResponse;
import com.bxtel.security5.auth.exceiption.NoIAuthDeallException;

@Component
public class AuthenticationManagerImpl implements IAuthenticationManager{
	List<IAuthentication>  authenticationlist;
	@Override
	public IAuthenticationResponse authenticate(AbsAuthenticationRequest authentication) throws AuthenticationException {
		for(IAuthentication auth:authenticationlist)
		{
			if(auth.supported(authentication.getClass()))
			{
				return auth.authenticate(authentication);
			}
		}
		throw new NoIAuthDeallException("this no class  implements IAuthentication");
	}
}
