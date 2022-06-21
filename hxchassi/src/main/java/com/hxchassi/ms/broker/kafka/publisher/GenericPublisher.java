package com.hxchassi.ms.broker.kafka.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hxchassi.ms.broker.kafka.message.GenericMessage;

@Service
public class GenericPublisher {

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	public void publishGenericMessage(GenericMessage message) throws JsonProcessingException {
		kafkaTemplate.send("t.generictopic", message);
	}
	
}
