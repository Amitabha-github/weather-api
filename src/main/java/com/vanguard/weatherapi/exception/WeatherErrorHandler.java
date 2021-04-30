package com.vanguard.weatherapi.exception;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;

public class WeatherErrorHandler implements ResponseErrorHandler{

	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		// TODO Auto-generated method stub
		return new DefaultResponseErrorHandler().hasError(response);
	}

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		// TODO Auto-generated method stub
		
		if(response.getStatusCode().series()==HttpStatus.Series.CLIENT_ERROR)
		{
			
			
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(response.getBody()))) {
		        String httpBodyResponse = reader.lines().collect(Collectors.joining(""));
		        String errorMessage = httpBodyResponse;
				throw new WeatherException(response.getRawStatusCode()+"", errorMessage);

			}

			
		}
		
	
	
	

}
		
	}
