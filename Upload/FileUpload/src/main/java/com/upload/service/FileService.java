package com.upload.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
	private final Path rootLocation = Paths.get("StockPriceFiles");

	public void store(MultipartFile file) throws IOException {
		Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
	}

}
