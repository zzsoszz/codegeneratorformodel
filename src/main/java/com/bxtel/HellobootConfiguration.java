package com.bxtel;

import java.io.IOException;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.Filter;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
//import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.web.servlet.DispatcherServlet;

import com.bxtel.security5.filter.FilterSecurityInterceptorFilter;
import com.bxtel.security5.filter.LogoutFilter;
import com.bxtel.security5.filter.MyExceptionTranslationFilter;
import com.bxtel.security5.filter.RememberMeFiilter;
import com.bxtel.security5.filter.UsernamePasswordLoginFilter;
@Configuration
public class HellobootConfiguration  implements CachingConfigurer {
	
	@Autowired
    private DataSource dataSource;
	
	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
	    TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
	    factory.setPort(9000);//119.254.84.182
	    factory.setSessionTimeout(10, TimeUnit.MINUTES);
	    factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/notfound.html"));
	    return factory;
	}
	@Autowired
	UsernamePasswordLoginFilter  usernamePasswordLoginFilter;
	
	@Autowired
	RememberMeFiilter  rememberMeFiilter;
	
	@Autowired
	MyExceptionTranslationFilter  myExceptionTranslationFilter;
	
	@Autowired
	FilterSecurityInterceptorFilter  filterSecurityInterceptorFilter;
	
	@Autowired
	LogoutFilter  logoutFilter;
	
//	@Bean(name="springSecurityFilterChain2")
//	public FilterChainProxy filterChainProxy() {
//		org.springframework.security.web.FilterChainProxy f=new org.springframework.security.web.FilterChainProxy();
//		Map<RequestMatcher, List<Filter>> filterChainMap = new HashMap<RequestMatcher, List<Filter>>() ;
//		List<Filter> none=new ArrayList<Filter>();
//		filterChainMap.put(new AntPathRequestMatcher("/js/**"), none);
//		filterChainMap.put(new AntPathRequestMatcher("/css/**"), none);
//		filterChainMap.put(new AntPathRequestMatcher("/images/**"), none);
//		filterChainMap.put(new AntPathRequestMatcher("/js/**"), none);
//		filterChainMap.put(new AntPathRequestMatcher("/**/*.jsp"),none);
//		List<Filter> filterlist=new ArrayList<Filter>();
////		filterlist.add(rememberMeFiilter);
////		filterlist.add(usernamePasswordLoginFilter);
////		filterlist.add(logoutFilter);
////		filterlist.add(myExceptionTranslationFilter);
//		filterlist.add(filterSecurityInterceptorFilter);
//		filterChainMap.put(new AntPathRequestMatcher("/**"), filterlist);
//		f.setFilterChainMap(filterChainMap);
//	    return f;
//	}
	
	/*
	 * Spring Cache抽象详解
	 *http://jinnianshilongnian.iteye.com/blog/2001040
	 */
	@Bean
    @Override
    public CacheManager cacheManager() {
        try {
            net.sf.ehcache.CacheManager ehcacheCacheManager = new net.sf.ehcache.CacheManager(new ClassPathResource("ehcache.xml").getInputStream());
            EhCacheCacheManager cacheCacheManager = new EhCacheCacheManager(ehcacheCacheManager);
            return cacheCacheManager;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
	
    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return new SimpleKeyGenerator();
    }



    @Bean  
    @Override  
    public CacheResolver cacheResolver() {  
        return new MyCacheResolver();  
    }


	@Override  
    public CacheErrorHandler errorHandler() {  
        return new CacheErrorHandler() {  
            @Override  
            public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {  
                System.out.println("cache get error");  
            }  
            @Override  
            public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {  
                System.out.println("cache put error");  
            }  
  
            @Override  
            public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {  
                System.out.println("cache evict error");  
            }  
  
            @Override  
            public void handleCacheClearError(RuntimeException exception, Cache cache) {  
                System.out.println("cache clear error");  
            }  
        };  
    }  
	
//	 /*
//     * ehcache 主要的管理器
//     */
//    @Bean(name = "appEhCacheCacheManager")
//    public EhCacheCacheManager ehCacheCacheManager(EhCacheManagerFactoryBean bean){
//        return new EhCacheCacheManager(bean.getObject ());
//    }
//
//    /*
//     * 据shared与否的设置,Spring分别通过CacheManager.create()或new CacheManager()方式来创建一个ehcache基地.
//     */
//    @Bean
//    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean(){
//        EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean ();
//        cacheManagerFactoryBean.setConfigLocation (new ClassPathResource("ehcache.xml"));
//        cacheManagerFactoryBean.setShared (true);
//        return cacheManagerFactoryBean;
//    }
    
      
	  @Bean
	  public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
	    ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet);
	    registration.addUrlMappings("/hirest/*");
	    return registration;
	  }
	  
	 
