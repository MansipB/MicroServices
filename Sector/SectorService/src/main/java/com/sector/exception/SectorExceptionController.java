package com.sector.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SectorExceptionController {

	@ExceptionHandler(value = SectorAlreadyExistsException.class)
	public ResponseEntity<Object> sectorAlreadyExistsException(SectorAlreadyExistsException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}

}
