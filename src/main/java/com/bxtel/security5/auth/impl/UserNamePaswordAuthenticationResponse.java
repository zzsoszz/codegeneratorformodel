package com.bxtel.security5.auth.impl;

import java.util.ArrayList;

import com.bxtel.security5.auth.IAuthenticationResponse;

public class UserNamePaswordAuthenticationResponse implements IAuthenticationResponse {
	Object userdata;
	ArrayList<String>  authorities;
	boolean isAuthenticated;
	UserNamePaswordAuthenticationResponse(Object userdata,ArrayList<String>  authorities)
	{
		this.userdata=userdata;
		this.authorities=authorities;
		this.isAuthenticated=true;
	}
	@Override
	public Object getUserData() {
		return userdata;
	}
	@Override
	public ArrayList getAuthorities() {
		return authorities;
	}
	@Override
	public boolean isAuthenticated() {
		return this.isAuthenticated;
	}
}
