package com.vanguard.weatherapi.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.vanguard.weatherapi.model.WeatherData;

public interface WeatherDAO extends CrudRepository<WeatherData, String>{
	
	public List<WeatherData> findByApiID(String apiID) ;

}



