package org.weewelchie.qr.boot.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("weewelchie.qr")
                .pathsToMatch("/*/**")
                .build();
    }

    @Bean
    public OpenAPI qrCodesOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("QrCodes API")
                        .description("QrCodes application")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("QRCodes Wiki Documentation")
                        .url("https://github.com/chriswelch99/QRCodeGenerator"));
    }
}
