package com.aws.lambda.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.aws.lambda.enums.ErrorType;

public class GenericResponse<T extends Serializable> {

	private T response;
	private boolean isSucceeded;
	private List<ErrorType> errors = new ArrayList<ErrorType>();
	
	public GenericResponse(T response) {
		super();
		this.response = response;
		this.isSucceeded = true;
	}
	
	public GenericResponse(T response, boolean isSucceeded) {
		super();
		this.response = response;
		this.isSucceeded = isSucceeded;
	}
	
	public GenericResponse(T response, boolean isSucceeded, List<ErrorType> errors) {
		super();
		this.response = response;
		this.isSucceeded = isSucceeded;
		this.errors = errors;
	}
	
	public GenericResponse(ErrorType error) {
		super();
		this.isSucceeded = false;
		this.errors.add(error);
	}
	
	public GenericResponse(ErrorType ... errors) {
		super();
		this.isSucceeded = false;
		for (ErrorType errorType : errors) {
			this.errors.add(errorType);			
		}
	}
	
	public T getResponse() {
		return response;
	}
	
	public void setResponse(T response) {
		this.response = response;
	}
	
	public boolean isSucceeded() {
		return isSucceeded;
	}
	
	public void setSucceeded(boolean isSucceeded) {
		this.isSucceeded = isSucceeded;
	}
	
	public List<ErrorType> getErrors() {
		return errors;
	}
	
	public void setErrors(List<ErrorType> errors) {
		this.errors = errors;
	}
	
	public void addError(ErrorType error) {
		errors.add(error);
	}
}
