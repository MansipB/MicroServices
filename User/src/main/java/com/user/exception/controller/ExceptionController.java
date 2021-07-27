
package com.user.exception.controller;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.user.exception.CustomUserNotFoundException;
import com.user.exception.EntityAlreadyExistsException;
import com.user.exception.UserNameNotFoundException;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(value = EntityAlreadyExistsException.class)
	public ResponseEntity<Object> entityAlreadyExistsException(EntityAlreadyExistsException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = UserNameNotFoundException.class)
	public ResponseEntity<Object> usernameNotFoundExceptionexception(UserNameNotFoundException exception) {
		return new ResponseEntity<>("Username not found", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = EntityNotFoundException.class)
	public ResponseEntity<Object> entityNotFoundExceptionexception(EntityNotFoundException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = CustomUserNotFoundException.class)
	public ResponseEntity<Object> userNotFoundExceptionexception(CustomUserNotFoundException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}
}
