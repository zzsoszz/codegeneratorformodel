package com.bxtel.security5.filter;
/*
 * AbstractRememberMeServices
 * 

http://javacrazyer.iteye.com/blog/769896
http://javacrazyer.iteye.com/blog/769896

 */
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
public class RememberMeFiilter  extends GenericFilterBean {
	public static final String DELIMITER=":";
	public static final String SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY = "SECURITY_REMEMBER_ME_COOKIE";
	public String requesturl="/j_spring_security_check";
	@Autowired
	public IAuthenticationManager  authenticationManager;
	@Autowired
	public IAuthenticationSuccessHandler successHandler = null;
	@Autowired
    public IAuthenticationFailureHandler failureHandler = null;
	public String getRequesturl() {
		return requesturl;
	}

	public void setRequesturl(String requesturl) {
		this.requesturl = requesturl;
	}
   
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain filterChain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		String cookieValue=extractRememberMeCookie(request);
		
		// && 
		if(request.getRequestURI().endsWith(requesturl) && cookieValue!=null)
		{
			try{
				String username=null;
				String password=null;
				String cookieAsPlainText = new String(Base64.decode(cookieValue.getBytes()));
				String cookieAsPlainTextArray[]=cookieAsPlainText.split(DELIMITER);
				Long expiretime=new Long(cookieAsPlainTextArray[0]).longValue();
				//token是否超时
				if(expiretime>System.currentTimeMillis())
				{
					username= new String(Base64.decode(cookieAsPlainTextArray[1].getBytes()));
					password= cookieAsPlainTextArray[2];
					try
					{
						password=ThreeDesHelper2.decode(password);
					}
					catch (Exception e) 
					{
						e.printStackTrace();
					}
					
					if(username==null || password ==null || password.isEmpty() || username.isEmpty()  )
					{
						throw new UsernameNotFoundException("username and password must not be null");
					}
					UserNamePaswordAuthenticationRequest authRequest = new UserNamePaswordAuthenticationRequest(username, password);
					IAuthenticationResponse authResult = authenticationManager.authenticate(authRequest);
					if(authResult.isAuthenticated())
					{
						  if(successHandler!=null)
						  {
							successHandler.onAuthenticationSuccess(request, response, authResult);
						  }
					}
				}else
				{
					//System.out.println("尝试其他方式登录");
				}
			}catch(AuthenticationException ex)
			{
				//认证失败,删除cookie
				Cookie cookie = new Cookie(SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY, null);
			    cookie.setMaxAge(0);
			    cookie.setPath(getCookiePath(request));
			    response.addCookie(cookie);
				throw ex;
			}
		}
		filterChain.doFilter(arg0, arg1);
	}

	
	public static String getCookiePath(HttpServletRequest request) {
	        String contextPath = request.getContextPath();
	        return contextPath.length() > 0 ? contextPath : "/";
	}
    protected String extractRememberMeCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        if ((cookies == null) || (cookies.length == 0)) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }
    protected String encodeCookie(String[] cookieTokens) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i < cookieTokens.length; i++) {
            sb.append(cookieTokens[i]);

            if (i < cookieTokens.length - 1) {
                sb.append(DELIMITER);
            }
        }
        String value = sb.toString();
        sb = new StringBuilder(new String(Base64.encode(value.getBytes())));
        while (sb.charAt(sb.length() - 1) == '=') {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
	
}
