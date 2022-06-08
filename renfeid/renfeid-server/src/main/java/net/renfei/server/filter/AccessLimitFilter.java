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
package net.renfei.server.filter;

import net.renfei.cloudflare.Cloudflare;
import net.renfei.cloudflare.entity.common.ApiToken;
import net.renfei.cloudflare.entity.common.Response;
import net.renfei.cloudflare.entity.firewall.FirewallRule;
import net.renfei.common.api.constant.APIResult;
import net.renfei.common.api.constant.enums.StateCodeEnum;
import net.renfei.common.api.utils.JacksonUtil;
import net.renfei.common.core.config.RedisConfig;
import net.renfei.common.core.config.SystemConfig;
import net.renfei.common.core.utils.IpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.concurrent.TimeUnit;

/**
 * 访问频次限制
 *
 * @author renfei
 */
@Order(1)
@Component
public class AccessLimitFilter implements Filter {
    private final static Logger logger = LoggerFactory.getLogger(AccessLimitFilter.class);
    private final static String REDIS_KEY = RedisConfig.REDIS_KEY_DATABASE + ":limit:access";
    private final SystemConfig systemConfig;
    private final RedisTemplate<String, Object> redisTemplate;

    public AccessLimitFilter(SystemConfig systemConfig,
                             RedisTemplate<String, Object> redisTemplate) {
        this.systemConfig = systemConfig;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws ServletException, IOException {
        if (systemConfig.getAccessLimit().getEnable()) {
            if (servletRequest instanceof HttpServletRequest && servletResponse instanceof HttpServletResponse) {
                HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
                HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
                String ip = IpUtils.getIpAddress(httpRequest);
                String key = REDIS_KEY + ":" + ip;
                //当前访问次数
                Object value = redisTemplate.opsForValue().get(key);
                if (value == null) {
                    //第一次访问
                    redisTemplate.opsForValue().set(key, 1, systemConfig.getAccessLimit().getTime(), TimeUnit.SECONDS);
                } else {
                    //对应key值自增
                    redisTemplate.opsForValue().increment(key);
                    boolean block = false;
                    Integer currentCnt = (Integer) value;
                    Long apiLimit = systemConfig.getAccessLimit().getApiRate()
                            > systemConfig.getAccessLimit().getGlobalRate()
                            ? systemConfig.getAccessLimit().getGlobalRate()
                            : systemConfig.getAccessLimit().getApiRate();
                    if (currentCnt > systemConfig.getAccessLimit().getBlacklistRate()) {
                        // 达到拉黑的阈值
                        block = true;
                        if (systemConfig.getAccessLimit().getBlacklistEnable()) {
                            // 调用防火墙接口拉入黑名单
                            // 目前只支持Cloudflare，百度云加速的API需要代理商才能调用
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
                                } else {
                                    if (response.getMessages() != null && !response.getMessages().isEmpty()) {
                                        logger.error(response.getMessages().toString());
                                    } else if (response.getErrors() != null && !response.getErrors().isEmpty()) {
                                        logger.error(response.getErrors().toString());
                                    }
                                    logger.warn("IP address {} blacklist setting failed.", ip);
                                }
                            } catch (Exception e) {
                                logger.error(e.getMessage(), e);
                            }
                        }
                    } else if (httpRequest.getRequestURI().toLowerCase().startsWith("/api/")) {
                        if (currentCnt > apiLimit) {
                            block = true;
                            logger.info("IP address {} reached the threshold for restricted access(API Access Limit).", ip);
                        }
                    } else if (currentCnt > systemConfig.getAccessLimit().getGlobalRate()) {
                        block = true;
                        logger.info("IP address {} reached the threshold for restricted access(Global Access Limit).", ip);
                    }
                    if (block) {
                        APIResult<String> result = APIResult.builder()
                                .code(StateCodeEnum.NotModified)
                                .message("Your access frequency is too fast. Please try again later.")
                                .data("你的访问频率过快，请稍后再试。")
                                .build();
                        responseResult(httpResponse, result);
                        return;
                    }
                }
            } else {
                logger.warn("AccessLimitFilter just supports HTTP requests");
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    private void responseResult(HttpServletResponse response, APIResult<String> result) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "application/json");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json;charset=UTF-8");

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(response.getOutputStream()))) {
            writer.write(JacksonUtil.obj2String(result));
            writer.flush();
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }
}
