package com.bxtel.security5.auth;

import java.util.Collection;

public interface ISecurityMetadataSource {
	Collection<IConfigAttribute> getAttributes(Object  securityobj) ;
}