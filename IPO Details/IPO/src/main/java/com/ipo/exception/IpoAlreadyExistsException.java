package com.ipo.exception;

import org.springframework.stereotype.Service;

@Service
public class IpoAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 1L;

	public IpoAlreadyExistsException() {
		super("Ipo already exists");
	}

}
