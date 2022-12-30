package net.renfei.common.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author renfei
 */
@Configuration
@EnableWebMvc
public class ApplicationConfiguration implements WebMvcConfigurer {
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        // 忽略 accept 请求头，前端转发请求的时候会把 accept 也转发过来，导致报错；后端接口全部是JSON格式
        configurer.favorPathExtension(false);
        configurer.ignoreAcceptHeader(true);
        configurer.defaultContentType(MediaType.APPLICATION_JSON);
    }
}
