package com.aws.lambda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.aws.lambda.function.LoginFunction;

@SpringBootApplication
public class SpringbootAwsLambdaApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootAwsLambdaApplication.class, args);
	}
	
//	@Bean
//	public Function<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> inputApiEvent() {
//		return v -> {
//			
//			return new APIGatewayProxyResponseEvent().withBody(v.getBody());
//		};
//	}
	
	@Bean
	public LoginFunction login() {
		return new LoginFunction();
	}
}
