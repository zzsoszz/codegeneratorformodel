package com.bxtel.system;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;


//@WebServlet(name="systemInitServlet")  
@WebServlet(value="/servlet/init-param", initParams={@WebInitParam(name="param1", value="value1")})  
public class SystemInitServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void init() {
		ServletContext   servletContext   =   this.getServletContext();
		Enumeration<String> attrs = servletContext.getAttributeNames();
		while(attrs.hasMoreElements())
		{
			String str = attrs.nextElement();
			System.out.println("servletcontextkey:"+str);
			//key:payController   value:com.bxtel.bxpay.controller.PayController@18c26d7
		}
		//jstl-servlet.xml 上线文
		//ApplicationContext   ctx= (ApplicationContext) servletContext.getAttribute(FrameworkServlet.SERVLET_CONTEXT_PREFIX+"jstl");
	}
}
