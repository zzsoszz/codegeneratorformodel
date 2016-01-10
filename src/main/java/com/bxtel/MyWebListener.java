package com.bxtel;
//package com.bxtel.system;
//
//import javax.servlet.ServletContext;
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//import javax.servlet.annotation.WebListener;
//import org.springframework.web.filter.DelegatingFilterProxy;
//
//import com.bxtel.security4.filter.UsernamePasswordLoginFilter;
//
////java.lang.UnsupportedOperationException: Section 4.4 of the Servlet 3.0 specification does not permit this method to be called from a ServletContextListener that was not defined in web.xml, a web-fragment.xml file nor annotated with @WebListener
//
//@WebListener
//public class MyWebListener implements ServletContextListener {
//	
//	@Override
//	public void contextInitialized(ServletContextEvent sce) {
//		ServletContext sc = sce.getServletContext();
//		//sc.addFilter("securityFilter",new DelegatingFilterProxy("springSecurityFilterChain2")).addMappingForUrlPatterns(null, false, "/*");
//		sc.addFilter("securityFilter",new UsernamePasswordLoginFilter());
//        System.err.println("aaaaa------------------------------------");
////		sc.add
////		sc.addServlet("myServlet", "Sample servlet", "foo.bar.MyServlet", null, -1);
////		sc.addServletMapping("myServlet", new String[] { "/urlpattern/*" });
//	}
//	@Override
//	public void contextDestroyed(ServletContextEvent sce) {
//		
//	}
//}