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
package net.renfei.common.core.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger接口配置
 *
 * @author renfei
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("RENFEI.NET Free Open API")
                        .description("任霏的免费开放API程序接口")
                        .license(new License().name("Apache 2.0").url("https://github.com/renfei/renfeid/blob/master/LICENSE"))
                        .contact(new Contact().name("RenFei").email("i@renfei.net").url("https://www.renfei.net")))
                .externalDocs(new ExternalDocumentation()
                        .description("Open Source")
                        .url("https://github.com/renfei/renfeid"));
    }

    @Bean
    public GroupedOpenApi freeOpenApi() {
        return GroupedOpenApi.builder()
                .group("FreeOpenApi")
                .pathsToMatch("/api/**")
                .addOpenApiCustomiser(openApiCustomizer())
                .build();
    }

    @Bean
    public GroupedOpenApi internalForegroundApi() {
        return GroupedOpenApi.builder()
                .group("InternalForegroundApi")
                .pathsToMatch("/-/api/**")
                .addOpenApiCustomiser(internalForegroundApiCustomizer())
                .build();
    }

    @Bean
    public GroupedOpenApi internalApi() {
        return GroupedOpenApi.builder()
                .group("InternalAPI")
                .pathsToMatch("/_/api/**")
                .addOpenApiCustomiser(internalApiCustomizer())
                .build();
    }

    @Bean
    public OpenApiCustomiser openApiCustomizer() {
        return openApi -> openApi
                .info(new Info()
                        .title("RENFEI.NET Free Open API")
                        .description("任霏的免费开放API程序接口，提供对外开放能力，无需登录认证。")
                        .license(new License().name("Apache 2.0").url("https://github.com/renfei/renfeid/blob/master/LICENSE"))
                        .contact(new Contact().name("RenFei").email("i@renfei.net").url("https://www.renfei.net")))
                .externalDocs(new ExternalDocumentation()
                        .description("Open Source")
                        .url("https://github.com/renfei/renfeid"));
    }

    @Bean
    public OpenApiCustomiser internalForegroundApiCustomizer() {
        return openApi -> openApi
                .info(new Info()
                        .title("RENFEI.NET Internal Foreground API")
                        .description("RENFEI.NET 前台前端 API 接口，不对外开放，可能要求登录认证以后使用。")
                        .contact(new Contact().name("RenFei").email("i@renfei.net").url("https://www.renfei.net")))
                .externalDocs(new ExternalDocumentation()
                        .description("Contact Us")
                        .url("https://www.renfei.net"));
    }

    @Bean
    public OpenApiCustomiser internalApiCustomizer() {
        return openApi -> openApi
                .info(new Info()
                        .title("RENFEI.NET Internal API")
                        .description("RENFEI.NET 内部程序 API 接口，不对外开放，必须登录认证。")
                        .contact(new Contact().name("RenFei").email("i@renfei.net").url("https://www.renfei.net")))
                .externalDocs(new ExternalDocumentation()
                        .description("Contact Us")
                        .url("https://www.renfei.net"));
    }
}
