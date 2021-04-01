package com.aws.lambda.bl;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import com.aws.lambda.model.LoginModel;
import com.aws.lambda.response.GenericResponse;

@Service
public class AuthBL {
	
	public GenericResponse<Serializable> processLogin(LoginModel model) {	
		
		return new GenericResponse<>(model);
	}
}
