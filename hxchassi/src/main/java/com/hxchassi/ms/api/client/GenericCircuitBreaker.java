package com.hxchassi.ms.api.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.hxchassi.ms.api.request.GenericRequest;

/**
 * Circuit Breaker genérico
 */
@Component
public class GenericCircuitBreaker implements GenericClient {

	@Override
	public ResponseEntity<String> createExternalEntity(GenericRequest requestBody) {
		var defaultMessage = "Desculpe, não podemos cadastrar entidade agora." + " Tente mais tarde.";
		return ResponseEntity.ok().body(defaultMessage);
	}

}
