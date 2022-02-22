package net.renfei.application;

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
        return applicationContext != null ? applicationContext.getBean(beanName) : null;
    }

    public static void checkSystemConfig(SystemConfig systemConfig){
        if(systemConfig==null){
            systemConfig = (SystemConfig) applicationContext.getBean("systemConfig");
        }
    }
}
