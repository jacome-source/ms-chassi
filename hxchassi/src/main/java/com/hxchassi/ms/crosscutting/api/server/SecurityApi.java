package com.hxchassi.ms.crosscutting.api.server;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hxchassi.ms.crosscutting.api.response.PlainMessage;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/chassis/security")
@Tag(name = "Security API", description = "Chassi exemplo de security API.")
public class SecurityApi {

	@GetMapping(value = "/basic_auth/one", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Basic authentication", description = "Basic authentication API.")
	public ResponseEntity<PlainMessage> demoSecurityBasicAuthOne() {
		var response = new PlainMessage("Você foi autenticado!");

		return ResponseEntity.ok().body(response);
	}

	@PostMapping(value = "/basic_auth/two", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Basic authentication", description = "Basic authentication API.")
	public ResponseEntity<PlainMessage> demoSecurityBasicAuthTwo(
			@RequestBody @Parameter(description = "Request body") PlainMessage requestBody) {
		var response = new PlainMessage(
				"Você foi autenticado! Sua mensagem é : " + requestBody.getMessage());

		return ResponseEntity.ok().body(response);
	}

	// Exemplo de cache control
	// A resposta será cacheada por 1 min
	// Melhora a performance e evita usuários maliciosos se aproveitarem do sistema
	@GetMapping(value = "/cache/time", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Custom security", description = "Custom security API.")
	public ResponseEntity<PlainMessage> demoSecurityCustom() {
		var response = new PlainMessage("Hora: " + LocalTime.now());

		return ResponseEntity.ok().cacheControl(CacheControl.maxAge(1, TimeUnit.MINUTES)).body(response);
	}

	@GetMapping(value = "/email", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Header validation", description = "Header valdation API.")
	public ResponseEntity<PlainMessage> demoSecurityEmailOne() {
		var response = new PlainMessage(
				"Você colocou email correto no header 'X-Developer-Email'.");

		return ResponseEntity.ok().body(response);
	}

}
