package com.stockexchange.exception;

import org.springframework.stereotype.Service;

@Service
public class StockExchangeAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 1L;

	public StockExchangeAlreadyExistsException() {
		super("StockExchange Already Exists");

	}

}
