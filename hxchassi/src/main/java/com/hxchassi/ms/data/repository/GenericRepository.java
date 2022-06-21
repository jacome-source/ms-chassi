package com.hxchassi.ms.data.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.hxchassi.ms.business.entity.GenericEntity;

public interface GenericRepository extends PagingAndSortingRepository<GenericEntity, Long> {

	public List<GenericEntity> findByIdBetween(long min, long max);

	
}
