package com.vanguard.weatherapi.controller;


import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vanguard.weatherapi.exception.WeatherException;

import com.vanguard.weatherapi.model.WeatherResponse;
import com.vanguard.weatherapi.service.WeatherService;
import com.vanguard.weatherapi.utility.WeatherConstants;



@RestController
@RequestMapping("/Weather")
public class WeatherController {

	@Autowired
	private WeatherService service;


	@GetMapping("/CurrentCondition")
	public WeatherResponse getWeather(@RequestParam Map<String, String> allparams)
	throws WeatherException

	{
		
		
			String cityName = allparams.get(WeatherConstants.REQ_PARAM_CITY);
			String countryName = allparams.get(WeatherConstants.REQ_PARAM_COUNTRY);
			String appId = allparams.get(WeatherConstants.REQ_PARAM_APPID);
			if (cityName == null || cityName.isBlank())
			{
				return new WeatherResponse(WeatherConstants.STATUS_FAILURE, WeatherConstants.MSG_CITY_MISSING);
			}
			if (appId == null || appId.isBlank())
			{	
				return new WeatherResponse(WeatherConstants.STATUS_FAILURE, WeatherConstants.MSG_APIID_MISSING);
			}

			boolean maxLimitReached = service.maxLimitReached(appId);
			if (maxLimitReached)
			{
				return new WeatherResponse(WeatherConstants.STATUS_FAILURE, WeatherConstants.MSG_HOURLY_LMT);
			}

			String description = service.getWeatherData(cityName,countryName, appId);

			WeatherResponse response = new WeatherResponse(WeatherConstants.STATUS_SUCCESS, description);

			return response;
		

	}

}
