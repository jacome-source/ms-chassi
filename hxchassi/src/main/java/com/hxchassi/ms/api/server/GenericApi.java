package com.hxchassi.ms.api.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hxchassi.ms.api.request.GenericRequest;
import com.hxchassi.ms.api.response.GenericResponse;
import com.hxchassi.ms.business.command.service.GenericCommandService;
import com.hxchassi.ms.business.query.service.GenericQueryService;

@RestController
@RequestMapping("/api/generic")
public class GenericApi {

	private static final Logger LOG = LoggerFactory.getLogger(GenericApi.class);

	@Autowired
	private GenericCommandService commandService;

	@Autowired
	private GenericQueryService queryService;

	@PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> createEntity(@RequestBody GenericRequest requestBody) {

		LOG.debug("Cadastrando entidade : {}", requestBody.getEntity());

		
		if (commandService.createEntity(requestBody.getEntity())) {
			return ResponseEntity.status(HttpStatus.CREATED).body(Boolean.TRUE.toString());
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Boolean.FALSE.toString());
		}
	}

	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericResponse> getByAmount(@RequestParam(required = false, defaultValue = "0") int min,
			@RequestParam(required = true) int max) {
		var responseBody = new GenericResponse(queryService.getEntitysInRage(min,max));
		return ResponseEntity.ok().body(responseBody);
	}
	
	/**
	 * Método pra testar Circuit Breaker e Retryer
	 * Chama cliente inexistente
	 */
	@PostMapping(value = "/external", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> createExternalEntity(@RequestBody GenericRequest requestBody) {

		LOG.debug("Cadastrando entidade : {}", requestBody.getEntity());

		
		if (commandService.createExternalEntity(requestBody.getEntity())) {
			return ResponseEntity.status(HttpStatus.CREATED).body(Boolean.TRUE.toString());
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Boolean.FALSE.toString());
		}
	}
	
}
