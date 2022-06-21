package com.hxchassi.ms.broker.kafka.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.hxchassi.ms.broker.kafka.message.GenericResponseMessage;

@Component
public class GenericResponseListener {

	private static final Logger LOG = LoggerFactory.getLogger(GenericResponseListener.class);


	@KafkaListener(topics = "t.generictopic.response")
	public void listenMasterPayrollResponse(GenericResponseMessage message) {
		LOG.debug("[Async-listen] Lendo respostas");
		LOG.debug("[Async-listen] "+message.getMessage());
		LOG.debug("[Async-listen] Terminando");
	}
	
}
