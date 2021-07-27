package com.user.exception;

import org.springframework.stereotype.Component;

@Component
public class CustomUserNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public CustomUserNotFoundException() {
		super("User Not Found");

	}

	public CustomUserNotFoundException(String searchType, String searchValue) {
		super("User with " + searchType + ": " + searchValue + " not found");
	}

}
