package com.stock.service;

import java.sql.Date;
import java.util.List;

import com.stock.entity.StockPriceEntity;
import com.stock.exception.StockPriceAlreadyExistsException;

public interface StockPriceService {

	public StockPriceEntity addStockPrice(StockPriceEntity stockPrice) throws StockPriceAlreadyExistsException;

	public List<StockPriceEntity> addAllStockPrices(List<StockPriceEntity> stockPrices);

	public List<StockPriceEntity> getAllStockPrices();

	List<StockPriceEntity> getStockPricesBySectorId(Long id);

	List<StockPriceEntity> getStockPricesByCompanyId(Long companyId, Long stockExchangeId, Date from, Date to);

	List<StockPriceEntity> getStockPricesByCompanyIdAndMonth(Long companyId, Long stockExchangeId, String monthAndYear);
}
