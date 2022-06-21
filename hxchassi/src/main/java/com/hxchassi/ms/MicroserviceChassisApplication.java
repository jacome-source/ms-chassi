package com.hxchassi.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MicroserviceChassisApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceChassisApplication.class, args);
	}

}
