package com.mobile.application.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ItemExceptionController {
	
	@ExceptionHandler(value =ItemNotfoundException.class)
	public ResponseEntity<Object> exception (ItemNotfoundException exception)
	{
	 return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);


}
}
