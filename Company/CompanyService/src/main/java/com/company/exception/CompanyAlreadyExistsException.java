package com.company.exception;

import org.springframework.stereotype.Service;

@Service
public class CompanyAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 1L;

	public CompanyAlreadyExistsException() {
		super();
	}

}
