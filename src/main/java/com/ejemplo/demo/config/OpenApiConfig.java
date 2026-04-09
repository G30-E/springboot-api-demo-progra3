package com.ejemplo.demo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Spring Boot API Demo Progra 3",
                version = "1.0.0",
                description = "API del taller de Spring Boot con validaciones, manejo de errores y documentación Swagger",
                contact = @Contact(
                        name = "Angello Escobar",
                        email = "aescobarg21@miumg.edu.gt"
                ),
                license = @License(
                        name = "Uso académico"
                )
        )
)
public class OpenApiConfig {
}