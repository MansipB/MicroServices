package com.stock.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.stock.dao.StockPriceRepository;
import com.stock.entity.CompanyEntity;
import com.stock.entity.StockExchangeEntity;
import com.stock.entity.StockPriceEntity;

@Service
public class ExcelReader {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	StockPriceRepository stockPriceRepository;

	public List<StockPriceEntity> readExcel(MultipartFile file) throws IOException {
		FileInputStream fis = (FileInputStream) file.getInputStream();
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);
		List<StockPriceEntity> stockPriceList = new ArrayList<>();
		for (int i = 1; i < sheet.getPhysicalNumberOfRows() - 1; i++) {
			StockPriceEntity stockPrice = new StockPriceEntity();
			Row row = sheet.getRow(i);
			Long companyId = (long) row.getCell(0).getNumericCellValue();
			CompanyEntity company = restTemplate.getForObject("http://localhost:9005/companies/" + companyId,
					CompanyEntity.class);
			stockPrice.setCompany(company);
			String stockExchangeName = row.getCell(1).getStringCellValue();
			StockExchangeEntity stockExchange = restTemplate.getForObject(
					"http://localhost:9004/stock-exchanges/" + stockExchangeName, StockExchangeEntity.class);
			stockPrice.setStockExchange(stockExchange);
			stockPrice.setSharePrice(row.getCell(2).getNumericCellValue());
			stockPrice.setDate(row.getCell(3).getDateCellValue());
			if (!stockPriceRepository.existsByDate(stockPrice.getDate())) {
				stockPriceList.add(stockPrice);
			}
		}
		return stockPriceList;
	}
}
