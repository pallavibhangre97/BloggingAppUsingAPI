package com.blogging.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blogging.payload.ApiResponse;

//How to handle Request with user id not found
//https://www.youtube.com/watch?v=4Y4i-edzzMk&list=PL0zysOflRCen-GihOcm1hZfYAlwr63K_M&index=13

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
		String message = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message, false);
		return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> methodArgumentNotValid(MethodArgumentNotValidException ex) {
		Map<String, String> newMap = new HashMap<>();
		List<ObjectError> errorList = ex.getBindingResult().getAllErrors();
		for (ObjectError objectError : errorList) {
			String fieldName = ((FieldError) objectError).getField();
			String messag = objectError.getDefaultMessage();
			newMap.put(fieldName, messag);
		}
		return new ResponseEntity<>(newMap, HttpStatus.NOT_FOUND);
	}
}
