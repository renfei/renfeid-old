package net.renfei.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.List;

/**
 * 自定义配置
 * 来自 application.yml 配置文件
 *
 * @author renfei
 */
@Data
@Order(0)
@Configuration
@ConfigurationProperties(prefix = "system")
public class SystemConfig {
    private String active;
    private String version;
    private String buildTime;
    private String siteDomainName;
    private boolean enableRedis;
    private String globalAesKey;
    private String leafKey;
    private String ownerUserName;
    private String siteName;
    private PageHead pageHead;
    private PageFooter pageFooter;
    private boolean showFriendlyLink;
    private Long defaultCacheSeconds;
    private String ip2LocationBinFile;
    private String ip2LocationBinFileV6;
    private Leaf leaf;
    private ThreadPool threadPool;
    private Baidu baidu;
    private Google google;

    @Data
    public static class PageHead{
        private String author;
        private String copyright;
        private List<String> dnsPrefetch;
        private String fbAppId;
        private String fbPages;
        private String favicon;
        private String appleTouchIcon;
        private List<String> css;
    }

    @Data
    public static class PageFooter{
        private List<String> jss;
    }

    @Data
    public static class Leaf {
        private Segment segment;
        private Snowflake snowflake;

        @Data
        public static class Segment {
            private Boolean enable;
        }

        @Data
        public static class Snowflake {
            private Boolean enable;
            private Zk zk;
            private Integer port;

            @Data
            public static class Zk {
                private String address;
            }
        }
    }

    @Data
    public static class ThreadPool {
        /**
         * 核心线程数
         */
        private int corePoolSize;

        /**
         * 最大线程数
         */
        private int maxPoolSize;

        /**
         * 线程空闲时间
         */
        private int keepAliveSeconds;

        /**
         * 任务队列容量（阻塞队列）
         */
        private int queueCapacity;
    }

    @Data
    public static class Baidu{
        private String tongji;
    }

    @Data
    public static class Google{
        private String ads;
        private String analytics;
    }
}
