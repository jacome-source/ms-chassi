package com.hxchassi.ms.business.command.service;

import javax.xml.bind.ParseConversionEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hxchassi.ms.api.client.GenericClient;
import com.hxchassi.ms.api.request.GenericRequest;
import com.hxchassi.ms.business.command.action.GenericCommandAction;
import com.hxchassi.ms.business.entity.GenericEntity;

import feign.FeignException;

/**
 * Exemplo de serviço
 */
@Service
public class GenericCommandService {
	
	private static final Logger LOG = LoggerFactory.getLogger(GenericCommandService.class);
	
	@Autowired
	private GenericCommandAction genericCommandAction;

	@Autowired
	private GenericClient genericClient;
	
	public boolean createEntity(GenericEntity entity) {
		genericCommandAction.validateEntity(entity);

		// valid & created
		if (genericCommandAction.createEntity(entity)) {
			genericCommandAction.sendNotification(entity);
			return true;
		}

		// valid but not created
		return false;
	}
	
	public boolean createExternalEntity(GenericEntity entity) {
		LOG.debug("[Sync] Simulando criação de usuário");
		LOG.debug("[Sync] Atualizando relação com microserviço, status : PENDING");

		try {
			// calling payroll microservice
			LOG.debug("[Sync] Chamanado API");
			var requestBody = new GenericRequest();
			requestBody.setEntity(entity);;
			ResponseEntity<String> result = genericClient.createExternalEntity(requestBody);
			LOG.debug("[Sync] Terminando API");

			// finalize the approval termination status (payroll already disabled)
			if (Boolean.parseBoolean(result.getBody().toString()))
				LOG.debug("[Sync] Atualizando relação com microserviço, status : APPROVED");
			else {
				LOG.debug("[Sync] Atualizando relação com microserviço, status : ERROR");
				LOG.warn("[Circuit Breaker] "+ result.getBody().toString());
				return false;
			}
			
			return true;
		} catch (FeignException e) {
			LOG.warn("[Sync] Feign client Exception : {}", e.getMessage());
			LOG.debug("[Sync] Atualizando relação com microserviço, status : ERROR");
			return false;
		}
	}
}
