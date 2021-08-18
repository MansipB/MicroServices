package com.company.exception;

import javax.persistence.EntityNotFoundException;

import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(value = ObjectNotFoundException.class)
	public ResponseEntity<Object> exception(ObjectNotFoundException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = CompanyAlreadyExistsException.class)
	public ResponseEntity<Object> companyAlreadyExistsException(CompanyAlreadyExistsException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = EntityNotFoundException.class)
	public ResponseEntity<Object> entityNotFoundExceptionexception(EntityNotFoundException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}

}
