package com.effectivemobile.testtask.bank.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Banking API")
                        .version("1.0")
                        .description("API for banking operations")
                        .contact(new Contact()
                                .name("Developer")
                                .email("a.seldom@gmail.com")
                                .url("https://github.com/aseldom")));
    }
}