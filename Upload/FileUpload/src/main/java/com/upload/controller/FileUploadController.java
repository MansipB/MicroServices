package com.upload.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.upload.entity.StockPriceEntity;
import com.upload.service.ExcelReader;
import com.upload.service.FileService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class FileUploadController {
	@Autowired
	FileService fileservice;

	@Autowired
	ExcelReader excelReader;

	@PostMapping("/uploadfile")
	public ResponseEntity<List<StockPriceEntity>> handleFileUpload(@RequestParam("file") MultipartFile file)
			throws IOException {
		return ResponseEntity.status(HttpStatus.OK).body(excelReader.readExcel(file));

	}

}
