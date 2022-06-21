package com.hxchassi.ms.business.query.action;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hxchassi.ms.business.entity.GenericEntity;
import com.hxchassi.ms.data.repository.GenericRepository;

@Component
public class GenericQueryAction {

	@Autowired
	private GenericRepository genericRepository;

	public List<GenericEntity> findAll() {
		return (List<GenericEntity>) genericRepository.findAll();
	}
	
	public List<GenericEntity> findByIdBetween(int min, int max) {
		return genericRepository.findByIdBetween(min, max);
	}

	public String maskEmail(String email) {
		return StringUtils.overlay(email, "*", 0, 4);
	}
	
}
