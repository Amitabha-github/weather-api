package com.vanguard.wseatherapi.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.vanguard.weatherapi.controller.WeatherController;
import com.vanguard.weatherapi.service.WeatherService;
import com.vanguard.weatherapi.utility.WeatherConstants;

@RunWith(SpringJUnit4ClassRunner.class)
public class WeatherControllerTest {
	
	private MockMvc mockMvc;
	
	@Mock
	private WeatherService service;
	
	@InjectMocks
	private WeatherController controller;

	@Before
	public void setUp() throws Exception {
		
		mockMvc= MockMvcBuilders.standaloneSetup(controller).build();
	}
	

	//test API with correct City And correct AppId
	@Test
	public void testAPIWithCorrectInputs1() throws Exception {
		
		MultiValueMap<String,String> paramMap=new LinkedMultiValueMap<String,String>(); 
		
		paramMap.add(WeatherConstants.REQ_PARAM_APPID, "b398bb9d14a250e65691218f088f3d6d");
		paramMap.add(WeatherConstants.REQ_PARAM_CITY, "london");
		
		mockMvc
		.perform(get(WeatherConstants.WEATHER_CONDITION_API_ENDPOINT)
				.params(paramMap)
				.accept(MediaType.APPLICATION_JSON)
				)
		.andExpect(jsonPath("$.status",Matchers.is(WeatherConstants.STATUS_SUCCESS)));
		
			
	}
	
	//test API with correct City , correct Country And correct AppId
	@Test
	public void testAPIWithCorrectInputs2() throws Exception {
		
		MultiValueMap<String,String> paramMap=new LinkedMultiValueMap<String,String>(); 
		
		paramMap.add(WeatherConstants.REQ_PARAM_APPID, "b398bb9d14a250e65691218f088f3d6d");
		paramMap.add(WeatherConstants.REQ_PARAM_CITY, "kolkata");
		paramMap.add(WeatherConstants.REQ_PARAM_COUNTRY, "IN");
		
		mockMvc
		.perform(get(WeatherConstants.WEATHER_CONDITION_API_ENDPOINT)
				.params(paramMap)
				.accept(MediaType.APPLICATION_JSON)
				)
		.andExpect(jsonPath("$.status",Matchers.is(WeatherConstants.STATUS_SUCCESS)));
		
			
	}
	//test API without City And AppId
		@Test
		public void testAPIWithIncorrectInputs1() throws Exception {
				
				MultiValueMap<String,String> paramMap=new LinkedMultiValueMap<String,String>(); 
				
				//paramMap.add(WeatherConstants.REQ_PARAM_APPID, "b398bb9d14a250e65691218f088f3d6d");
				//paramMap.add(WeatherConstants.REQ_PARAM_CITY, "london");
				
				mockMvc
				.perform(get(WeatherConstants.WEATHER_CONDITION_API_ENDPOINT)
						.params(paramMap)
						.accept(MediaType.APPLICATION_JSON)
						)
				.andExpect(jsonPath("$.status",Matchers.is(WeatherConstants.STATUS_FAILURE)));
				
					
		}
	//test API without City And correct AppId
		@Test
		public void testAPIWithIncorrectInputs2() throws Exception {
			
			MultiValueMap<String,String> paramMap=new LinkedMultiValueMap<String,String>(); 
			
			paramMap.add(WeatherConstants.REQ_PARAM_APPID, "b398bb9d14a250e65691218f088f3d6d");
			//paramMap.add(WeatherConstants.REQ_PARAM_CITY, "london");
			
			mockMvc
			.perform(get(WeatherConstants.WEATHER_CONDITION_API_ENDPOINT)
					.params(paramMap)
					.accept(MediaType.APPLICATION_JSON)
					)
			.andExpect(jsonPath("$.status",Matchers.is(WeatherConstants.STATUS_FAILURE)));
			
				
		}
		
	//test API without AppId
		 @Test
		public void testAPIWithIncorrectInputs3() throws Exception {
					
					MultiValueMap<String,String> paramMap=new LinkedMultiValueMap<String,String>(); 
					
					//paramMap.add(WeatherConstants.REQ_PARAM_APPID, "b398bb9d14a250e65691218f088f3d6d");
					paramMap.add(WeatherConstants.REQ_PARAM_CITY, "london");
					
					mockMvc
					.perform(get(WeatherConstants.WEATHER_CONDITION_API_ENDPOINT)
							.params(paramMap)
							.accept(MediaType.APPLICATION_JSON)
							)
					.andExpect(jsonPath("$.status",Matchers.is(WeatherConstants.STATUS_FAILURE)));
					
						
			}

		
}
