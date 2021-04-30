package com.vanguard.weatherapi.exception;

import org.springframework.http.HttpStatus;

public class WeatherException extends RuntimeException{
	
	private String statusCode;
	private String error;
	public WeatherException(String statusCode, String error) {
		super();
		this.statusCode = statusCode;
		this.error = error;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
	

}
