package com.hxchassi.ms.crosscutting.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hxchassi.ms.crosscutting.api.client.FeignRetryer;

import feign.Retryer;

@Configuration
public class FeignConfig {

	@Bean
	public Retryer retryer() {
		return new FeignRetryer();
	}

}
