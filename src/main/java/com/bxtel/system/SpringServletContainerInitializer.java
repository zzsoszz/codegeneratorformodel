//package com.bxtel.system;
//
//import java.lang.reflect.Modifier;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.ServiceLoader;
//import java.util.Set;
//import javax.servlet.ServletContainerInitializer;
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.HandlesTypes;
//import org.springframework.core.annotation.AnnotationAwareOrderComparator;
//import org.springframework.web.WebApplicationInitializer;
//@HandlesTypes(WebApplicationInitializer.class)
// 从中可以看出，WebApplicationInitializer才是我们需要关心的接口，我们只需要将相应的servlet，filter，listener等硬编码到该接口的实现类中即可。比如：
//public class SpringServletContainerInitializer implements ServletContainerInitializer {
//        @Override
//	public void onStartup(Set<Class<?>> webAppInitializerClasses, ServletContext servletContext)
//			throws ServletException {
//
//		List<WebApplicationInitializer> initializers = new LinkedList<WebApplicationInitializer>();
//
//		if (webAppInitializerClasses != null) {
//			for (Class<?> waiClass : webAppInitializerClasses) {
//				// Be defensive: Some servlet containers provide us with invalid classes,
//				// no matter what @HandlesTypes says...
//				if (!waiClass.isInterface() && !Modifier.isAbstract(waiClass.getModifiers()) &&
//						WebApplicationInitializer.class.isAssignableFrom(waiClass)) {
//					try {
//						initializers.add((WebApplicationInitializer) waiClass.newInstance());
//					}
//					catch (Throwable ex) {
//						throw new ServletException("Failed to instantiate WebApplicationInitializer class", ex);
//					}
//				}
//			}
//		}
//
//		if (initializers.isEmpty()) {
//			servletContext.log("No Spring WebApplicationInitializer types detected on classpath");
//			return;
//		}
//
//		AnnotationAwareOrderComparator.sort(initializers);
//		servletContext.log("Spring WebApplicationInitializers detected on classpath: " + initializers);
//
//		for (WebApplicationInitializer initializer : initializers) {
//			initializer.onStartup(servletContext);
//		}
//	}
//
//}
//
