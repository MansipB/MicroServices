package com.user.exception;

import org.springframework.stereotype.Service;

@Service
public class UserNameNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public UserNameNotFoundException() {
		super("UserName not found");

	}

	public UserNameNotFoundException(String username) {
		super("UserName : " + username + " not found");

	}

}
