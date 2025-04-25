package com.ats.rtem.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * Configuration class for OpenAPI (SWAGGER) documentation.
 * <p>
 * This class defines the OpenAPI configuration for the application,
 * providing API metadata such as title, description, version, license,
 * and external documentation.
 * </p>
 */
@Configuration
public class OpenApiConfig {
    /**
     * Configures the OpenAPI documentation for the application.
     * <p>
     * This method initializes an {@link OpenAPI} instance with
     * application metadata including title, description, version,
     * license information, security requirements, and external documentation links.
     * </p>
     *
     * @return An {@link OpenAPI} instance containing the API documentation configuration.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Real Time Employee Management System (RTEM)  v1.0.0")
                        .version("1.0")
                        .description("""
                                API documentation for RTEM BE Spring Boot project.
                                
                                **Developers:**
                                - Alexander Thomas Sayson
                                - Kelvin Malabanan
                                """)
                        .contact(new Contact()
                                .name("RTEM-DEV-TEAM")
                                .email("alexanderthomassayson@gmail.com")))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .name("Authorization")
                                .description("Bearer Token")));
    }
}
