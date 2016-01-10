/* Copyright 2004, 2005, 2006 Acegi Technology Pty Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bxtel.security5.auth.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.security.web.util.AntPathRequestMatcher;
import org.springframework.security.web.util.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import com.bxtel.security5.auth.IConfigAttribute;
import com.bxtel.security5.auth.ISecurityMetadataSource;
import com.bxtel.user.dao.SecurityDataRepository;
import com.bxtel.user.model.SecurityData;
@Component
public class SecurityMetadataSourceImpl implements InitializingBean,ISecurityMetadataSource,BeanPostProcessor//,InitializingBean,BeanNameAware,BeanFactoryAware,ApplicationContextAware,DiposibleBean
{
	
    protected final Log logger = LogFactory.getLog(getClass());
    
    @Autowired
    SecurityDataRepository dao;
    
    LinkedHashMap<RequestMatcher, Collection<IConfigAttribute>> requestMap;
    
    SecurityMetadataSourceImpl()
    {
    	 
    }
    protected void buildRequestMap() {
		LinkedHashMap<RequestMatcher, Collection<IConfigAttribute>> requestMap = null;
		requestMap = new LinkedHashMap<RequestMatcher, Collection<IConfigAttribute>>();
		Iterable<SecurityData> resourceMap = dao.findAll();
		for (SecurityData one : resourceMap) {
			String key = one.getPath();
			String[] roles = StringUtils.delimitedListToStringArray(one.getRolelist(), ",");//role
			Collection<IConfigAttribute>  roleslist=new ArrayList<IConfigAttribute>();
			for(int i=0;i<roles.length;i++)
			{
				ConfigAttributeImpl e=new ConfigAttributeImpl(roles[i]);
				roleslist.add((IConfigAttribute) e);
			}
			requestMap.put(new AntPathRequestMatcher(key),roleslist);
		}
		this.requestMap=requestMap;
	}
    public Collection<IConfigAttribute> getAttributes(Object object) {
        final HttpServletRequest request = (HttpServletRequest)object;
        for (Map.Entry<RequestMatcher, Collection<IConfigAttribute>> entry : requestMap.entrySet()) {
            if (entry.getKey().matches(request)) {
                return entry.getValue();
            }
        }
        return null;
    }
    
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		buildRequestMap();
	}
}
