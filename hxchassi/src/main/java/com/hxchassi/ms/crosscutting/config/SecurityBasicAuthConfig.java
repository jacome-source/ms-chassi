package com.hxchassi.ms.crosscutting.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * Configuração do Security para Rest
 */
@Configuration
@EnableWebSecurity
public class SecurityBasicAuthConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationEntryPoint authEntryPoint;

	// Configura endpoint específico para necessitar validação
	// Utilizando Basic Autenthication
	// Possível utilizar JWT token ou Oauth  
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().mvcMatchers("/api/chassis/security/basic_auth/*").authenticated()
				.anyRequest().permitAll().and().httpBasic().authenticationEntryPoint(authEntryPoint).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	// Banco de usuários em memória
	// Possível utilizar Authentication Manager com JDBC (BD) ou LDAP
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password(encoder().encode("password")).roles("ADMIN");
		auth.inMemoryAuthentication().withUser("jacome").password(encoder().encode("jacome")).roles("SUPER-ADMIN");
	}

	@Bean
	public static PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
}
