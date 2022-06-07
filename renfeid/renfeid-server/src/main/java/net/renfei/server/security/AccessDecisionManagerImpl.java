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
package net.renfei.server.security;

import net.renfei.common.core.config.SystemConfig;
import net.renfei.common.core.entity.UserDetail;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * 自定义权限决策管理器
 * 有了权限资源({@link FilterInvocationSecurityMetadataSourceImpl})，
 * 知道了当前访问的url需要的具体权限，接下来就是决策当前的访问是否能通过权限验证了
 *
 * @author renfei
 */
@Component
public class AccessDecisionManagerImpl implements AccessDecisionManager {
    private final SystemConfig systemConfig;

    public AccessDecisionManagerImpl(SystemConfig systemConfig) {
        this.systemConfig = systemConfig;
    }

    /**
     * 取当前用户的权限与这次请求的这个url需要的权限作对比，决定是否放行
     * auth 包含了当前的用户信息，包括拥有的权限,即之前登录时候存储的用户对象
     * object 就是{@link FilterInvocation}对象，可以得到request等web资源。
     * configAttributes 是本次访问需要的权限。即上一步的 {@link FilterInvocationSecurityMetadataSourceImpl} 中查询核对得到的权限列表
     *
     * @param authentication
     * @param object
     * @param collection
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object object,
                       Collection<ConfigAttribute> collection)
            throws AccessDeniedException, InsufficientAuthenticationException {
        // 【警告】如果 net.renfei.security.interceptor.FilterInvocationSecurityMetadataSourceImpl.getAttributes
        // 返回 null 就不会进入此方法，所以需要保护的资源一定要入库并设置所需的角色
        if (authentication == null) {
            throw new AccessDeniedException("Access Denied! 当前访问没有权限！");
        }
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        if (systemConfig.getAuthIgnore() != null && !systemConfig.getAuthIgnore().isEmpty()) {
            for (String url : systemConfig.getAuthIgnore()
            ) {
                // 需要忽略鉴权的地址，直接 return
                if (new AntPathRequestMatcher(url).matches(request)) {
                    return;
                }
            }
        }
        if (authentication.getPrincipal() instanceof UserDetail) {
            UserDetail user = (UserDetail) authentication.getPrincipal();
            if (systemConfig.getSuperTubeUserName().equals(user.getUsername())) {
                // 超管，不做校验
                return;
            }
        }
        //当前用户所具有的权限
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            for (ConfigAttribute configAttribute : collection) {
                //当前请求需要的权限
                String needRole = configAttribute.getAttribute();
                if (authority.getAuthority().equals(needRole)) {
                    return;
                }
            }
        }
        if (new AntPathRequestMatcher("/_/api/**").matches(request)) {
            //API接口，抛出禁止访问
            throw new AccessDeniedException("Access Denied! 权限不足！");
        }
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
