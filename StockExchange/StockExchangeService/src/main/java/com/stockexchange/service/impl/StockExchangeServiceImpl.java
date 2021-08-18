package com.stockexchange.service.impl;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stockexchange.dao.StockExchangeRepository;
import com.stockexchange.entity.StockExchangeEntity;
import com.stockexchange.exception.StockExchangeAlreadyExistsException;
import com.stockexchange.service.StockExchangeService;

@Service
public class StockExchangeServiceImpl implements StockExchangeService {

	@Autowired
	private StockExchangeRepository stockExchangeRepository;

	@Override
	public StockExchangeEntity createStockExchange(StockExchangeEntity stockExchange)
			throws StockExchangeAlreadyExistsException {
		StockExchangeEntity existingStockExchange = stockExchangeRepository
				.findBystockExchangeName(stockExchange.getStockExchangeName());
		if (existingStockExchange != null) {
			throw new StockExchangeAlreadyExistsException();
		}

		return stockExchangeRepository.save(stockExchange);

	}

	@Override
	public StockExchangeEntity getStockExchangeById(Long stockExchangeId) {
		Optional<StockExchangeEntity> stockExchange = stockExchangeRepository.findById(stockExchangeId);
		if (!stockExchange.isPresent()) {
			throw new ObjectNotFoundException(stockExchangeId, "Stock Exchange");
		}
		return stockExchange.get();
	}

	@Override
	public List<StockExchangeEntity> getAllStockExchange() {
		return stockExchangeRepository.findAll();
	}

	@Override
	public long getNumberOfStockExchange() {
		return stockExchangeRepository.count();
	}

	@Override
	public StockExchangeEntity getStockExchangeByName(String stockExchangeName) {
		StockExchangeEntity stockExchangeEntity = stockExchangeRepository.findBystockExchangeName(stockExchangeName);
		if (stockExchangeEntity == null) {
			throw new ObjectNotFoundException(stockExchangeName, "Stock Exchange");
		}
		return stockExchangeEntity;
	}

}
