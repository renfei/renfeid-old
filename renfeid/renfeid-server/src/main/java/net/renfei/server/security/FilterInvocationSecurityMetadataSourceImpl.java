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

import net.renfei.uaa.api.RoleService;
import net.renfei.uaa.api.SystemApiService;
import net.renfei.uaa.api.entity.RoleDetail;
import net.renfei.uaa.api.entity.SystemApi;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 自定义权限资源过滤器，实现动态的权限验证
 * {@link FilterInvocationSecurityMetadataSource}（权限资源过滤器接口）继承了 {@link SecurityMetadataSource}（权限资源接口）
 * Spring Security是通过SecurityMetadataSource来加载访问时所需要的具体权限
 * 它的主要责任就是当访问一个url时，返回这个url所需要的访问权限
 *
 * @author renfei
 */
@Component
public class FilterInvocationSecurityMetadataSourceImpl implements FilterInvocationSecurityMetadataSource {
    private final AntPathMatcher antPathMatcher;
    private final List<SystemApi> sysApiList;
    private final RoleService roleService;

    public FilterInvocationSecurityMetadataSourceImpl(SystemApiService systemApiService,
                                                      RoleService roleService) {
        this.roleService = roleService;
        this.antPathMatcher = new AntPathMatcher();
        // 从数据库加载权限配置
        this.sysApiList = systemApiService.allSystemApiList().getData().getData();
    }

    /**
     * 返回本次访问需要的权限，可以有多个权限
     * 其实就是通过url地址获取 角色信息的方法
     *
     * @param object {@link FilterInvocation}
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation filterInvocation = (FilterInvocation) object;
        HttpServletRequest request = filterInvocation.getHttpRequest();
        String requestMethod = request.getMethod().toLowerCase();
        String requestUrl = filterInvocation.getRequest().getRequestURI();
        if (requestUrl.startsWith("/_/api/")) {
            List<ConfigAttribute> configAttributes = new CopyOnWriteArrayList<>();
            for (SystemApi sysApi : sysApiList
            ) {
                // 遍历系统所有的资源进行匹配
                String method = sysApi.getMethodName().toLowerCase();
                String url = sysApi.getUrlPath();
                if (requestMethod.equals(method) && antPathMatcher.match(url, requestUrl)) {
                    // 匹配命中了，将访问此资源需要的角色添加到 List<ConfigAttribute>
                    List<RoleDetail> roleDTOList = roleService.allRoleList(true).getData();
                    if (roleDTOList != null) {
                        configAttributes.addAll(roleDTOList);
                    }
                }
            }
            if (!configAttributes.isEmpty()) {
                return configAttributes;
            }
        }
        // 【警告】如果返回为null则说明此url地址不需要相应的角色就可以访问, 这样Security会放行
        Collection<ConfigAttribute> co = new CopyOnWriteArrayList<>();
        co.add(new SecurityConfig("null"));
        return co;
    }

    /**
     * 此处方法如果做了实现，返回了定义的权限资源列表，
     * Spring Security会在启动时校验每个ConfigAttribute是否配置正确，
     * 如果不需要校验，这里实现方法，方法体直接返回null即可。
     *
     * @return {@link Collection}
     */
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    /**
     * 方法返回类对象是否支持校验，
     * web项目一般使用FilterInvocation来判断，或者直接返回true
     *
     * @param aClass
     * @return boolean
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
