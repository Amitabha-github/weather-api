package com.vanguard.weatherapi.exception;

public class WeatherErrorMessage {
	
	int cod;
	String message;
	
	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public WeatherErrorMessage() {
		super();
	}

	public WeatherErrorMessage(int cod, String message) {
		super();
		this.cod = cod;
		this.message = message;
	}
	

}
