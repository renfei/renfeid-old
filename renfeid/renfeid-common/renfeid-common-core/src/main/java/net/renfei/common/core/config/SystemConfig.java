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
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
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
@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "system")
public class SystemConfig {
    private String active;
    private String version;
    private String buildTime;
    private String superTubeUserName;
    private Boolean bindingIp;
    private Jwt jwt;
    private List<String> authIgnore;
    private AWS aws;
    private Aliyun aliyun;

    @Data
    public static class Jwt{
        private String secret;
        private String issuer;
        private Long expiration;
    }

    @Data
    public static class AWS{
        private String region;
        private String bucketName;
    }

    @Data
    public static class Aliyun{
        private String accessKeyId;
        private String accessKeySecret;
        private Green green;

        @Data
        public static class Green{
            private String regionId;
        }
    }
}
