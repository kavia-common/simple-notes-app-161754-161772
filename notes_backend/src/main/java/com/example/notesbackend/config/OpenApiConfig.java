package com.example.notesbackend.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenAPI configuration for the Notes API.
 */
@Configuration
public class OpenApiConfig {

    // PUBLIC_INTERFACE
    @Bean
    public OpenAPI notesOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Notes Backend API")
                        .description("REST API for managing notes (create, read, update, delete)")
                        .version("0.1.0")
                        .contact(new Contact().name("Notes App").url("https://example.com")))
                .addTagsItem(new Tag().name("Notes").description("CRUD operations for notes"))
                .externalDocs(new ExternalDocumentation()
                        .description("Swagger UI")
                        .url("/swagger-ui.html"));
    }
}
