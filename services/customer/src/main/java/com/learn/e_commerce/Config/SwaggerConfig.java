package com.learn.e_commerce.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import io.swagger.v3.oas.models.security.SecurityScheme.In;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Citizen App")
                        .version("V-1.0")
                        .description("Demo project for citizen mobile app .")
                        .description("Powered by Ammar .")
                        .contact(new Contact()
                                .name("Smart Digital Services")
                                .url("https://egyptsmartcards.com")
                                .email("info@egyptsmartcards.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.example.com/license"))
                );
    }
}