package com.vanguard.weatherapi;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.vanguard.weatherapi.WeatherApiApplication;
import com.vanguard.weatherapi.dao.WeatherDAO;
import com.vanguard.weatherapi.service.WeatherService;
import com.vanguard.weatherapi.utility.WeatherConstants;


@RunWith(SpringRunner.class)
@SpringBootTest
(
webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
classes = WeatherApiApplication.class
)


@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application_test.properties")
class WeatherApiApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	// Test with correct CityName and appiD
	@Test
	void testAPIWithCorrectInputs1() throws Exception{

		MultiValueMap<String,String> paramMap=new LinkedMultiValueMap<String,String>(); 

		paramMap.add(WeatherConstants.REQ_PARAM_APPID, "b398bb9d14a250e65691218f088f3d6d");
		paramMap.add(WeatherConstants.REQ_PARAM_CITY, "london");
		

		mockMvc
		.perform(MockMvcRequestBuilders.get(WeatherConstants.WEATHER_CONDITION_API_ENDPOINT)
				.params(paramMap)
				.accept(MediaType.APPLICATION_JSON)
				)
		.andExpect(jsonPath("$.status",Matchers.is(WeatherConstants.STATUS_SUCCESS)));

	}	

	// Test with correct CityName, country and appiD
	@Test
	void testAPIWithCorrectInputs2() throws Exception{

		MultiValueMap<String,String> paramMap=new LinkedMultiValueMap<String,String>(); 

		paramMap.add(WeatherConstants.REQ_PARAM_APPID, "cb67ca9c79eea2ece185fccdb8d3effe");
		paramMap.add(WeatherConstants.REQ_PARAM_CITY, "london");
		paramMap.add(WeatherConstants.REQ_PARAM_COUNTRY, "UK");



		mockMvc
		.perform(MockMvcRequestBuilders.get(WeatherConstants.WEATHER_CONDITION_API_ENDPOINT)
				.params(paramMap)
				.accept(MediaType.APPLICATION_JSON)
				)
		.andExpect(jsonPath("$.status",Matchers.is(WeatherConstants.STATUS_SUCCESS)));

	}


	// Test with wrong iD
	@Test
	void testAPIWithWrongInputs1() throws Exception{

		MultiValueMap<String,String> paramMap=new LinkedMultiValueMap<String,String>(); 

		paramMap.add(WeatherConstants.REQ_PARAM_APPID, "wrongid");
		paramMap.add(WeatherConstants.REQ_PARAM_CITY, "london");


		mockMvc
		.perform(MockMvcRequestBuilders.get(WeatherConstants.WEATHER_CONDITION_API_ENDPOINT)
				.params(paramMap)
				.accept(MediaType.APPLICATION_JSON)
				)
		.andExpect(jsonPath("$.status",Matchers.is(WeatherConstants.STATUS_FAILURE)));

	}

	// Test with wrong city name
	@Test
	void testAPIWithWrongInputs2() throws Exception{

		MultiValueMap<String,String> paramMap=new LinkedMultiValueMap<String,String>(); 

		paramMap.add(WeatherConstants.REQ_PARAM_APPID, "44bb3da54b9491836fd699376eb651f5");
		paramMap.add(WeatherConstants.REQ_PARAM_CITY, "london123");


		mockMvc
		.perform(MockMvcRequestBuilders.get(WeatherConstants.WEATHER_CONDITION_API_ENDPOINT)
				.params(paramMap)
				.accept(MediaType.APPLICATION_JSON)
				)
		.andExpect(jsonPath("$.status",Matchers.is(WeatherConstants.STATUS_FAILURE)))
		.andExpect(jsonPath("$.message",Matchers.is("city not found")));

	}

	// Test with wrong city name and country name combination
	@Test
	void testAPIWithWrongInputs3() throws Exception{

		MultiValueMap<String,String> paramMap=new LinkedMultiValueMap<String,String>(); 

		paramMap.add(WeatherConstants.REQ_PARAM_APPID, "ffa8f5c6ad9112d2cceeaeb1eec56c60");
		paramMap.add(WeatherConstants.REQ_PARAM_CITY, "london123");
		paramMap.add(WeatherConstants.REQ_PARAM_COUNTRY, "US");

		mockMvc
		.perform(MockMvcRequestBuilders.get(WeatherConstants.WEATHER_CONDITION_API_ENDPOINT)
				.params(paramMap)
				.accept(MediaType.APPLICATION_JSON)
				)
		.andExpect(jsonPath("$.status",Matchers.is(WeatherConstants.STATUS_FAILURE)))
		.andExpect(jsonPath("$.message",Matchers.is("city not found")));

	}

	// Test for hourly max limit of 5 API calls for each appId
	@Test
	void testAPIForHourlyMaxLimit() throws Exception{

		MultiValueMap<String,String> paramMap=new LinkedMultiValueMap<String,String>(); 

		paramMap.add(WeatherConstants.REQ_PARAM_APPID, "99234824e18722da324ecfd4f9034b94");
		paramMap.add(WeatherConstants.REQ_PARAM_CITY, "london");

		for(int count =0; count<5; count++)
		{
			mockMvc
			.perform(MockMvcRequestBuilders.get(WeatherConstants.WEATHER_CONDITION_API_ENDPOINT)
					.params(paramMap)
					.accept(MediaType.APPLICATION_JSON)
					)
			.andExpect(jsonPath("$.status",Matchers.is(WeatherConstants.STATUS_SUCCESS)));

		}

		mockMvc
		.perform(MockMvcRequestBuilders.get(WeatherConstants.WEATHER_CONDITION_API_ENDPOINT)
				.params(paramMap)
				.accept(MediaType.APPLICATION_JSON)
				)
		.andExpect(jsonPath("$.status",Matchers.is(WeatherConstants.STATUS_FAILURE)))
		.andExpect(jsonPath("$.message",Matchers.is(WeatherConstants.MSG_HOURLY_LMT)));

	}
}
