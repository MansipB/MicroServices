package com.stockexchange.service;

import java.util.List;

import com.stockexchange.entity.StockExchangeEntity;
import com.stockexchange.exception.StockExchangeAlreadyExistsException;

public interface StockExchangeService {
	public StockExchangeEntity createStockExchange(StockExchangeEntity stockExchange)
			throws StockExchangeAlreadyExistsException;

	StockExchangeEntity getStockExchangeById(Long stockExchangeId);

	List<StockExchangeEntity> getAllStockExchange();

	long getNumberOfStockExchange();

	StockExchangeEntity getStockExchangeByName(String stockExchangeName);
}
