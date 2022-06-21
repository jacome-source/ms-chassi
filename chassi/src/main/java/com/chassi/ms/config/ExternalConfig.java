package com.chassi.ms.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/*
 * Configuração para acesso a arquivo externos ao projeto
 * PropertySources - Indica a fonte do arquivo externo
 * classpath - coringa que assume um dos valores que o saguem (conf/) ou (c/conf/)
 * classpath irá encontrar o arquivo no primeiro endereço /conf/microservice.yaml (definido dentro do próprio projeto)
 */ 

@Configuration
@PropertySources({ @PropertySource(factory = YamlPropertySourceFactory.class, value = { "classpath:microservice.yml",
		"file:/conf/microservice.yml", "file:c:/conf/microservice.yml" }, ignoreResourceNotFound = true) })
public class ExternalConfig {
}
