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

import net.renfei.common.api.constant.APIResult;
import net.renfei.common.core.config.SystemConfig;
import net.renfei.common.core.utils.IpUtils;
import net.renfei.uaa.api.UserService;
import net.renfei.common.core.entity.UserDetail;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static net.renfei.common.api.constant.Constant.*;

/**
 * 用户身份认证过滤器
 *
 * @author renfei
 */
@Order(10)
@Component
public class AuthorizationFilter extends OncePerRequestFilter {
    private final SystemConfig systemConfig;
    private final UserService userService;

    public AuthorizationFilter(SystemConfig systemConfig, UserService userService) {
        this.systemConfig = systemConfig;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String auth = request.getHeader(HEADER_TOKEN_NAME);
        if (ObjectUtils.isEmpty(auth) || !auth.startsWith(HEADER_TOKEN)) {
            // 请求头中没有token
            filterChain.doFilter(request, response);
            return;
        }
        final String token = auth.split(" ")[1].trim();
        APIResult<UserDetail> userDetailApiResult;
        if (systemConfig.getBindingIp()) {
            userDetailApiResult = userService.getUserDetailByToken(token, IpUtils.getIpAddress(request));
        } else {
            userDetailApiResult = userService.getUserDetailByToken(token);
        }
        if (userDetailApiResult.getCode() != 200) {
            // token 无效
            filterChain.doFilter(request, response);
            return;
        }
        UserDetail userDetail = userDetailApiResult.getData();
        // 将用户信息放入安全上下文中
        UsernamePasswordAuthenticationToken
                authentication = new UsernamePasswordAuthenticationToken(
                userDetail, null,
                userDetail.getAuthorities()
        );
        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}
