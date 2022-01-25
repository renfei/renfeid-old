package net.renfei.utils;

import net.renfei.config.SystemConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 应用程序上下文工具
 * 从这里可以获取应用程序上下文
 *
 * @author renfei
 */
@Component
public class ApplicationContextUtil implements ApplicationContextAware {
    private static SystemConfig systemConfig;
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
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
}
