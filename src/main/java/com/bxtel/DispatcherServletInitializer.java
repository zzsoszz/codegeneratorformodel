//package com.bxtel;
//
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import org.springframework.web.filter.DelegatingFilterProxy;
//import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
//public class DispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
//	
//	@Override
//	public void onStartup(ServletContext servletContext) throws ServletException {
//		servletContext.addFilter("securityFilter", new DelegatingFilterProxy("springSecurityFilterChain")).addMappingForUrlPatterns(null, false, "/*");
//		super.onStartup(servletContext);
//	}
//	
//	@Override
//	protected Class<?>[] getRootConfigClasses() {
//		return null;
//	}
//
//	@Override
//	protected Class<?>[] getServletConfigClasses() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	protected String[] getServletMappings() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	
//	
//}