package com.studentservice.app.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
@OpenAPIDefinition(
	     info = @Info(
	               title = "Student Service Apis",
	               version = "v2",
		       description = "sign up of students, departments and courses",
		       contact = @Contact(
					name = "Aptech Java Team",
					email = "gojo@atbtechsoft.com"
				)
			)
	)
public class SwaggerConfigs {
	@Bean
    public OpenAPI signupServiceOpenAPI() {
        return new OpenAPI()          
                .components(new Components().addSecuritySchemes("apiKeyScheme",  new SecurityScheme()
                        .type(SecurityScheme.Type.APIKEY)
                        .in(SecurityScheme.In.HEADER)
                        .name("Authorization")
                        ))
                .addSecurityItem(new SecurityRequirement().addList("apiKeyScheme"));
    }
}
