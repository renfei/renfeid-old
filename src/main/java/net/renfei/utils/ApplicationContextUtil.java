package net.renfei.utils;

import net.renfei.config.SystemConfig;
import org.springframework.context.ApplicationContext;

/**
 * 应用程序上下文工具
 * 从这里可以获取应用程序上下文
 *
 * @author renfei
 */
public class ApplicationContextUtil {
    private static SystemConfig systemConfig;
    private static ApplicationContext applicationContext;
    private static final int MAX_ATTEMPTS = 10000;

    public static void setApplicationContext(ApplicationContext applicationContext) {
        ApplicationContextUtil.applicationContext = applicationContext;
    }

    public static void setSystemConfig(SystemConfig systemConfig) {
        ApplicationContextUtil.systemConfig = systemConfig;
    }

    public static Object getBean(String beanName) {
        if ("systemConfig".equals(beanName)) {
            return systemConfig;
        }
        for (int i = 0;
             i < MAX_ATTEMPTS && (applicationContext == null || applicationContext.getBean(beanName) == null);
             i++) {
            Thread.yield();
        }
        return applicationContext != null ? applicationContext.getBean(beanName) : null;
    }
}
