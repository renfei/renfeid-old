package net.renfei.config;

import net.renfei.utils.ApplicationContextUtil;
import net.renfei.utils.SentryUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 重写Spring默认线程池配置
 *
 * @author renfei
 */
@Configuration
public class ThreadPoolConfig implements AsyncConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(ThreadPoolConfig.class);
    private final SystemConfig systemConfig;

    public ThreadPoolConfig(SystemConfig systemConfig) {
        this.systemConfig = systemConfig;
        ApplicationContextUtil.setSystemConfig(systemConfig);
    }

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //核心线程池大小
        assert systemConfig != null;
        executor.setCorePoolSize(systemConfig.getThreadPool().getCorePoolSize());
        //最大线程数
        executor.setMaxPoolSize(systemConfig.getThreadPool().getMaxPoolSize());
        //队列容量
        executor.setQueueCapacity(systemConfig.getThreadPool().getQueueCapacity());
        //活跃时间
        executor.setKeepAliveSeconds(systemConfig.getThreadPool().getKeepAliveSeconds());
        //线程名字前缀
        executor.setThreadNamePrefix("renfeid-thread-");
        /*
          当poolSize已达到maxPoolSize，如何处理新任务（是拒绝还是交由其它线程处理）
          CallerRunsPolicy：不在新线程中执行任务，而是由调用者所在的线程来执行
         */
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    /**
     * 异步任务中异常处理
     *
     * @return
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (ex, method, params) -> {
            logger.error("==========================" + ex.getMessage() + "=======================", ex);
            logger.error("exception method:" + method.getName());
            SentryUtils.captureException(ex);
        };
    }
}
