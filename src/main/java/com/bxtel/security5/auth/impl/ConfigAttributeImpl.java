package com.bxtel.security5.auth.impl;

import org.springframework.security.access.ConfigAttribute;

public class ConfigAttributeImpl  implements ConfigAttribute  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String role;
	ConfigAttributeImpl(String role){
		this.role=role;
	}
	@Override
	public String getAttribute() {
		return role;
	}
}
