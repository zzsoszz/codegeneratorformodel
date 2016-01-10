package com.bxtel.security5.auth.impl;
import com.bxtel.security5.auth.AbsAuthenticationRequest;
public class UserNamePaswordAuthenticationRequest extends AbsAuthenticationRequest{
	String username;
	String password;
	public UserNamePaswordAuthenticationRequest(String username,String password)
	{
		this.username=username;
		this.password=password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
