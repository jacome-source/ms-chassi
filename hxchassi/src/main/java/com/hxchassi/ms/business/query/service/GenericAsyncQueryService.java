package com.hxchassi.ms.business.query.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hxchassi.ms.broker.kafka.message.GenericMessage;
import com.hxchassi.ms.broker.kafka.publisher.GenericPublisher;

@Service
public class GenericAsyncQueryService {

	private static final Logger LOG = LoggerFactory.getLogger(GenericAsyncQueryService.class);

	@Autowired
	private GenericPublisher genericPublisher;

	public boolean propagateEntity(String entityId) {
		LOG.debug("[Async-publish] Simulando propagação de entidade",
				entityId);

		try {
			// publishing to message broker
			LOG.debug("[Async-publish] Iniciando propagação no message broker");
			var messagePublish = new GenericMessage();
			messagePublish.setEntityID(entityId);
			genericPublisher.publishGenericMessage(messagePublish);
			LOG.debug("[Async-publish] Terminando de publica no message broker");

			return true;
		} catch (Exception e) {
			LOG.warn("[Async-publish] Client exception : {}", e.getMessage());
			return false;
		}
	}
	
}
