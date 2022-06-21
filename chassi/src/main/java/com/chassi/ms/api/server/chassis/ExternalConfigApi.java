package com.chassi.ms.api.server.chassis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chassi.ms.api.response.PlainMessage;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/chassis")
@Tag(name = "External Config API", description = "Chassi exemplo de API para uso de configuração externa.")
public class ExternalConfigApi {

	// Chave definida no arquivo microservice.yml
	@Value("${microservice.title:Default Title}")
	private String title;

	@GetMapping(value = "/title", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Get title", description = "Recupera o title de uma configuração externa, "
			+ "ou usa o title default se não existir configuração externa definida.")
	public ResponseEntity<PlainMessage> demoTitle() {
		var response = new PlainMessage(title);
		return ResponseEntity.ok().body(response);
	}

}
