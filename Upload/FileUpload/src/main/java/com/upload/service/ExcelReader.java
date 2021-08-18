package com.upload.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.upload.entity.StockPriceEntity;

@Service
public class ExcelReader {

	public List<StockPriceEntity> readExcel(MultipartFile file) throws IOException {
		FileInputStream fis = (FileInputStream) file.getInputStream();
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);
		List<StockPriceEntity> stockPriceList = new ArrayList<>();

		for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
			StockPriceEntity stockPrice = new StockPriceEntity();
			Row row = sheet.getRow(2);
			stockPrice.setCompanyId((long) row.getCell(0).getNumericCellValue());
			stockPrice.setStockExchangeName(row.getCell(1).getStringCellValue());
			stockPrice.setSharePrice(row.getCell(2).getNumericCellValue());
			stockPrice.setDate(row.getCell(3).getDateCellValue());
			SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm:ss");
			stockPrice.setTime(formatTime.format(row.getCell(4).getDateCellValue()));
			stockPriceList.add(stockPrice);
		}
		return stockPriceList;

	}
}
