package com.hxchassi.ms.api.request;

import com.hxchassi.ms.business.entity.GenericEntity;

/**
 * Exemplo de Request
 */
public class GenericRequest {

	private GenericEntity entity;
		
	public GenericEntity getEntity() {
		return entity;
	}

	public void setEntity(GenericEntity entity) {
		this.entity = entity;
	}

}
