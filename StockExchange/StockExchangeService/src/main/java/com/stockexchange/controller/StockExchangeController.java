package com.stockexchange.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stockexchange.entity.StockExchangeEntity;
import com.stockexchange.exception.StockExchangeAlreadyExistsException;
import com.stockexchange.service.impl.StockExchangeServiceImpl;

@RestController
@CrossOrigin
public class StockExchangeController {

	@Autowired
	StockExchangeServiceImpl stockExchangeService;

	@PostMapping("admin/stock-exchanges/add")
	public ResponseEntity<StockExchangeEntity> createStockExchange(@RequestBody StockExchangeEntity stockExchange)
			throws StockExchangeAlreadyExistsException {
		return ResponseEntity.ok(stockExchangeService.createStockExchange(stockExchange));
	}

	@GetMapping("/stock-exchanges")
	public ResponseEntity<List<StockExchangeEntity>> getAllStockExchange() {
		List<StockExchangeEntity> allStockExchanges = stockExchangeService.getAllStockExchange();
		return ResponseEntity.ok(allStockExchanges);
	}

	@GetMapping("/stock-exchanges/length")
	public ResponseEntity<Long> getNumberOfStockExchange() {
		Long numberOfStockExchange = stockExchangeService.getNumberOfStockExchange();
		return ResponseEntity.ok(numberOfStockExchange);
	}

	@DeleteMapping("/stock-exchanges/{stockExchangeId}")
	public ResponseEntity<Object> deleteStockExchange() {
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("Deleting a Stock Exchange is not Permitted");
	}

	@GetMapping("/stock-exchanges/{stockExchangeName}")
	public ResponseEntity<StockExchangeEntity> getStockExchangeByName(@PathVariable("stockExchangeName") String name) {
		return ResponseEntity.ok(stockExchangeService.getStockExchangeByName(name));
	}

}
