package com.apigateway.exception;

import org.springframework.stereotype.Service;

@Service
public class UserAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 1L;

	public UserAlreadyExistsException() {
		super("User Already Exists");

	}

}
