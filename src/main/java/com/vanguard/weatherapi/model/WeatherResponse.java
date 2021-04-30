package com.vanguard.weatherapi.model;

public class WeatherResponse {
	
	String status;
	String message;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public WeatherResponse(String status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	public WeatherResponse() {
		super();
	}
	

}
