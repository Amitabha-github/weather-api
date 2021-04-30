package com.vanguard.weatherapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vanguard.weatherapi.model.WeatherResponse;
import com.vanguard.weatherapi.utility.WeatherConstants;

@ControllerAdvice
public class WeatherApiExceptionHandler extends ResponseEntityExceptionHandler{

	
    

	@ExceptionHandler(WeatherException.class)
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
    		WeatherException wexp) {
        
        WeatherErrorMessage msg;
		try {
			msg = new ObjectMapper().readValue(wexp.getError(), WeatherErrorMessage.class);
		
			return new ResponseEntity<Object>(new WeatherResponse(WeatherConstants.STATUS_FAILURE, msg.getMessage())
				,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(new WeatherResponse(WeatherConstants.STATUS_FAILURE, WeatherConstants.MSG_RUNTIME_ERR)
					,HttpStatus.OK);
		}
    }
	
}
