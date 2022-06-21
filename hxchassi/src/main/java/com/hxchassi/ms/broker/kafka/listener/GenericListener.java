package com.hxchassi.ms.broker.kafka.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import com.hxchassi.ms.broker.kafka.message.GenericMessage;
import com.hxchassi.ms.broker.kafka.message.GenericResponseMessage;

@Component
public class GenericListener {

	private static final Logger LOG = LoggerFactory.getLogger(GenericListener.class);

	// Método de comunicação assíncrona
	@KafkaListener(topics = "t.generictopic")
	@SendTo(value = "t.generictopic.response")
	public GenericResponseMessage listenGenericTopic(GenericMessage message) {
		LOG.debug("[Async] Escutando o message broker");


		var genericResponseMessage = new GenericResponseMessage();
		genericResponseMessage.setEntityID(message.getEntityID());
		genericResponseMessage
				.setMessage("Simulando transformação na entidade: " + message.getEntityID());

		LOG.debug("[Async] Terminando de escutar o message broker");

		return genericResponseMessage;
	}
	
}
