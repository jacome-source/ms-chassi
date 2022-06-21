package com.hxchassi.ms.api.response;

import java.util.List;

import com.hxchassi.ms.business.entity.GenericEntity;

public class GenericResponse {

	private List<GenericEntity> list;

		
	public GenericResponse() {	}
	
	public GenericResponse(List<GenericEntity> list) {
		super();
		this.list = list;
	}

	public List<GenericEntity> getList() {
		return list;
	}

	public void setList(List<GenericEntity> list) {
		this.list = list;
	}
	
	
}
