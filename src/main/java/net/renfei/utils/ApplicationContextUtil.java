package net.renfei.utils;

import org.springframework.context.ApplicationContext;

/**
 * 应用程序上下文工具
 * 从这里可以获取应用程序上下文
 *
 * @author renfei
 */
public class ApplicationContextUtil {
    private static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext applicationContext) {
        ApplicationContextUtil.applicationContext = applicationContext;
    }

    public static Object getBean(String beanName) {
        return applicationContext != null ? applicationContext.getBean(beanName) : null;
    }
}
