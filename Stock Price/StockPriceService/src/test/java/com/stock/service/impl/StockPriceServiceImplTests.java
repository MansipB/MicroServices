package com.stock.service.impl;

import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.stock.dao.StockPriceRepository;
import com.stock.entity.StockPriceEntity;

class StockPriceServiceImplTests {

	@InjectMocks
	StockPriceServiceImpl stockPriceService;

	@Mock
	StockPriceRepository stockPriceRepository;

	@Test
	void getAllStockPricesTest() {
		StockPriceEntity stockPrice1 = mock(StockPriceEntity.class);
		StockPriceEntity stockPrice2 = mock(StockPriceEntity.class);
		List<StockPriceEntity> stockPrices = new ArrayList<>();
		stockPrices.add(stockPrice1);
		stockPrices.add(stockPrice2);
	}
}
