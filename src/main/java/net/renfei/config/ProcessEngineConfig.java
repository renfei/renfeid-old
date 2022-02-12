package net.renfei.config;

import net.renfei.utils.SentryUtils;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 流程引擎配置
 *
 * @author renfei
 */
@Configuration
public class ProcessEngineConfig {
    private static final Logger logger = LoggerFactory.getLogger(ProcessEngineConfig.class);

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.mail.host}")
    private String emailHost;

    @Value("${spring.mail.password}")
    private String emailPassword;

    @Value("${spring.mail.username}")
    private String emailUserName;

    /**
     * 初始化流程引擎
     *
     * @return
     */
    @Primary
    @Bean(name = "processEngine")
    public ProcessEngine initProcessEngine() {
        logger.info("=============================ProcessEngineBegin=============================");
        // 这里没有单独对流程引擎中的 8 个核心服务做初始化，
        // 是因为使用 flowable-spring-boot-starter 依赖，会自动帮忙注册好，不需要自己再注册，直接使用即可
        // 流程引擎配置
        ProcessEngineConfiguration cfg = null;

        try {
            cfg = new StandaloneProcessEngineConfiguration()
                    .setJdbcUrl(url)
                    .setJdbcUsername(username)
                    .setJdbcPassword(password)
                    .setJdbcDriver(driverClassName)
                    /*
                     * 创建完数据库后，关闭自动更新。
                     * 原因是更新的标准并非是你引入的流程引擎的版本，而是官方发布的版本，所以如果一直开启，
                     * 以后重启之类的可能导致提示版本升级失败，毕竟你的依赖版本并没有升级。
                     */
                    // 初始化基础表，不需要的可以改为 DB_SCHEMA_UPDATE_FALSE
                    .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE)
                    // 默认邮箱配置
                    // 发邮件的主机地址
                    .setMailServerHost(emailHost)
                    // POP3/SMTP服务的授权码
                    .setMailServerPassword(emailPassword)
                    // 默认发件人
                    .setMailServerDefaultFrom(emailUserName)
                    // 设置发件人用户名
                    .setMailServerUsername("管理员")
                    // 解决流程图乱码
                    .setActivityFontName("宋体")
                    .setLabelFontName("宋体")
                    .setAnnotationFontName("宋体");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            SentryUtils.captureException(e);
        }
        // 初始化流程引擎对象
        assert cfg != null;
        ProcessEngine processEngine = cfg.buildProcessEngine();
        logger.info("=============================ProcessEngineEnd=============================");
        return processEngine;
    }
}
