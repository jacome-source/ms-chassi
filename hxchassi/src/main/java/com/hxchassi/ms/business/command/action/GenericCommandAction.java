package com.hxchassi.ms.business.command.action;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hxchassi.ms.business.entity.GenericEntity;
import com.hxchassi.ms.business.exception.GenericEntityException;
import com.hxchassi.ms.data.repository.GenericRepository;

/**
 * Exemplo de ação
 */
@Component
public class GenericCommandAction {

	private static final Logger LOG = LoggerFactory.getLogger(GenericCommandAction.class);

	@Autowired
	private GenericRepository genericRepository;

	public boolean createEntity(GenericEntity entity) {
		return genericRepository.save(entity).getId() > 0 ? true : false;
	}

	public void sendNotification(GenericEntity entity) {
		LOG.info("Mandando notificação : {}", entity);
	}

	public void validateEntity(GenericEntity entity) throws GenericEntityException {
		if (!StringUtils.endsWithIgnoreCase(entity.getEmail(), "@email.com")) {
			throw new GenericEntityException("Email inválido: " + entity.getEmail());
		}
	}

	
}
