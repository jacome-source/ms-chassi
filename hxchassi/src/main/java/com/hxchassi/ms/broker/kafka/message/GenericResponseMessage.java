package com.hxchassi.ms.broker.kafka.message;

public class GenericResponseMessage {

	private String entityID;
	
	private String message;

	public String getEntityID() {
		return entityID;
	}

	public void setEntityID(String entityID) {
		this.entityID = entityID;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
