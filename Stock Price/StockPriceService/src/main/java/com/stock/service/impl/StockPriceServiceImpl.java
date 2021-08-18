package com.stock.service.impl;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.stock.dao.StockPriceRepository;
import com.stock.entity.StockPriceEntity;
import com.stock.exception.StockPriceAlreadyExistsException;
import com.stock.service.StockPriceService;

@Service
public class StockPriceServiceImpl implements StockPriceService {

	@Autowired
	StockPriceRepository stockPriceRepository;

	@Override
	public StockPriceEntity addStockPrice(StockPriceEntity stockPrice) throws StockPriceAlreadyExistsException {
		if (stockPriceRepository.existsByDate(stockPrice.getDate())) {
			throw new StockPriceAlreadyExistsException();
		}

		return stockPriceRepository.save(stockPrice);

	}

	@Override
	public List<StockPriceEntity> addAllStockPrices(List<StockPriceEntity> stockPrices) {
		return stockPriceRepository.saveAll(stockPrices);
	}

	@Override
	public List<StockPriceEntity> getAllStockPrices() {
		return stockPriceRepository.findAll();
	}

	@Override
	public List<StockPriceEntity> getStockPricesByCompanyId(Long companyId, Long stockExchangeId, Date from, Date to) {
		List<StockPriceEntity> findByCompanyId = stockPriceRepository.findByCompanyIdAndStockExchangeIdAndDateBetween(
				companyId, stockExchangeId, from, to, Sort.by(Sort.Direction.ASC, "Date"));
		if (findByCompanyId.isEmpty()) {
			throw new EntityNotFoundException("No Stock Prices with this company id and stock Exchange id found");
		}
		return findByCompanyId;
	}

	@Override
	public List<StockPriceEntity> getStockPricesByCompanyIdAndMonth(Long companyId, Long stockExchangeId,
			String monthAndYear) {
		List<StockPriceEntity> company = stockPriceRepository.findByCompanyIdAndDateContaining(companyId,
				stockExchangeId, monthAndYear);
		if (company.isEmpty()) {
			throw new EntityNotFoundException("No Stock Prices with this company id found");
		}
		return company;
	}

	@Override
	public List<StockPriceEntity> getStockPricesBySectorId(Long id) {

		List<StockPriceEntity> findByCompanyId = stockPriceRepository.findBySectorId(id);
		if (findByCompanyId.isEmpty()) {
			throw new EntityNotFoundException("No Stock Prices with this sector id found");
		}
		return findByCompanyId;
	}

}
