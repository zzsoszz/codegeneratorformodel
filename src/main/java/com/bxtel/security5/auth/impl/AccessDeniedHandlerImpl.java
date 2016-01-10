package com.bxtel.security5.auth.impl;

import java.io.IOException;

import java.io.PrintWriter;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Component;

import com.bxtel.commons.Response;
import com.bxtel.security5.auth.AccessDeniedHandler;
import com.bxtel.security5.auth.exceiption.AccessDeniedException;
import com.bxtel.security5.filter.SecurityConfig;
import com.fasterxml.jackson.databind.ObjectMapper;

import dinamica.util.JsonHelper;
import dinamica.util.LogHelper;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    protected static final Log logger = LogFactory.getLog(AccessDeniedHandlerImpl.class);

    public AccessDeniedHandlerImpl() {
    	
    }

    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        if (!response.isCommitted()) {
        	 boolean isAjax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));  
        	 if (isAjax) {
        		response.setContentType("application/json;charset=UTF-8");  
    			response.setHeader("Pragma", "No-cache");  
    			response.setHeader("Cache-Control", "no-cache");  
    			response.setDateHeader("Expires", 0);  
     			response.setContentType("application/json");
     			PrintWriter out = response.getWriter();
     			Response<String> kv=new Response<String>();  
     			kv.setReturncode("00000002");
				kv.setReturnmsg("权限被拒");
				ObjectMapper om=JsonHelper.getObjectMapperInstance();
				out.print(om.writeValueAsString(kv)); 
				out.flush();
     			out.close();
     			return;
     		}
        	else {
                // Put exception into request scope (perhaps of use to a view)
                request.setAttribute(WebAttributes.ACCESS_DENIED_403, accessDeniedException);
                request.setAttribute("errorDetails", accessDeniedException.getMessage());
                request.setAttribute("errorTrace",  LogHelper.getTrace(accessDeniedException));
                // Set the 403 status code.
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                // forward to error page.
                RequestDispatcher dispatcher = request.getRequestDispatcher(SecurityConfig.noprivilegeurl);
                dispatcher.forward(request, response);
            }
        }
    }
	public static Log getLogger() {
		return logger;
	}

}