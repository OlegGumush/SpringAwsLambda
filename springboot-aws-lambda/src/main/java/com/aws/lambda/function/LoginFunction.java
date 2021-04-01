package com.aws.lambda.function;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.aws.lambda.bl.AuthBL;
import com.aws.lambda.enums.ErrorType;
import com.aws.lambda.model.LoginModel;
import com.aws.lambda.response.GenericResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

@Component
public class LoginFunction extends BaseFunction {

	@Autowired
	private AuthBL authBL;
	
    Logger logger = LoggerFactory.getLogger(LoginFunction.class);

	@Override
	public APIGatewayProxyResponseEvent apply(APIGatewayProxyRequestEvent requestEvent) {

		logger.info("Start login");
		try {
			LoginModel model = objectMapper.readValue(requestEvent.getBody(), LoginModel.class);
			GenericResponse<Serializable> response = authBL.processLogin(model);
			
			if (response.isSucceeded()) {
				return ok(response);
			} else { 
				return badRequest(response);
			}	
		} catch (JsonProcessingException e) {
			return badRequest(new GenericResponse<Serializable>(ErrorType.FailedToParseJsonRequest));
		} catch (Exception e) {
			return internalServerError();
		}
	}
}