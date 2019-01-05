package com.example.springmicroservices.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.springmicroservices.beans.ExceptionResponse;

@RestController
@ControllerAdvice
public class CustomExceptionController extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)  // This method handling the exceptions of Exception class type
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {	
		ExceptionResponse expRes = new ExceptionResponse(""+HttpStatus.BAD_REQUEST, 
				"Some issue has come while 8th floor started the foriegn faculty: "+ex);
		return new ResponseEntity(expRes ,HttpStatus.NOT_FOUND);
	}

	@Override  //This method handles the bad request exceptions[Validation issue], Overrided the method of ResponseEntityExceptionHandler
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionResponse expRes = new ExceptionResponse("" + HttpStatus.BAD_REQUEST, "This is the bad request generated --:" + ex.getBindingResult().getFieldError());
		return new ResponseEntity<Object>(expRes, HttpStatus.BAD_REQUEST);
	}
	
	
	
}
