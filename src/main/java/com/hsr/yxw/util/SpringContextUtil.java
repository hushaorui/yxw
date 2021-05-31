package com.hsr.yxw.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * This class saves a map of spring-bean ids to their corresponding interfaces. <br/>
 * Any bean-lookup can use this class getBeanId method to obtain a spring bean only specifying the interface class. <br/>
 * The bean-id-map of this class must be consistent <br/>
 * to the applicationContext.xml file.
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {
	private static ApplicationContext applicationContext;

	/**
	 * implement ApplicationContextAware interface callback method to setup the context environment
	 */
	public void setApplicationContext(ApplicationContext context) {
		applicationContext = context;
	}

	/**
	 * get bean object
	 * 
	 * @return Object registered bean instance
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		return (T) applicationContext.getBean(name);
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<?> name) {
		return (T) applicationContext.getBean(name);
	}
}