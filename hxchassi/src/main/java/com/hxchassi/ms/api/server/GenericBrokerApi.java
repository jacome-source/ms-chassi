package com.hxchassi.ms.api.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hxchassi.ms.business.query.service.GenericAsyncQueryService;
import com.hxchassi.ms.crosscutting.api.response.PlainMessage;

/**
 * API utilizada pra startar brokers
 */
@RestController
@RequestMapping("/api/generic/async")
public class GenericBrokerApi {

	@Autowired
	private GenericAsyncQueryService genericAsyncQueryService;
	
	@GetMapping(value = "/{entity_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PlainMessage> propagateMessage(
			@PathVariable(name = "entity_id", required = true) String entityId)
			throws JsonProcessingException {
		genericAsyncQueryService.propagateEntity(""+entityId);

		return ResponseEntity.status(HttpStatus.OK).body(new PlainMessage("Propagado : " + entityId));
	}
}
