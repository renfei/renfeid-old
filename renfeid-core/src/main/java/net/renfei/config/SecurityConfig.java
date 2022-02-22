package net.renfei.config;

import net.renfei.filter.AuthorizationFilter;
import net.renfei.security.handler.AccessDeniedHandlerImpl;
import net.renfei.security.handler.AuthenticationEntryPointImpl;
import net.renfei.security.interceptor.AccessDecisionManagerImpl;
import net.renfei.security.interceptor.FilterInvocationSecurityMetadataSourceImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

/**
 * Spring Security 安全配置
 *
 * @author renfei
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final SystemConfig systemConfig;
    private final AuthorizationFilter authorizationFilter;
    private final AccessDecisionManagerImpl accessDecisionManager;
    private final FilterInvocationSecurityMetadataSourceImpl filterInvocationSecurityMetadataSource;

    public SecurityConfig(SystemConfig systemConfig,
                          AuthorizationFilter authorizationFilter,
                          AccessDecisionManagerImpl accessDecisionManager,
                          FilterInvocationSecurityMetadataSourceImpl filterInvocationSecurityMetadataSource) {
        this.systemConfig = systemConfig;
        this.authorizationFilter = authorizationFilter;
        this.accessDecisionManager = accessDecisionManager;
        this.filterInvocationSecurityMetadataSource = filterInvocationSecurityMetadataSource;
    }

    /**
     * anyRequest          |   匹配所有请求路径
     * access              |   SpringEl表达式结果为true时可以访问
     * anonymous           |   匿名可以访问
     * denyAll             |   用户不能访问
     * fullyAuthenticated  |   用户完全认证可以访问（非remember-me下自动登录）
     * hasAnyAuthority     |   如果有参数，参数表示权限，则其中任何一个权限可以访问
     * hasAnyRole          |   如果有参数，参数表示角色，则其中任何一个角色可以访问
     * hasAuthority        |   如果有参数，参数表示权限，则其权限可以访问
     * hasIpAddress        |   如果有参数，参数表示IP地址，如果用户IP和参数匹配，则可以访问
     * hasRole             |   如果有参数，参数表示角色，则其角色可以访问
     * permitAll           |   用户可以任意访问
     * rememberMe          |   允许通过remember-me登录的用户访问
     * authenticated       |   用户登录后可访问
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if ("prod".equals(systemConfig.getActive())) {
            http.csrf()
                    .csrfTokenRepository(new HttpSessionCsrfTokenRepository())
                    // 此处忽略开放接口地址和Druid监控接口
                    .ignoringAntMatchers(
                            "/api/**",
                            "/graphql/**",
                            "/druid/**"
                    );
        } else {
            // 非生产环境，禁用 csrf
            http.csrf().disable();
        }
        http.headers()
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
    }
}
