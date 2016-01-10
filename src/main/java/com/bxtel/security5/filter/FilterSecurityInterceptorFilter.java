package com.bxtel.security5.filter;

import java.io.IOException;



import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import com.bxtel.security5.auth.*;
import com.bxtel.security5.auth.exceiption.AccessDeniedException;
import com.bxtel.security5.auth.exceiption.UserNotLogin;


//public boolean supports(Class<?> clazz) {
//return FilterInvocation.class.isAssignableFrom(clazz);
//}

@Component
public class FilterSecurityInterceptorFilter extends GenericFilterBean  {
		
		private RequestCache requestCache = new HttpSessionRequestCache();
		
		private static final Log logger = LogFactory.getLog(FilterSecurityInterceptorFilter.class);
		
		//需要保护的资源
		@Resource
		private ISecurityMetadataSource securityMetadataSource;
		//不需要保护的资源
		private HashMap unSecurityMetadataSource;
		
		private boolean checkRole=true;
		
		//保护策略 SR 只保护安全资源  ALL保护所有资源
		public  String protectStrategy="SR";
		
		public String getProtectStrategy() {
			return protectStrategy;
		}

		public void setProtectStrategy(String protectStrategy) {
			this.protectStrategy = protectStrategy;
		}
		
		public boolean isCheckRole() {
			return checkRole;
		}

		public void setCheckRole(boolean checkRole) {
			this.checkRole = checkRole;
		}



		private String pathtype="ant";
		
		public String getPathtype() {
			return pathtype;
		}

		public void setPathtype(String pathtype) {
			this.pathtype = pathtype;
		}
		
		public HashMap getUnSecurityMetadataSource() {
			return unSecurityMetadataSource;
		}

		public void setUnSecurityMetadataSource(HashMap unSecurityMetadataSource) {
			this.unSecurityMetadataSource = unSecurityMetadataSource;
		}
		public ISecurityMetadataSource getSecurityMetadataSource() {
			return securityMetadataSource;
		}
		public void setSecurityMetadataSource(ISecurityMetadataSource securityMetadataSource) {
			this.securityMetadataSource = securityMetadataSource;
		}

		public void doFilter(ServletRequest request, ServletResponse response,FilterChain filterChain) throws IOException, ServletException {
			HttpServletRequest servletRequest = (HttpServletRequest) request;
			HttpServletResponse servletResponse = (HttpServletResponse) response;
			logger.debug("request url :"+servletRequest.getRequestURI());
			if("ALL".equals(getProtectStrategy()))
			{
				if(isNeedSecurityCheck(servletRequest,servletResponse)){
					if (logger.isDebugEnabled()) {
						logger.debug("安全检查:"+servletRequest.getRequestURL());
					}
					HttpSession ses = servletRequest.getSession(true);
				    if(ses==null)
					{
						throw new UserNotLogin("session is not be created");
					}
				    IAuthenticationResponse auth =(IAuthenticationResponse) ses.getAttribute("securitycontext");
					if(auth==null)
					{
						throw new UserNotLogin("user is not online");
					}
					if(auth==null || auth.isAuthenticated()==false)
					{
						throw new UserNotLogin("user is unAuthenticated");
					}
					Collection<IConfigAttribute> roles = securityMetadataSource.getAttributes(servletRequest);
					if(roles==null)
					{
						throw new AccessDeniedException("Access is denied : resource "+servletRequest.getRequestURL()+" have no roles ");
					}
					if(isCheckRole())
					{
						Collection<? extends IGrantedAuthority> authorities = auth.getAuthorities();
						if(authorities==null)
						{
						  	throw new AccessDeniedException("Access is denied : user have no privilege in path "+servletRequest.getRequestURI());
						}
						if(!decide(roles,authorities))
						{
							//验证失败跳转到failedurl指定的页面
							//servletResponse.sendRedirect(servletRequest.getContextPath()+failedurl);
							throw new AccessDeniedException("Access is denied");
						}
					}
					
				}else{
					logger.debug("url need not sercurity :"+servletRequest.getRequestURI());
				}
			}else{
				//只检查需要角色的资源
				Collection<IConfigAttribute> roles = securityMetadataSource.getAttributes(servletRequest);
				if(roles!=null)
				{
					    HttpSession ses = servletRequest.getSession(true);
					    if(ses==null)
						{
							throw new UserNotLogin("session is not be created");
						}
					    IAuthenticationResponse auth =(IAuthenticationResponse) ses.getAttribute("securitycontext");
						if(auth==null)
						{
							throw new UserNotLogin("user is not online");
						}
						if(auth==null || auth.isAuthenticated()==false)
						{
							throw new UserNotLogin("user is unAuthenticated");
						}
						if(isCheckRole())
						{
							Collection<? extends IGrantedAuthority> authorities = auth.getAuthorities();
							if(authorities==null)
							{
							  	throw new AccessDeniedException("Access is denied : user have no privilege in path "+servletRequest.getRequestURI());
							}
							if(!decide(roles,authorities))
							{
								//验证失败跳转到failedurl指定的页面
								//servletResponse.sendRedirect(servletRequest.getContextPath()+failedurl);
								throw new AccessDeniedException("Access is denied");
							}
						}
				}
				else{
					logger.debug("url need not sercurity :"+servletRequest.getRequestURI());
				}
				
			}
			filterChain.doFilter(request, response);
		}
		
		
		private boolean isNeedSecurityCheck(HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
			logger.debug(unSecurityMetadataSource.keySet().size());
			Iterator it = unSecurityMetadataSource.keySet().iterator();
			while(it.hasNext())
			{
				String key=(String) it.next();
				pathtype=(String) unSecurityMetadataSource.get(key);
				RequestMatcher  rm=null;
				if("regex".equals(pathtype))
				{
					rm=new RegexRequestMatcher(key,servletRequest.getMethod());
				}else
				{
					rm=new AntPathRequestMatcher(key);
				}
				if(rm.matches(servletRequest))
				{
					return false;
				}
			}
			return true;
		}
		public boolean decide(Collection<IConfigAttribute> configroles,Collection<? extends IGrantedAuthority> authorities)
		{
			for(IConfigAttribute config:configroles)
			{
				Iterator<? extends IGrantedAuthority> it = authorities.iterator();
				while(it.hasNext())
				{
					IGrantedAuthority ga = it.next();
					if(config.getAttribute().equals(ga.getAuthority()))
					{
						return true;
					}
				}
			}
			return false;
		}
}


/*
Spring security防用户重复登录 
使用Spring security如何防止用户的重复登录呢？如果用户账号已登录，这时再进行第二次或多次登录，需要阻止这样的多次登录。

一.在web.xml中配置listener

<listener>
      <listener-class>org.springframework.security.web.session.HttpSessionEventPublisher</listener-class>
</listener>
二.在security.xml中配置Hibernate ORM提供了三种继承映射策略
<session-management>
          <concurrency-control max-sessions="1" error-if-maximum-exceeded="true"/>
</session-management>
max-sessions表示最多允许多少次重复登录。如果没有配置error-if-maximum-exceeded，那么用户账号的第二次登录会使第一次登录失效，而配置了的话，那么第二次登录会被阻止。通常的做法是阻止第二次登录。
*/