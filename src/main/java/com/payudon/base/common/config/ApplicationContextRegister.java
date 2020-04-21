package com.payudon.base.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextRegister implements ApplicationContextAware {
	private static Logger logger = LoggerFactory.getLogger(ApplicationContextRegister.class);
	private static ApplicationContext APPLICATION_CONTEXT;

	/**
	 * 设置spring上下文
	 * 
	 * @param applicationContext
	 *            spring上下文
	 * @throws BeansException
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		logger.debug("ApplicationContext registed-->{}", applicationContext);
		APPLICATION_CONTEXT = applicationContext;
	}

	/**
	 * 获取容器
	 * 
	 * @return
	 */
	public static ApplicationContext getApplicationContext() {
		return APPLICATION_CONTEXT;
	}

	/**
	 * 获取容器对象
	 * 
	 * @param type
	 * @param <T>
	 * @return
	 */
	public static <T> T getBean(Class<T> type) {
		return APPLICATION_CONTEXT.getBean(type);
	}
	/**
	 * 通过类型和bean名字获取容器对象
	 * @Title: getBean 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param clazz
	 * @param @param beanName
	 * @param @return    设定文件 
	 * @return T    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月6日 上午9:22:32
	 */
	public static <T> T getBean(Class<T> clazz,String beanName) {
		return APPLICATION_CONTEXT.getBean(clazz, beanName);
	}
	/**
	 * 通过bean名字获取容器对象
	 * @Title: getBean 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param beanName
	 * @param @return    设定文件 
	 * @return Object    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月6日 上午9:25:06
	 */
	public static <T> Object getBean(String beanName) {
		return APPLICATION_CONTEXT.getBean(beanName);
	}
}
