package com.example.cactusrestclient;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

public class ErrorHandler implements ResponseErrorHandler {
	  @Override
	  public void handleError(ClientHttpResponse response) throws IOException {
		  System.out.println(response.getStatusText());
	  }

	  @Override
	  public boolean hasError(ClientHttpResponse response) throws IOException {
		if (response.getStatusCode() != HttpStatus.OK)
			return true;
		else
			return false;
	  }
}