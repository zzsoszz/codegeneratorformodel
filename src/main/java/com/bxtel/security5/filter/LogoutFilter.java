package com.bxtel.security5.filter;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import com.bxtel.commons.Response;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dinamica.util.JsonHelper;

@Component
public class LogoutFilter extends GenericFilterBean  {
	String requesturl="/j_spring_security_logout";
	String successurl;
	public void doFilter(ServletRequest request0, ServletResponse response0,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) request0;
		HttpServletResponse response = (HttpServletResponse) response0;
		if(request.getRequestURI().endsWith(requesturl))
		{
			boolean isAjax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With")); 
			request.getSession().invalidate();
			if(isAjax)
			{
				Response<String> kv=new Response<String>();  
     			kv.setReturncode("00000000");
				kv.setReturnmsg("登出成功");
				outputJson(response, kv);
			}else{
				response.sendRedirect(request.getContextPath()+successurl);
			}
			return;
		}
		filterChain.doFilter(request, response);
	}
	private void outputJson(HttpServletResponse response, Response<String> kv)
				throws IOException, JsonGenerationException, JsonMappingException {
			response.setContentType("application/json;charset=UTF-8");  
			response.setHeader("Pragma", "No-cache");  
			response.setHeader("Cache-Control", "no-cache");  
			response.setDateHeader("Expires", 0);  
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			ObjectMapper om=JsonHelper.getObjectMapperInstance();
			out.print(om.writeValueAsString(kv)); 
			out.flush();
			out.close();
	}

	public void destroy() {
		
	}
	

	public String getRequesturl() {
		return requesturl;
	}


	public void setRequesturl(String requesturl) {
		this.requesturl = requesturl;
	}


	public String getSuccessurl() {
		return successurl;
	}


	public void setSuccessurl(String successurl) {
		this.successurl = successurl;
	}


}


/*
最近在做servlet的知识回顾，唉，发现都忘得差不多了

在做一个sessionOutFilter的时候有一句代码是sendRedirect，但是到达不了目的页面，报的就是下面的错误

严重: Servlet.service() for servlet jsp threw exception
 java.lang.IllegalStateException
  at org.apache.catalina.connector.ResponseFacade.sendRedirect(ResponseFacade.java:435)
  at org.apache.jsp.main_jsp._jspService(main_jsp.java:188)
  at org.apache.jasper.runtime.HttpJspBase.service(HttpJspBase.java:70)
  at javax.servlet.http.HttpServlet.service(HttpServlet.java:803)
  at org.apache.jasper.servlet.JspServletWrapper.service(JspServletWrapper.java:374)
  at org.apache.jasper.servlet.JspServlet.serviceJspFile(JspServlet.java:337)
  at org.apache.jasper.servlet.JspServlet.service(JspServlet.java:266)
  at javax.servlet.http.HttpServlet.service(HttpServlet.java:803)
  at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:290)
  at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:206)
  at org.apache.catalina.core.ApplicationDispatcher.invoke(ApplicationDispatcher.java:630)
  at org.apache.catalina.core.ApplicationDispatcher.processRequest(ApplicationDispatcher.java:436)
 ......
 
 
 
网上查了一下有人说在
 
response.sendRedirect(request.getContextPath()+ "/HomePage.jsp");后面加上
 return;即可解决报错问题，但是在我加上之后依然报该错，最后看到这篇文章：
 
《使用response.sendRedirect的注意事项》转自：http://www.newasp.net/tech/java/15117.html
 
使用response.sendRedirect时就需要注意以下两点：
 1，在使用response.sendRedirect时，前面不能有HTML输出。
 这并不是绝对的，不能有HTML输出其实是指不能有HTML被送到了浏览器。事实上现在的server都有cache机制，一般在8K（我是说JSP　SERVER），这就意味着，除非你关闭了cache，或者你使用了out.flush()强制刷新，那么在使用sendRedirect之前，有少量的HTML输出也是允许的。
 如果报错说，“一些信息已经被submitted”（原文忘了），那么，你就要注意看了，前面是不是有过多的HTML输出了。
 
2，在response.sendRedirect之后，应该紧跟一句return;
 我们已经知道response.sendRedirect是通过浏览器来做转向的，所以只有在页面处理完成后，才会有实际的动作。既然你已经要做转向了，那么后的输出还有什么意义呢？而且有可能会因为后面的输出导致转向失败。
*/