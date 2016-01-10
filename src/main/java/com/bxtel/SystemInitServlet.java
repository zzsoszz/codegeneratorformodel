package com.bxtel;

import java.util.Enumeration;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.ApplicationContext;

import com.bxtel.commons.Request;
import com.bxtel.commons.SearchData;
import com.bxtel.sms.bo.SmsBO;
import com.bxtel.sms.controller.SmsController;
import com.bxtel.sms.model.Sms;


//@WebServlet(name="systemInitServlet")  
@WebServlet(value="/servlet/init-param", loadOnStartup=1, initParams={@WebInitParam(name="param1", value="value1")})  
public class SystemInitServlet extends HttpServlet{
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
		ApplicationContext   ctx= (ApplicationContext) servletContext.getAttribute("org.springframework.web.context.WebApplicationContext.ROOT");
		ListableBeanFactory cc = (ListableBeanFactory)ctx;
		Map<String, Object> aa = BeanFactoryUtils.beansOfTypeIncludingAncestors(cc, java.lang.Object.class);
		Iterator<String> it = aa.keySet().iterator();
		while(it.hasNext())
		{
			String key=it.next();//TongchengClient BxBankCardTBO
			System.out.println("key:"+key+"   value:"+aa.get(key));
		}
		SmsBO  smsbo=(SmsBO) ctx.getBean(SmsBO.class);
		Sms model=new Sms();
		model.setMobile("13730666347");
		model.setContent("hello boy !!!");
		model.setStatus("1");
		smsbo.add(model);
		
		
		SmsController  sms=(SmsController) ctx.getBean(SmsController.class);
		Request req=new Request();
		req.setImsi("123456");
		req.setTimestamp("20121314");
		req.setVersion("v1.0.1");
		SearchData data=new SearchData();
		data.setPage(0);
		data.setPagesize(10);
		Map<String, Object> search=new HashMap<String, Object>();
		search.put("EQ_mobile", "13730666347");
		data.setSearch(search);
		req.setData(data);
		try {
			System.out.println(sms.searchforjson(req));
			System.out.println(sms.searchforjson(req));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
