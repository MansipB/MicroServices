package com.user.exception;

import org.springframework.stereotype.Service;

@Service
public class EntityAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 1L;

	public EntityAlreadyExistsException() {
		super("Entity Already Exists");

	}

	public EntityAlreadyExistsException(String entityName) {
		super(entityName + " Already Exists");
	}

}
