package com.vanguard.weatherapi.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(WeatherDataID.class)
public class WeatherData {
	@Id
	private String apiID;
	@Id
	private Date timestamp;
	private String description;
	
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public WeatherData(String apiID, Date timestamp, String description) {
		super();
		this.apiID = apiID;
		this.timestamp = timestamp;
		this.description = description;
	}
	public WeatherData() {
		super();
	}

	
	
	

}
