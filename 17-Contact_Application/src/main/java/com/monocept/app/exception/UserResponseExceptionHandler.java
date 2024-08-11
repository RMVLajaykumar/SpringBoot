package com.monocept.app.exception;

import java.nio.file.AccessDeniedException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserResponseExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<UserErrorResponse> handleException(UserNotFoundException exc) {

		// create a Student Error Message
		UserErrorResponse error = new UserErrorResponse();

		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());

		// return ResponseEntity

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<UserErrorResponse> handleException(ContactApiException exc) {

		// create a Student Error Message
		UserErrorResponse error = new UserErrorResponse();

		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<UserErrorResponse> handleException(AccessDeniedException exc) {

		// create a Student Error Message
		UserErrorResponse error = new UserErrorResponse();

		error.setStatus(HttpStatus.UNAUTHORIZED.value());
		error.setMessage(exc.getClass().getSimpleName());
		error.setTimeStamp(System.currentTimeMillis());

		// return ResponseEntity

		return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler
	public ResponseEntity<UserErrorResponse> handleException(Exception exc) {

		// create a Student Error Message
		UserErrorResponse error = new UserErrorResponse();
		System.out.println("printing error");
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(exc.getClass().getSimpleName());
		exc.printStackTrace();
		error.setTimeStamp(System.currentTimeMillis());

		// return ResponseEntity

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	

	@ExceptionHandler
	public ResponseEntity<UserErrorResponse> handleException(NoRecordFoundException exc) {

		// create a Student Error Message
		UserErrorResponse error = new UserErrorResponse();

		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());

		// return ResponseEntity

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
}