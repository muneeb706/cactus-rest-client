package com.example.cactustrestclient;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.cactusrestclient.CactusRestClient;
import com.example.cactusrestclient.CactusRestClientApplication;
import com.example.cactusrestclient.CustomLogger;

@SpringBootTest(classes = CactusRestClientApplication.class)
class CactustRestClientApplicationTests {

	@Autowired
	private CactusRestClient restClient;
	
	@MockBean
	private RestTemplate resttemplate;
	
	@MockBean
	private CustomLogger logger;
	
	@Test
	public void getChemicalStructureInfoTestOK() {
		mockRestTemplateExchange("abcdefgh", HttpStatus.OK);
		mockLoggerLog();
				
		assertTrue(restClient.getChemicalStructureInfo("arsenic", "smiles").getStatus() == "SUCCESS");
	}
	
	@Test
	public void getChemicalStructureInfoTest500() {
		mockRestTemplateExchange("", HttpStatus.INTERNAL_SERVER_ERROR);
		mockLoggerLog();
				
		assertTrue(restClient.getChemicalStructureInfo("arsenic", "smiles").getStatus() == "ERROR");
	}
	
	@Test
	public void getHistoryOk() {
		mockLogHistory();			
		assertTrue(restClient.getHistory().size() == 0);
	}

	@SuppressWarnings({ "unchecked" })
	private void mockRestTemplateExchange(String response, HttpStatus status) {
		
		ResponseEntity<String> responseEntity = new ResponseEntity<String>(response, status);
		when(resttemplate.exchange(Mockito.anyString(), 
				Mockito.any(HttpMethod.class), 
				Mockito.any(HttpEntity.class), 
				Mockito.any(Class.class), 
				Mockito.any(Map.class)))
				.thenReturn(responseEntity);
	}
	
	private void mockLoggerLog() {
		doNothing().when(logger).log(Mockito.any(Object.class));
	}
	
	private void mockLogHistory() {
		when(logger.getLogs()).thenReturn(new ArrayList<String>());
	}


}
