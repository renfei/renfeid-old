package net.renfei.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 自定义配置
 * 来自 application.yml 配置文件
 *
 * @author renfei
 */
@Data
@Component
@ConfigurationProperties(prefix = "system")
public class SystemConfig {
    private String active;
    private String version;
    private String buildTime;
    private String globalAesKey;
    private String siteName;
    private String ip2LocationBinFile;
    private String ip2LocationBinFileV6;
    private Leaf leaf;

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
}
