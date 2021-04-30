package com.vanguard.weatherapi.model;

import java.io.Serializable;
import java.util.Date;

public class WeatherDataID implements Serializable{
	
	private String apiID;
	private Date timestamp;
	
	public WeatherDataID() {
		super();
	}
	public WeatherDataID(String apiID, Date timestamp) {
		super();
		this.apiID = apiID;
		this.timestamp = timestamp;
	}
	public String getApiID() {
		return apiID;
	}
	public void setApiID(String apiID) {
		this.apiID = apiID;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

}
