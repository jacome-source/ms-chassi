package com.chassi.ms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.chassi.ms.api.client.FeignRetryer;

import feign.Retryer;

@Configuration
public class FeignConfig {

	@Bean
	public Retryer retryer() {
		return new FeignRetryer();
	}

}
