package com.hxchassi.ms.business.query.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hxchassi.ms.business.entity.GenericEntity;
import com.hxchassi.ms.business.query.action.GenericQueryAction;

/**
 * Exemplo de serviço de query
 */
@Service
public class GenericQueryService {

	@Autowired
	private GenericQueryAction genericQueryAction;

	public List<GenericEntity> getEntitys() {
		var list = genericQueryAction.findAll();
		list.forEach(ge -> ge.setEmail(genericQueryAction.maskEmail(ge.getEmail())));
		
		return list;
	}
	
	public List<GenericEntity> getEntitysInRage(int min, int max) {
		var list = genericQueryAction.findByIdBetween(min, max);
		list.forEach(emp -> emp.setEmail(genericQueryAction.maskEmail(emp.getEmail())));
		
		return list;
	}
	
}
