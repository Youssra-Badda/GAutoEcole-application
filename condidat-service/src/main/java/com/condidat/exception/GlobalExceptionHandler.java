package com.condidat.exception;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
		@ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
	        String errorMessage = ex.getBindingResult().getFieldError().getDefaultMessage();
	        return ResponseEntity.unprocessableEntity().body(errorMessage);
	    }
		 @ExceptionHandler(ResponseStatusException.class)
		    public ResponseEntity<String> handleResponseStatusException(ResponseStatusException ex) {
		        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
		    }
		 
		 
//		  @ExceptionHandler(MethodArgumentNotValidException.class)
//		    @ResponseBody
//		    public ErrorResponse handleValidationException(MethodArgumentNotValidException ex) {
//		        BindingResult result = ex.getBindingResult();
//		        StringBuilder errorMessage = new StringBuilder("Validation error(s): ");
//		        
//		        for (FieldError error : result.getFieldErrors()) {
//		            errorMessage.append(error.getField()).append(": ").append(error.getDefaultMessage()).append(", ");
//		        }
//		        
//		        // Remove the trailing comma and space
//		        errorMessage.setLength(errorMessage.length() - 2);
//
//		        // You can also log the error message if needed
//		        // logger.error(errorMessage.toString());
//
//		        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), errorMessage.toString());
//		    }
		 
		 
}
