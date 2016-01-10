package com.bxtel.security5.auth.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.bxtel.security5.auth.IAuthenticationResponse;
import com.bxtel.security5.auth.IAuthenticationSuccessHandler;
@Component
public class AuthenticationSuccessHandlerImpl implements IAuthenticationSuccessHandler{
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,IAuthenticationResponse authentication) throws IOException, ServletException {
		
	}
}
