package com.bxtel.security5.auth.impl;
import com.bxtel.security5.auth.IAuthentication;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.bxtel.security5.auth.AbsAuthenticationRequest;
import com.bxtel.security5.auth.IAuthenticationResponse;
import com.bxtel.security5.auth.exceiption.AuthenticationException;
import com.bxtel.user.dao.UserRepository;
import com.bxtel.user.model.User;


@Component
public class UserNamePasswordAuthenticationImpl implements IAuthentication{
	
	@Autowired
	UserRepository dao;
	
	@Override
	public IAuthenticationResponse authenticate(AbsAuthenticationRequest authentication) throws AuthenticationException {
		UserNamePaswordAuthenticationRequest req=(UserNamePaswordAuthenticationRequest)authentication;
		User user=dao.findByMobileAndPassword(req.getUsername(),req.getUsername());
		ArrayList<String> authorities=new ArrayList<String>();
		authorities.add("USER");
		UserNamePaswordAuthenticationResponse resp=new UserNamePaswordAuthenticationResponse(user,authorities);
		return resp;
	}
	
	@Override
	public boolean supported(Class myclass) {
		return myclass.isAssignableFrom(UserNamePaswordAuthenticationRequest.class);
	}
}
