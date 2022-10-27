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
package net.renfei.server.service;

import net.renfei.cloudflare.Cloudflare;
import net.renfei.cloudflare.entity.common.ApiToken;
import net.renfei.cloudflare.entity.common.Response;
import net.renfei.cloudflare.entity.firewall.FirewallRule;
import net.renfei.common.core.config.SystemConfig;
import net.renfei.common.core.service.EmailService;
import net.renfei.common.core.service.aliyun.AliyunService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 访问频率超限处理
 *
 * @author renfei
 */
@Service
public class AccessLimitBlockService {
    private final static Logger logger = LoggerFactory.getLogger(AccessLimitBlockService.class);
    private final SystemConfig systemConfig;
    private final EmailService emailService;
    private final AliyunService aliyunService;

    public AccessLimitBlockService(SystemConfig systemConfig,
                                   EmailService emailService,
                                   AliyunService aliyunService) {
        this.systemConfig = systemConfig;
        this.emailService = emailService;
        this.aliyunService = aliyunService;
    }

    /**
     * 联动 WAF 防火墙拉黑IP地址
     *
     * @param ip
     */
    @Async
    public void block(String ip) {
        if (systemConfig.getAccessLimit().getBlacklistEnable()) {
            // 调用防火墙接口拉入黑名单
            // 阿里云CDN
            List<String> cdnDomainBlackList = aliyunService.getCdnDomainBlackList("www.renfei.net");
            cdnDomainBlackList.add(ip);
            aliyunService.setCdnDomainBlackList("www.renfei.net", cdnDomainBlackList);
            // Cloudflare
            ApiToken apiToken = new ApiToken(systemConfig.getCloudflare().getToken());
            Cloudflare cloudflare = new Cloudflare(apiToken);
            FirewallRule firewallRule = new FirewallRule();
            firewallRule.setMode("managed_challenge");
            firewallRule.setNotes("renfeid program automatic setting.");
            FirewallRule.Configuration configuration = new FirewallRule.Configuration();
            configuration.setTarget("ip");
            configuration.setValue(ip);
            firewallRule.setConfiguration(configuration);
            try {
                Response<FirewallRule> response = cloudflare.firewall().createAccessRuleByAccount(systemConfig.getCloudflare().getAccountId(), firewallRule);
                if (response.getSuccess()) {
                    logger.warn("IP address {} reaches the blacklist threshold.", ip);
                    notice(systemConfig.getAccessLimit().getNoticeEmail(),
                            String.format("IP地址：%s 由于超过设置的访问速率阈值，已经被WAF防火墙联动设置为黑名单，请知悉。", ip));
                } else {
                    if (response.getMessages() != null && !response.getMessages().isEmpty()) {
                        logger.error(response.getMessages().toString());
                        notice(systemConfig.getAccessLimit().getNoticeEmail(),
                                String.format("IP地址：%s 超过设置的访问速率阈值，调用WAF防火墙联动接口失败！失败信息：%s",
                                        ip, response.getMessages().toString()));
                    } else if (response.getErrors() != null && !response.getErrors().isEmpty()) {
                        logger.error(response.getErrors().get(0).getMessage());
                        notice(systemConfig.getAccessLimit().getNoticeEmail(),
                                String.format("IP地址：%s 超过设置的访问速率阈值，调用WAF防火墙联动接口失败！失败信息：%s",
                                        ip, response.getErrors().get(0).getMessage()));
                    }
                    logger.warn("IP address {} blacklist setting failed.", ip);
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                notice(systemConfig.getAccessLimit().getNoticeEmail(),
                        String.format("IP地址：%s 超过设置的访问速率阈值，调用WAF防火墙联动接口失败！失败信息：%s",
                                ip, e.getMessage()));
            }
        }
    }

    private void notice(String email, String contents) {
        try {
            emailService.send(email, "管理员", "WAF防火墙联动通知", contents);
        } catch (Exception ignored) {
        }
    }
}
