package net.renfei.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import net.renfei.utils.ApplicationContextUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger接口配置
 *
 * @author renfei
 */
@Configuration
public class SwaggerConfig implements WebMvcConfigurer {
    protected final SystemConfig SYSTEM_CONFIG;

    {
        SYSTEM_CONFIG = (SystemConfig) ApplicationContextUtil.getBean("systemConfig");
    }

    @Bean
    public OpenAPI springShopOpenAPI() {
        assert SYSTEM_CONFIG != null;
        return new OpenAPI()
                .info(new Info().title("RENFEI.NET Open API")
                        .description("任霏的开放API程序接口")
                        .version(SYSTEM_CONFIG.getVersion())
                        .license(new License().name("Apache 2.0").url("https://github.com/renfei/renfeid/blob/master/LICENSE"))
                        .contact(new Contact().name("RenFei").email("i@renfei.net").url("https://www.renfei.net")))
                .externalDocs(new ExternalDocumentation()
                        .description("Open Source")
                        .url("https://github.com/renfei/renfeid"));
    }

}
