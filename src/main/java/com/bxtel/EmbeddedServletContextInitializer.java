package com.bxtel;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;
@Configuration
public class EmbeddedServletContextInitializer implements ServletContextInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
    	servletContext.addFilter("securityFilter",
    	new DelegatingFilterProxy("springSecurityFilterChain2")).addMappingForUrlPatterns(null, false, "/*");
        System.out.println("------------------------------------");
    }
}