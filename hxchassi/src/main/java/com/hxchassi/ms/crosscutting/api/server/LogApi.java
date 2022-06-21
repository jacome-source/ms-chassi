package com.hxchassi.ms.crosscutting.api.server;

import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hxchassi.ms.crosscutting.api.response.PlainMessage;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Exemplo de utilização de logs no sistema
 */
@RestController
@RequestMapping("/api/chassis")
@Tag(name = "Log API", description = "Chassi exemplo da API de log.")
public class LogApi {

	// Responsável por registrar o log
	private static final Logger LOG = LoggerFactory.getLogger(LogApi.class);

	@GetMapping(value = "/log", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Log API", description = "Log API, possui delay random entre 10-1000 ms.")
	public ResponseEntity<PlainMessage> demoLog() {
		LOG.debug("Início da API de Log");

		var startTime = System.currentTimeMillis();

		// random delay
		try {
			Thread.sleep(ThreadLocalRandom.current().nextLong(10, 1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		var processTime = System.currentTimeMillis() - startTime;
		var message = "Demorou " + processTime + " ms para gerar a mensagem.";
		var response = new PlainMessage(message);
		LOG.debug(message);

		LOG.debug("Fim da API de Log");

		return ResponseEntity.ok().body(response);
	}

}
