package com.vanguard.weatherapi.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.vanguard.weatherapi.dao.WeatherDAO;
import com.vanguard.weatherapi.exception.WeatherErrorHandler;
import com.vanguard.weatherapi.exception.WeatherException;
import com.vanguard.weatherapi.model.WeatherData;
import com.vanguard.weatherapi.utility.WeatherConstants;
import com.vanguard.weatherapi.utility.WeatherUtility;

@Service
public class WeatherService {
	
	
	@Autowired
	private  WeatherDAO weatherDAO;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	
	public WeatherData addWeatherRecord(WeatherData weather)
	{
		return weatherDAO.save(weather);
		
	}
	
	public boolean maxLimitReached(String apiID)
	{
		
	 List<WeatherData> list=weatherDAO.findByApiID(apiID);
	 
	 Date d=new Date();
	 
	 Calendar calendar = Calendar.getInstance();
	    calendar.setTime(d);
	    calendar.add(Calendar.HOUR_OF_DAY, -1);
	    Date newd= calendar.getTime();
	 
	 
	   Long count=      list
			 			.stream()
			 			.map((item)-> item.getTimestamp())
			 			.filter((item) -> item.after(newd))
			 			.count();
			 			
	 
		return count==5 ? true : false;
		
	}
	
	public String getWeatherData(String cityName,String countryName,String appId) throws WeatherException
	{
		
		if(countryName != null && !countryName.isBlank() )
		{
			cityName = new StringBuilder()
					.append(cityName)
					.append(",")
					.append(countryName)
					.toString();
		}
		
		String url= new StringBuilder()
		.append(WeatherConstants.OPEN_WEATHER_API_URL)
		.append(WeatherConstants.QUERY_PARAM_CITYANDCOUNTRY)
		.append(cityName)
		.append(WeatherConstants.QUERY_PARAM_APPID)
		.append(appId).toString();

		restTemplate.setErrorHandler(new WeatherErrorHandler());
		
		JSONObject obj = restTemplate.getForObject(url, JSONObject.class);
		
		
		String description= WeatherUtility.getvalue(obj.toString());
		
		
		 WeatherData data=new WeatherData(appId,new Date(),description);
		 addWeatherRecord(data);
		 
		 return description;
		
	}
	

}
