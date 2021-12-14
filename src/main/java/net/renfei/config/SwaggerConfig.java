package net.renfei.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.tags.Tag;
import net.renfei.utils.ApplicationContextUtil;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

/**
 * Swagger接口配置
 *
 * @author renfei
 */
@Configuration
public class SwaggerConfig {
    protected final SystemConfig SYSTEM_CONFIG;

    {
        SYSTEM_CONFIG = (SystemConfig) ApplicationContextUtil.getBean("systemConfig");
    }

    @Bean
    public OpenAPI springShopOpenAPI() {
        assert SYSTEM_CONFIG != null;
        return new OpenAPI()
                .components(new Components().addSecuritySchemes("basicScheme",
                        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").name("JWT").in(SecurityScheme.In.HEADER)))
                .info(new Info().title("RENFEI.NET Free Open API")
                        .description("任霏的免费开放API程序接口")
                        .version(SYSTEM_CONFIG.getVersion())
                        .license(new License().name("Apache 2.0").url("https://github.com/renfei/renfeid/blob/master/LICENSE"))
                        .contact(new Contact().name("RenFei").email("i@renfei.net").url("https://www.renfei.net")))
                .security(new ArrayList<SecurityRequirement>() {{
                    SecurityRequirement securityRequirement = new SecurityRequirement();
                    securityRequirement.addList("JWT");
                    this.add(securityRequirement);
                }})
                .externalDocs(new ExternalDocumentation()
                        .description("Open Source")
                        .url("https://github.com/renfei/renfeid"));
    }

    @Bean
    public GroupedOpenApi freeOpenApi() {
        return GroupedOpenApi.builder()
                .group("FreeOpenApi")
                .pathsToMatch("/api/**")
                .build();
    }

    @Bean
    public GroupedOpenApi foregroundApi() {
        return GroupedOpenApi.builder()
                .group("ForegroundApi")
                .pathsToMatch("/_api/foreground/**")
                .build();
    }

    @Bean
    public GroupedOpenApi backgroundApi() {
        return GroupedOpenApi.builder()
                .group("BackgroundApi")
                .pathsToMatch("/_api/background/**")
                .build();
    }
}
