
package com.asbnotebook.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.asbnotebook.dto.ApiError;
import com.asbnotebook.dto.Codes;

@ControllerAdvice
public class ApiErrorHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		System.out.println(request);
		ApiError apiError = new ApiError();
		apiError.setCount(ex.getBindingResult().getErrorCount());
		apiError.setStatus(HttpStatus.BAD_REQUEST);
		apiError.setError("Validation failed");
		List<String> errors = new ArrayList<>();
		Codes co= new Codes();
		BindingResult bindingResult = ex.getBindingResult();
		bindingResult.getAllErrors().forEach(error -> errors.add(error.getCode()));
		List<ObjectError> er = bindingResult.getAllErrors();
		for (ObjectError objectError : er) {
			co.setDefaultMessage(objectError.getDefaultMessage());
			co.setArguments(objectError.getArguments());
			co.setCode(objectError.getCodes());
			co.setObjectName(objectError.getObjectName());
		}
		List<Codes> c = new ArrayList<Codes>();
		Codes c1 = new Codes();
		c1.setCodes1(errors);
		c1.setDefaultMessage(co.getDefaultMessage());
		c1.setArguments(co.getArguments());
		c1.setCode(co.getCode());
		c1.setObjectName(co.getObjectName());
		c.add(c1);
		apiError.setErrors(c);
		
		//System.out.println(request.getDescription(false));
		String name = ((ServletWebRequest)request).getRequest().getRequestURI().toString();
		apiError.setPath(name);
		return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
	}

}
