package com.vanguard.weatherapi.utility;

import java.util.List;
import java.util.stream.Collectors;

import com.jayway.jsonpath.JsonPath;

public class WeatherUtility {
	
	public static String getvalue(String json)
	{
		
		List<String> descriptionList = JsonPath.read(json, "$.weather[*].description");
		
		
		return descriptionList.stream().collect(Collectors.joining(","));
		
	}

}
