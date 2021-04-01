package com.aws.lambda.function;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.aws.lambda.response.GenericResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class BaseFunction implements Function<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

	@Autowired
	protected ObjectMapper objectMapper;
	
	public APIGatewayProxyResponseEvent badRequest(GenericResponse<? extends Serializable> response){
		
		try {
			return new APIGatewayProxyResponseEvent()
			        .withStatusCode(400)
			        .withHeaders(headers)
			        .withBody(objectMapper.writeValueAsString(response));
		} catch (JsonProcessingException e) {
			
			return new APIGatewayProxyResponseEvent()
	                .withStatusCode(500)
	                .withHeaders(headers);
		}
	}

	protected APIGatewayProxyResponseEvent ok(GenericResponse<? extends Serializable> response) {

		try {
			return new APIGatewayProxyResponseEvent()
			        .withStatusCode(200)
			        .withHeaders(headers)
			        .withBody(objectMapper.writeValueAsString(response));
		} catch (JsonProcessingException e) {
			
			return new APIGatewayProxyResponseEvent()
	                .withStatusCode(500)
	                .withHeaders(headers);
		}
	}
	
	protected APIGatewayProxyResponseEvent internalServerError() {
		return new APIGatewayProxyResponseEvent()
		        .withStatusCode(500)
		        .withHeaders(headers);
	}

	protected static final Map<String, String> headers = new HashMap<>();
	{
		headers.put("Content-type", "application/json");
	}
}
