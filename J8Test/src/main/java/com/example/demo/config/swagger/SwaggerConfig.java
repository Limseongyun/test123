package com.example.demo.config.swagger;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {
	@Bean
	public OpenAPI openApi() {
		return new OpenAPI()
				.components(new Components()
							.addSecuritySchemes("Token!", new SecurityScheme()
															.type(SecurityScheme.Type.HTTP)
															.in(SecurityScheme.In.HEADER)
															.scheme("Bearer")
															.name("Authorization")
															.bearerFormat("JWT")
												)
							).addSecurityItem(new SecurityRequirement().addList("Token!"))
							.info(new Info().title("sample swagger")
							.description("sample desc")
							.version("v0.0.1"));
	}
	
	@Bean
	public GroupedOpenApi samp() {
		return GroupedOpenApi.builder()
				.group("sample")
				.packagesToScan("com.example.demo.samplemvc.restcontroller")
				.build();
	}
	
	@Bean
	public GroupedOpenApi api() {
		return GroupedOpenApi.builder()
				.group("api")
				.packagesToScan("com.example.demo.mvc.restcontroller")
				.build();
	}
}
