package com.example.cactusrestclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CactusRestClientApplication {
	
	@Bean
	public RestTemplate getRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ErrorHandler());
		return restTemplate;
	}
	
	@Bean
	@Scope("singleton")
	public CustomLogger getCustomLogger() {
		CustomLogger logger = new CustomLogger();
		return logger;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(CactusRestClientApplication.class, args);
	}

}
