package com.chassi.ms.api.server.chassis;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.chassi.ms.api.response.PlainMessage;
import com.chassi.ms.exception.BadInputRequestException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/chassis/exception")
@Tag(name = "Exception API", description = "Chassi exemplo da API de exceções.")
public class ExceptionApi {

	private static final Logger LOG = LoggerFactory.getLogger(Exception.class);

	@GetMapping(value = "/global", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Exception API", description = "<p>Exemplo de lançamento de exceção "
			+ "Se o valor do parâmetro for inválido.</p>")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Mostra o valor dos parâmetros"),
			@ApiResponse(responseCode = "400", description = "Se <code>param_one</code> ou <code>param_two</code> forem inválidos.", content = {
					@Content(examples = { @ExampleObject(value = "{\r\n" + "    \"message\": \"string\",\r\n"
							+ "    \"recomendação\": \"string\"\r\n" + "}") }, mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", description = "Se <code>use_file</code> for <code>true</code>.", content = {
					@Content(examples = { @ExampleObject(value = "{\r\n" + "    \"message\": \"string\",\r\n"
							+ "    \"recomendação\": \"string\"\r\n" + "}") }, mediaType = "application/json") }) })
	public ResponseEntity<PlainMessage> demoExceptionGlobal(
			@RequestParam(name = "param_one") @Parameter(description = "Deve ser numérico.", example = "1526", required = false) String paramOne,
			@RequestParam(name = "param_two") @Parameter(description = "Tamanho máximo de 5 caracteres.", example = "A5b9c", required = false) String paramTwo,
			@RequestParam(name = "use_file", defaultValue = "false") @Parameter(description = "Se <code>true</code> lança exception (simulando accesso de arquivo não existente).") boolean useFile)
			throws FileNotFoundException {
		// Joga NumberFormatException se string contiver non-numeric
		int number = Integer.parseInt(paramOne);

		// max length é 5 
		if (paramTwo.length() > 5) {
			LOG.warn("Input errado: param_two");
			throw new BadInputRequestException("param_two é muito longo!");
		}

		// checa exception
		if (useFile) {
			var file = new FileReader("a-not-exists-file.txt");
			LOG.info("File is {}", file);
		}

		// Constroi response (sem lançamento de exceção)
		var message = String.join(", ", "param_one (converted to number) : " + number, "param_two : " + paramTwo);
		var response = new PlainMessage(message);

		return ResponseEntity.ok().body(response);
	}

	@GetMapping(value = "/response", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Exception API", description = "<p>Exemplo de lançamento de exceção "
			+ "Se o valor do parâmetro for inválido.</p>")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Mostra o valor dos parâmetros"),
			@ApiResponse(responseCode = "400", description = "If <code>param_one</code> é inválido.", content = {
					@Content(examples = { @ExampleObject(value = "{\r\n" + "    \"message\": \"string\",\r\n"
							+ "    \"recomendação\": \"string\"\r\n" + "}") }, mediaType = "application/json") }) })
	public ResponseEntity<PlainMessage> demoExceptionResponse(
			@RequestParam(name = "param_one") @Parameter(description = "Válido quando o parâmetro começa com <code>mivroservice</code>.", example = "microserviceXYZ", required = false) String paramOne) {

		// Precisa especificar prefixo
		if (!paramOne.startsWith("microservice")) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "param_one precisa começar com 'microservice'");
		}

		var response = new PlainMessage("param_one : " + paramOne);

		return ResponseEntity.ok().body(response);
	}

}
