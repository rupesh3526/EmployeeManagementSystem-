package com.ems.EmployeeManagementSystem.ExceptionHandle;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalException {

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<ErrorResponse> handlenoSuchElementException(NoSuchElementException ex,HttpServletRequest request ){
		
		
		ErrorResponse error = new ErrorResponse(
	            HttpStatus.NOT_FOUND.value(),
	            "Employee not found",
	            ex.getMessage(),
	            request.getRequestURI()
	            );
		
		 return ResponseEntity
		            .status(HttpStatus.NOT_FOUND)
		            .body(error);
		
	}
	
	@ExceptionHandler( WrongInputException.class)
	public ResponseEntity<ErrorResponse> handleWrongInputException( WrongInputException ex,HttpServletRequest request ){
		
		
		ErrorResponse error = new ErrorResponse(
	            HttpStatus.BAD_REQUEST.value(),
	            "Wrong Input",
	            ex.getMessage(),
	            request.getRequestURI()
	            );
		
		 return ResponseEntity
		            .status(HttpStatus.BAD_REQUEST)
		            .body(error);
		
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex,HttpServletRequest request) {

	    Map<String, String> errors = new HashMap<>();

	    ex.getBindingResult().getFieldErrors().forEach(error ->
	        errors.put(error.getField(), error.getDefaultMessage())
	    );
	    
	    
	    

	    return ResponseEntity.badRequest().body(errors);
	}

		
	
}
