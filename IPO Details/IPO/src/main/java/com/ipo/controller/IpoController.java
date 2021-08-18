package com.ipo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ipo.entity.IpoDetailEntity;
import com.ipo.exception.IpoAlreadyExistsException;
import com.ipo.service.impl.IpoServiceImpl;

@RestController
@RequestMapping("/ipo")
public class IpoController {

	@Autowired
	IpoServiceImpl ipoService;

	@Autowired
	RestTemplate restTemplate;

	@PostMapping("/add")
	public ResponseEntity<IpoDetailEntity> addIpo(@RequestBody IpoDetailEntity ipo) throws IpoAlreadyExistsException {
		return ResponseEntity.ok(ipoService.addIpo(ipo));
	}

	@GetMapping("/")
	public ResponseEntity<List<IpoDetailEntity>> getAllIpos() {
		return ResponseEntity.ok(ipoService.getAllIpos());
	}

	@GetMapping("/upcoming")
	public ResponseEntity<List<IpoDetailEntity>> getAllUpcomingIpos() {
		return ResponseEntity.ok(ipoService.getAllUpcomingIpos());
	}
}
