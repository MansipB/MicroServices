package com.sector.exception;

import org.springframework.stereotype.Service;

@Service
public class SectorAlreadyExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SectorAlreadyExistsException() {
		super("Sector Already Exists");

	}
}
