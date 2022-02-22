package net.renfei.servlet;

import com.alibaba.druid.support.http.StatViewServlet;
import net.renfei.config.SystemConfig;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 手动注册 Servlet
 *
 * @author renfei
 */
@Configuration
public class ServletRegistration {
    private final SystemConfig systemConfig;

    public ServletRegistration(SystemConfig systemConfig) {
        this.systemConfig = systemConfig;
    }

    /**
     * 注册 Druid 的监控页面
     * @return
     */
    @Bean
    public ServletRegistrationBean statViewServlet() {
        // 注意！修改 net.renfei.config.SystemConfig.Druid.urlMappings 的地址以后
        // 需要到 net.renfei.config.SecurityConfig.configure 中修改 ignoringAntMatchers 忽略CSRF校验
        ServletRegistrationBean<StatViewServlet> bean =
                new ServletRegistrationBean<>(new StatViewServlet(), systemConfig.getDruid().getUrlMappings());
        //配置使用这个servlet需要的参数的值，因为druid提供的监控视图servlet StatViewServlet 需要配置一些必要的参数才能够正常的使用
        Map<String, String> initParameters = new HashMap<>();
        //配置登陆账号和密码
        initParameters.put("loginUsername", systemConfig.getDruid().getUserName());
        initParameters.put("loginPassword", systemConfig.getDruid().getPassword());
        //允许谁可以访问
        //allow这个key对应的value就是允许谁可以访问，如果为空，则所有人可以访问
        initParameters.put("allow", systemConfig.getDruid().getAllow());
        //禁止这个IP访问
        initParameters.put("deny", systemConfig.getDruid().getDeny());
        bean.setInitParameters(initParameters);
        return bean;
    }
}
