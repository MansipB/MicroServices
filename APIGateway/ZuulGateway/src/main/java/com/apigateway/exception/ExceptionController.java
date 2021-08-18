
package com.apigateway.exception;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(value = UserAlreadyExistsException.class)
	public ResponseEntity<Object> entityAlreadyExistsException(UserAlreadyExistsException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = EntityNotFoundException.class)
	public ResponseEntity<Object> entityNotFoundExceptionexception(EntityNotFoundException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		List<String> error = new ArrayList<>();
		error.add(ex.getLocalizedMessage());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
