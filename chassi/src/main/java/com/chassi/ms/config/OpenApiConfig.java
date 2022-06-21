package com.chassi.ms.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

/**
 * Configuração do SpringDoc - swagger
 * Utiliza anotações 'Tag', 'Operation', 'Parameter' e 'APIResponse'
 */
@Configuration
public class OpenApiConfig {

	// springdoc config
	@Bean
	public OpenAPI customOpenAPI() {
		var servers = new ArrayList<Server>();
		servers.add(new Server().url("http://localhost:8004").description("Development server"));

		return new OpenAPI().components(new Components()).info(new Info()
				.description("<p>Exemplo de API de documentação de Microserviços.</p>")
				.title("Chassi de Microserviços").version("2.0.0")).servers(servers);
	}

}
