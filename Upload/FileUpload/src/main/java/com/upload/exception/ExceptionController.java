package com.upload.exception;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(value = FileAlreadyExistsException.class)
	public ResponseEntity<Object> fileAlreadyExistsException(FileAlreadyExistsException exception) {
		return new ResponseEntity<>("File Already Exists.Try Another File", HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = IOException.class)
	public ResponseEntity<Object> exception(IOException exception) {
		return new ResponseEntity<>("File Unreadable", HttpStatus.BAD_REQUEST);
	}

}
