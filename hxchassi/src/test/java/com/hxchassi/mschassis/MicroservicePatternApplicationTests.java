package com.hxchassi.mschassis;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hxchassi.ms.MicroserviceChassisApplication;

@SpringBootTest(classes = MicroserviceChassisApplication.class)
class MicroservicePatternApplicationTests {

	@Value("${test.chassi.services.url}")
	private String baseURL;
	
	@Test
	void testCreateEntity() throws JsonProcessingException {
		System.out.println(baseURL);		
		RestTemplate restTemplate = new RestTemplate();
				
		String postBodyJson = createJson();			
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(postBodyJson ,headers);
		
		ResponseEntity<String> newEntity = restTemplate.postForEntity(baseURL+"/api/generic", request, String.class);
		assertNotNull(newEntity);
		assertTrue(Boolean.parseBoolean(newEntity.getBody()));
	}

	private String createJson() {
		String postBodyJson = "{ "
				+ "    \"entity\": { "
				+ "       \"email\": \"teste@email.com\", "
				+ "        \"description\": \"teste\" "
				+ "    }"
				+ "}";
		return postBodyJson;
	}

}
