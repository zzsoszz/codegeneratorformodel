package com.bxtel.security5.filter;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.security.web.util.ThrowableAnalyzer;
import org.springframework.security.web.util.ThrowableCauseExtractor;
import org.springframework.stereotype.Component;

import com.bxtel.security5.auth.AccessDeniedHandler;
import com.bxtel.security5.auth.exceiption.AccessDeniedException;
import com.bxtel.security5.auth.exceiption.AuthenticationException;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.bxtel.commons.Response;
import dinamica.util.HttpHelper;
import dinamica.util.JsonHelper;

@Component
public class MyExceptionTranslationFilter  extends GenericFilterBean{
	
    private ThrowableAnalyzer throwableAnalyzer = new DefaultThrowableAnalyzer();
    
    private RequestCache requestCache = new HttpSessionRequestCache();
    
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;
    
    protected final Log logger = LogFactory.getLog(MyExceptionTranslationFilter.class);
    
	private void handleSpringSecurityException(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
	            RuntimeException exception) throws IOException, ServletException {
		 if (exception instanceof AuthenticationException) {
			 	boolean isAjax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
			 	if(isAjax)
			 	{
					Response<String> kv=new Response<String>();  
	     			kv.setReturncode("302");
					kv.setReturnmsg("权限被拒");
					kv.setData(request.getContextPath()+SecurityConfig.entrypoint);
					outputJson(request,response,kv);
			 	}else{
			 		requestCache.saveRequest(request, response);
			 		SavedRequest savedRequest = requestCache.getRequest(request, response);
			 		logger.debug("MyExceptionTranslationFilter saveurl:"+savedRequest.getRedirectUrl());
			 		if(HttpHelper.judgeIsWeiXin(request))
			 		{
			 			response.sendRedirect(request.getContextPath()+SecurityConfig.weixinentrypoint);
			 		}else if(HttpHelper.judgeIsMoblie(request))
			 		{
				 		response.sendRedirect(request.getContextPath()+SecurityConfig.mobileentrypoint);
			 		}else
			 		{
				 		response.sendRedirect(request.getContextPath()+SecurityConfig.entrypoint);
			 		}
			 	}
	     }
		 else if (exception instanceof AccessDeniedException) {
			   accessDeniedHandler.handle(request, response, (AccessDeniedException) exception);
         }
		 return ;
	}
 
	
	
	private void outputJson(HttpServletRequest request, HttpServletResponse response, Response<String> resp)	throws IOException, JsonGenerationException, JsonMappingException {
		//request.getSession(true); 可以不用创建session 因为是为登陆状态，不需要维持会话
		response.setContentType("application/json;charset=UTF-8");  
		response.setHeader("Pragma", "No-cache");  
		response.setHeader("Cache-Control", "no-cache");  
		response.setDateHeader("Expires", 0);
		PrintWriter out = response.getWriter();
		ObjectMapper om=JsonHelper.getObjectMapperInstance();
		out.print(om.writeValueAsString(resp)); 
		out.flush();
		out.close();
	}
	
	
    private static final class DefaultThrowableAnalyzer extends ThrowableAnalyzer {
        protected void initExtractorMap() {
            super.initExtractorMap();
            registerExtractor(ServletException.class, new ThrowableCauseExtractor() {
                public Throwable extractCause(Throwable throwable) {
                    ThrowableAnalyzer.verifyThrowableHierarchy(throwable, ServletException.class);
                    return ((ServletException) throwable).getRootCause();
                }
            });
        }
    }
    
	 
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        try {
            chain.doFilter(request, response);
            //logger.debug("Chain processed normally");
        }
        catch (IOException ex) {
            throw ex;
        }
        catch (Exception ex) {
            // Try to extract a SpringSecurityException from the stacktrace
            Throwable[] causeChain = throwableAnalyzer.determineCauseChain(ex);
            RuntimeException ase = (AuthenticationException) throwableAnalyzer.getFirstThrowableOfType(AuthenticationException.class, causeChain);
            
            if (ase == null) {
                ase = (AccessDeniedException)throwableAnalyzer.getFirstThrowableOfType(AccessDeniedException.class, causeChain);
            }
            if (ase != null) {
                handleSpringSecurityException(request, response, chain, ase);
            } else {
                // Rethrow ServletExceptions and RuntimeExceptions as-is
                if (ex instanceof ServletException) {
                    throw (ServletException) ex;
                }
                else if (ex instanceof RuntimeException) {
                    throw (RuntimeException) ex;
                }

                // Wrap other Exceptions. This shouldn't actually happen
                // as we've already covered all the possibilities for doFilter
                throw new RuntimeException(ex);
            }
        }
    }
}
