package com.stock.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.stock.entity.StockPriceEntity;
import com.stock.exception.StockPriceAlreadyExistsException;
import com.stock.service.ExcelReader;
import com.stock.service.impl.StockPriceServiceImpl;

@RestController
@CrossOrigin
public class StockPriceController {

	@Autowired
	StockPriceServiceImpl stockPriceService;

	@Autowired
	ExcelReader excelReader;

	@PostMapping("/admin/stock-prices")
	public ResponseEntity<Long> addStockPrice(@RequestBody StockPriceEntity stockPrice)
			throws StockPriceAlreadyExistsException {
		return ResponseEntity.status(HttpStatus.CREATED).body(stockPriceService.addStockPrice(stockPrice).getId());
	}

	@GetMapping("/stock-prices")
	public ResponseEntity<List<StockPriceEntity>> getAllStockPrices() {
		return ResponseEntity.status(HttpStatus.CREATED).body(stockPriceService.getAllStockPrices());
	}

	@PostMapping("stock-prices/uploadfile")
	public ResponseEntity<List<StockPriceEntity>> handleFileUpload(@RequestParam("file") MultipartFile file)
			throws IOException {
		List<StockPriceEntity> stockPrices = excelReader.readExcel(file);
		return ResponseEntity.status(HttpStatus.OK).body(stockPriceService.addAllStockPrices(stockPrices));

	}

	@GetMapping("stock-prices/companies/{companyId}/stockExchange/{stockExchangeId}/from/{from}/to/{to}")
	public ResponseEntity<List<StockPriceEntity>> getStockPricesByCompanyId(@PathVariable("companyId") Long companyId,
			@PathVariable("stockExchangeId") Long stockExchangeId, @PathVariable("from") Date from,
			@PathVariable("to") Date to) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(stockPriceService.getStockPricesByCompanyId(companyId, stockExchangeId, from, to));

	}

	@GetMapping("stock-prices/sectors/{sectorId}")
	public ResponseEntity<List<StockPriceEntity>> getStockPricesBySectorId(@PathVariable("sectorId") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(stockPriceService.getStockPricesBySectorId(id));

	}

	@GetMapping("stock-prices/companies/{companyId}/stockExchange/{stockExchangeId}/period/{time}")
	public ResponseEntity<List<StockPriceEntity>> getStockPricesByCompanyIdAndMonth(
			@PathVariable("companyId") Long companyId, @PathVariable("stockExchangeId") Long stockExchangeId,
			@PathVariable("time") String time) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(stockPriceService.getStockPricesByCompanyIdAndMonth(companyId, stockExchangeId, time));

	}

}
