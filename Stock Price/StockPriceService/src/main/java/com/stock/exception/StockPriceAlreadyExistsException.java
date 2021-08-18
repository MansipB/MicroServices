package com.stock.exception;

import org.springframework.stereotype.Service;

@Service
public class StockPriceAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 1L;

	public StockPriceAlreadyExistsException() {
		super("Stock Price for this date and time already Exists");
	}

}
