package com.example.cactusrestclient;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/cactus")
public class CactusRestClient {
	
	private static final String SUCCESS = "SUCCESS";
	private static final String ERROR = "ERROR";
	private static final String ERROR_MESSAGE = "Given identifier or representation is invalid.";
	private static final String CACTUS_URL = "https://cactus.nci.nih.gov/chemical/structure/{identifier}/{representation}";
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private CustomLogger customLogger;
	
	
	@RequestMapping("/{identifier}/{representation}")
	public Response getChemicalStructureInfo(@PathVariable("identifier") String identifier, @PathVariable("representation") String representation ) {
		
		Map<String, String> param = new HashMap<>();
		param.put("identifier", identifier);
		param.put("representation", representation);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.TEXT_PLAIN));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<String> responseEntity = restTemplate.exchange(CACTUS_URL, HttpMethod.GET, entity, String.class, param);
		
		Response response = null;
		
		if (responseEntity.getStatusCode()==HttpStatus.OK) {
			response = new Response(identifier, representation, SUCCESS, responseEntity.getBody());
		} else {
			response = new Response(identifier, representation, ERROR, ERROR_MESSAGE);
		}
		
		customLogger.log(response);
		
		return response;
		
	}
	
	@RequestMapping("/history")
	public List<String> getHistory() {
		return customLogger.getLogs();
	}
}
