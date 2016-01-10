package com.bxtel.security5.filter;

import java.io.IOException;


import javax.servlet.*;
import javax.servlet.http.*;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import com.bxtel.security5.auth.IAuthenticationFailureHandler;
import com.bxtel.security5.auth.IAuthenticationManager;
import com.bxtel.security5.auth.IAuthenticationResponse;
import com.bxtel.security5.auth.IAuthenticationSuccessHandler;
import com.bxtel.security5.auth.exceiption.AuthenticationException;
import com.bxtel.security5.auth.exceiption.UsernameNotFoundException;
import com.bxtel.security5.auth.impl.UserNamePaswordAuthenticationRequest;
import dinamica.coder.ThreeDesHelper2;

@Component
public class UsernamePasswordLoginFilter extends GenericFilterBean  {
	
	String requesturl="/j_spring_security_check";
	
	String entrypoint;
	
	private IAuthenticationManager  authenticationManager;
	
	private IAuthenticationSuccessHandler successHandler = null;
    
	private IAuthenticationFailureHandler failureHandler = null;
	
	private String getCookiePath(HttpServletRequest request) {
	        String contextPath = request.getContextPath();
	        return contextPath.length() > 0 ? contextPath : "/";
	}
	
	public void doFilter(ServletRequest request0, ServletResponse response1,
			FilterChain filterChain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) request0;
		HttpServletResponse response = (HttpServletResponse) response1;
		if(request.getRequestURI().endsWith(requesturl))
		{
			String username=request.getParameter("j_username");
			String password=request.getParameter("j_password");
			try{
				if(username==null || password ==null || password.isEmpty() || username.isEmpty()  )
				{
					throw new UsernameNotFoundException("username and password must not be null");
				}
				UserNamePaswordAuthenticationRequest authRequest = new UserNamePaswordAuthenticationRequest(username, password);
				IAuthenticationResponse authResult = authenticationManager.authenticate(authRequest);
				if(authResult.isAuthenticated())
				{
					request.getSession(true).setAttribute("securitycontext", authResult);
					//保存登录信息到cookie 用户于RememberMeFiilter---start
					String autologin=request.getParameter("autologin");//true 自动登录   
					if("true".equals(autologin))
					{
						  int seconds=7*24*60*60;//保存7天
			              long expiretime=System.currentTimeMillis()+seconds;
			              String cookievalue=null;
			              String cookievaluebase64=null;
			              try {
							cookievalue = new Long(expiretime).toString()+RememberMeFiilter.DELIMITER+new String(Base64.encode(username.getBytes()))+RememberMeFiilter.DELIMITER+ThreeDesHelper2.encode(password);
							cookievaluebase64=new String(Base64.encode(cookievalue.getBytes()));
			              } catch (Exception e) {
							  e.printStackTrace();
						  }
			              Cookie cookie = new Cookie(RememberMeFiilter.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY,cookievaluebase64);  
			   			  cookie.setMaxAge(Integer.MAX_VALUE);
			   			  cookie.setPath(RememberMeFiilter.getCookiePath(request));
			   			  response.addCookie(cookie);
					}else{
						Cookie cookie = new Cookie(RememberMeFiilter.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY, null);
					    cookie.setMaxAge(0);
					    cookie.setPath(getCookiePath(request));
					    response.addCookie(cookie);
					}
					//---------------------------------------------end
					if(successHandler!=null)
					{
						successHandler.onAuthenticationSuccess(request, response, authResult);
					}
					return ;
				}
				else{
					throw new Exception("user authentication failed");
				}
			}catch (AuthenticationException e) {
				//e.printStackTrace();
	            if(failureHandler!=null)
	            {
	            	failureHandler.onAuthenticationFailure(request, response, e);
	            }
	            return;
	        } catch (Exception e) {
	        	e.printStackTrace();
	        	response.sendRedirect(request.getContextPath()+entrypoint);
	            return;
			}
			
			
			
			
		}
		filterChain.doFilter(request0, response1);
	}

	public String getRequesturl() {
		return requesturl;
	}

	public void setRequesturl(String requesturl) {
		this.requesturl = requesturl;
	}

	public String getEntrypoint() {
		return entrypoint;
	}

	public void setEntrypoint(String entrypoint) {
		this.entrypoint = entrypoint;
	}

	public IAuthenticationManager getAuthenticationManager() {
		return authenticationManager;
	}

	public void setAuthenticationManager(IAuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	public IAuthenticationSuccessHandler getSuccessHandler() {
		return successHandler;
	}

	public void setSuccessHandler(IAuthenticationSuccessHandler successHandler) {
		this.successHandler = successHandler;
	}

	public IAuthenticationFailureHandler getFailureHandler() {
		return failureHandler;
	}

	public void setFailureHandler(IAuthenticationFailureHandler failureHandler) {
		this.failureHandler = failureHandler;
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