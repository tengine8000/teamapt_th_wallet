package com.emmanueltorty.walletapp.exceptions;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class GlobalExceptionHandler {
	
	@Autowired
	private ErrorDetails err;
	
	// handle specific exceptions
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request)
	{
		err.setTimestamp(new Date());
		err.setMessage(ex.getMessage());
		err.setDetails(request.getDescription(false));
		
		return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
		
	}
	// handle specific exceptions
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request)
	{
		err.setTimestamp(new Date());
		err.setMessage("Validation Error");
		err.setDetails(ex.getBindingResult().getFieldError().getDefaultMessage());
		
		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
		
	}

	// handle specific exceptions
	@ExceptionHandler(UniqueFieldException.class)
	public ResponseEntity<?> handleUniqueFieldException(UniqueFieldException ex, WebRequest request)
	{
		err.setTimestamp(new Date());
		err.setMessage("Validation Error");
		err.setDetails(ex.getMessage());
		
		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
		
	}
	// handle specific exceptions
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<?> handleUsernameNotFoundException(UsernameNotFoundException ex, WebRequest request)
	{
		err.setTimestamp(new Date());
		err.setMessage("Validation Error");
		err.setDetails(ex.getMessage());
		
		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
		
	}
	
	// handle specific exceptions
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Exception ex, WebRequest request)
	{
		err.setTimestamp(new Date());
		err.setMessage(ex.getMessage());
		err.setDetails(request.getDescription(false));
		
		return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	
	// handle global exceptions
	

}