package com.hxchassi.ms.crosscutting.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hxchassi.ms.crosscutting.api.filter.CheckEmailFilter;

/**
 * Configura chamada do CheckEmailFilter para url com padrão específico
 */
@Configuration
public class SecurityFilterConfig {

	@Bean
	public FilterRegistrationBean<CheckEmailFilter> checkEmailFilter() {
		var registrationBean = new FilterRegistrationBean<CheckEmailFilter>();

		registrationBean.setFilter(new CheckEmailFilter());
		registrationBean.addUrlPatterns("/api/chassis/security/email/*");

		return registrationBean;
	}

}
