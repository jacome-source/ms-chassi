package com.hxchassi.ms.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.hxchassi.ms.api.request.GenericRequest;

@FeignClient(name = "genericClient", url = "${circuit.chassi.services.url}", fallback = GenericCircuitBreaker.class)
public interface GenericClient {

	@PostMapping(value = "/api/generic/")
	ResponseEntity<String> createExternalEntity(@RequestBody GenericRequest requestBody);
}
