/*
 *   Copyright 2022 RenFei(i@renfei.net)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.renfei.common.core.config;

import lombok.Data;
import net.renfei.common.api.constant.enums.SecretLevelEnum;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.List;

/**
 * 系统配置
 *
 * @author renfei
 */
@Data
@Order(0)
//@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "system")
public class SystemConfig {
    /**
     * 允许最大长度用户名
     */
    public static final Integer MAX_USERNAME_LENGTH = 60;
    /**
     * 允许最大评论长度
     */
    public static final Integer MAX_COMMENT_LENGTH = 10240;
    /**
     * 允许最大链接长度
     */
    public static final Integer MAX_LINK_LENGTH = 300;
    private String active;
    private String version;
    private String buildTime;
    private String siteName;
    private String siteDomainName;
    private String superTubeUserName;
    private Boolean enableSuperTubeUser;
    private Boolean enableCache;
    private Boolean enablePostAuditing;
    private SecretLevelEnum maxSecretLevel;
    private Boolean enableSignUp;
    private Boolean bindingIp;
    private Jwt jwt;
    private UCenter uCenter;
    private List<String> authIgnore;
    private AWS aws;
    private Aliyun aliyun;
    private Leaf leaf;
    private AccessLimit accessLimit;
    private Cloudflare cloudflare;

    @Data
    public static class Jwt {
        private String secret;
        private String issuer;
        private Long expiration;
    }

    @Data
    public static class UCenter {
        private Boolean enable;
        private String api;
        private String key;
        private String appId;
        private String connect;
    }

    @Data
    public static class AWS {
        private String region;
        private String bucketName;
    }

    @Data
    public static class Aliyun {
        private String accessKeyId;
        private String accessKeySecret;
        private Oss oss;
        private Green green;
        private Sms sms;

        @Data
        public static class Oss {
            private String regionId;
            private String endpoint;
            private String bucketName;
        }

        @Data
        public static class Green {
            private String regionId;
        }

        @Data
        public static class Sms {
            private String endpoint;
            private String signName;
            private String templateCode;
        }
    }

    @Data
    public static class Leaf {
        private Integer port;
        private String zk;
    }

    @Data
    public static class AccessLimit {
        private Boolean enable;
        private Boolean blacklistEnable;
        private Long globalRate;
        private Long apiRate;
        private Long blacklistRate;
        private Long time;
    }

    @Data
    public static class Cloudflare {
        public String accountId;
        public String token;
    }
}
