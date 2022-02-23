package com.telus.ds.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

//Fixing OpenAPI issue where application server base URL is changed from HTTPS to HTTP.
@Configuration
public class OpenApiSwaggerConfig {
	
	@Value("${swagger.base.ui.url}")
	String swaggerBaseUIURL;
	
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().addServersItem(new Server().url(swaggerBaseUIURL))
                .components(new Components().addSecuritySchemes("basicScheme",
                        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic")))
                .info(new Info().title("Tracker API").version("V1")
                        .license(new License().name("Apache 2.0").url(swaggerBaseUIURL)));
    }
}