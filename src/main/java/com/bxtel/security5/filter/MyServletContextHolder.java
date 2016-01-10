package com.bxtel.security5.filter;

import javax.servlet.ServletContext;

public class MyServletContextHolder {
	public static ServletContext servletContext;

	
	public static  ServletContext getServletContext() {
		return servletContext;
	}
	
	public static void setServletContext(ServletContext servletContext) {
		MyServletContextHolder.servletContext = servletContext;
	}
}
