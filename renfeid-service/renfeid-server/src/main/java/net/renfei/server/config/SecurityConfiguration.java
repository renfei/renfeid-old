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
package net.renfei.server.config;

import net.renfei.common.core.config.SystemConfig;
import net.renfei.server.filter.AuthorizationFilter;
import net.renfei.server.security.AccessDecisionManagerImpl;
import net.renfei.server.security.FilterInvocationSecurityMetadataSourceImpl;
import net.renfei.server.security.handler.AccessDeniedHandlerImpl;
import net.renfei.server.security.handler.AuthenticationEntryPointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Spring Security 安全配置
 *
 * @author renfei
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private final SystemConfig systemConfig;
    private final AuthorizationFilter authorizationFilter;
    private final AccessDecisionManagerImpl accessDecisionManager;
    private final FilterInvocationSecurityMetadataSourceImpl filterInvocationSecurityMetadataSource;

    public SecurityConfiguration(SystemConfig systemConfig,
                                 AuthorizationFilter authorizationFilter,
                                 AccessDecisionManagerImpl accessDecisionManager,
                                 FilterInvocationSecurityMetadataSourceImpl filterInvocationSecurityMetadataSource) {
        this.systemConfig = systemConfig;
        this.authorizationFilter = authorizationFilter;
        this.accessDecisionManager = accessDecisionManager;
        this.filterInvocationSecurityMetadataSource = filterInvocationSecurityMetadataSource;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable();
        http
                .headers()
                .frameOptions().sameOrigin()
                .and()
                .authorizeRequests()
                .antMatchers("/_/api/**").authenticated()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setSecurityMetadataSource(filterInvocationSecurityMetadataSource);
                        o.setAccessDecisionManager(accessDecisionManager);
                        return o;
                    }
                })
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .anyRequest().permitAll()
                .and().exceptionHandling()
                .accessDeniedHandler(new AccessDeniedHandlerImpl())
                .authenticationEntryPoint(new AuthenticationEntryPointImpl());
        http.addFilterBefore(
                authorizationFilter,
                UsernamePasswordAuthenticationFilter.class
        );
        return http.build();
    }
}