//	  @Bean
//	  public FilterRegistrationBean userInsertingMdcFilterRegistrationBean() {
//	      FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//	      UserInsertingMdcFilter userFilter = new UserInsertingMdcFilter();
//	      registrationBean.setFilter(userFilter);
//	      registrationBean.setOrder(Integer.MAX_VALUE);
//	      return registrationBean;
//	  }
  
//	  @Bean
//	  public ServletRegistrationBean servletRegistrationBean() {
//	    return new ServletRegistrationBean(new SystemInitServlet(), "/signin");
//	  }
	 
    
//	  private void printStacks() {
//		StackTraceElement[] elements = Thread.currentThread().getStackTrace();
//		System.out.println("========================");
//
//		for (int i = 0; i < elements.length; i++) {
//			System.out.println(elements[i]);
//	 	}
//	  }
	 
//	  http://blog.sina.com.cn/s/blog_72ef7bea0102w8a2.html
//	  [Spring Boot] 使用多个Servlet [此博文包含图片] (2015-10-28 06:07:53)
//	  转载
//	  ▼
//	  标签： spring boot multiple servlet 	分类： Web.JavaEE
//
//	  当使用Spring boot的嵌入式servlet容器时，可以通过Spring bean或扫描Servlet组件的方式注册Servlet、Filter和Servlet规范的所有监听器(例如HttpSessionListener)。
//
//	      当urlMapping不是很复杂时，可以通过ServletRegistrationBean、 FilterRegistrationBean 和 ServletListenerRegistrationBean获得完整控制。如果bean实现了ServletContextInitializer接口的话则可以直接注册。
//	      当使用@ServletComponentScan扫描Servlet组件时，Servlet、过滤器和监听器可以是通过@WebServlet、@WebFilter和@WebListener自动注册
//
//	      
//	      
//	@Autowired
//	EntityManagerFactory emf;
//	
//	@Autowired
//	PlatformTransactionManager platformTransactionManager;
//	
//	

	
//	@Bean(name = "transactionManager")
//	public PlatformTransactionManager transactionManager() {
//	  JpaTransactionManager tm =   new JpaTransactionManager();
//      tm.setEntityManagerFactory(emf);
//      tm.setDataSource(dataSource);
//	  return tm;
//	}
//	
//	
//	@Bean(name = "transactionInterceptor")
//	public TransactionInterceptor transactionInterceptor() {
//	  org.springframework.transaction.interceptor.TransactionInterceptor tm =   new org.springframework.transaction.interceptor.TransactionInterceptor();
//	  org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource nameMatchTransactionAttributeSource =   new org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource();
//	  tm.setTransactionManager(platformTransactionManager);
//	  return tm;
//	}
	
}

//@Bean
//public User getPerson()
//{
//	return new User("qingtian","123456");
//}
//
//@Bean
//public FilterRegistrationBean mdcFilterRegistrationBean() {
//	FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//	MDCInsertingServletFilter loggingFilter = new MDCInsertingServletFilter();
//	registrationBean.setFilter(loggingFilter);
//	registrationBean.setOrder(Integer.MAX_VALUE - 1);
//	return registrationBean;
//}
//@Bean(name = "entityManagerFactory")
//public LocalContainerEntityManagerFactoryBean emf() {
//  LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
//  emf.setDataSource(dataSource);
//  emf.setPackagesToScan(
//      new String[] {"your.package"});
//  emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//  return emf;
//}
//
//@Autowired
//EntityManagerFactory emf;
//@Bean(name = "transactionManager")
//public PlatformTransactionManager transactionManager() {
//  JpaTransactionManager tm = 
//      new JpaTransactionManager();
//      tm.setEntityManagerFactory(emf);
//      tm.setDataSource(dataSource);
//  return tm;
//}
//

//http://stackoverflow.com/questions/26286736/how-to-verify-spring-aspecj-transactions
//@Configuration
//@EnableTransactionManagement(mode = AdviceMode.ASPECTJ)
//@ComponentScan
//@EnableAsync
//@EnableScheduling
//@EnableEntityLinks
//@EnableAspectJAutoProxy
//@EnableApiResources(apiPrefix = "")
//@EnableJpaRepositories(transactionManagerRef = "transactionManager")
//@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
//@EnableConfigurationProperties
//@EnableAutoConfiguration
//@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
//@EnableWebSocketMessageBroker
//@Slf4j

//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
//@EnableAutoConfiguration
//@ComponentScan(basePackages = { "com.geopro" })
//@EnableJpaRepositories(basePackages = { "com.geopro.repositories" })
//@EntityScan(basePackages = { "com.geopro.entities" })
//@EnableTransactionManagement(proxyTargetClass=true)//(mode=AdviceMode.ASPECTJ)//
//public class AppConfig {
//    @Bean
//    public HibernateJpaSessionFactoryBean sessionFactory(EntityManagerFactory emf) {
//         HibernateJpaSessionFactoryBean factory = new HibernateJpaSessionFactoryBean();
//         factory.setEntityManagerFactory(emf);
//         return factory;
//    }
//}
