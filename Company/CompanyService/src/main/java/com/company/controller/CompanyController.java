package com.company.controller;

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

import com.company.entity.CompanyEntity;
import com.company.entity.StockExchangeEntity;
import com.company.exception.CompanyAlreadyExistsException;
import com.company.service.impl.CompanyServiceImpl;

@RestController
@CrossOrigin
public class CompanyController {

	@Autowired
	private CompanyServiceImpl companyService;

	@GetMapping("/companies/{id}")
	public ResponseEntity<CompanyEntity> getCompanyById(@PathVariable("id") Long companyId) {
		CompanyEntity companyById = companyService.getCompanyById(companyId);
		return ResponseEntity.ok().body(companyById);

	}

	@PostMapping("/admin/companies/add")
	public ResponseEntity<Object> addCompany(@RequestBody CompanyEntity company) throws CompanyAlreadyExistsException {
		return ResponseEntity.status(HttpStatus.CREATED).body(companyService.createCompany(company));
	}

	@DeleteMapping("/admin/companies/{id}")
	public ResponseEntity<String> deactivateCompany(@PathVariable("id") Long companyId) {
		companyService.deactivateCompanyById(companyId);
		return ResponseEntity.ok().body("Company with Id : " + companyId + " succesfully Deactivated");
	}

	@GetMapping("/companies")
	public ResponseEntity<List<CompanyEntity>> getAllCompanies() {
		return ResponseEntity.ok(companyService.getAllCompanies());
	}

	@GetMapping("/companies/length")
	public ResponseEntity<Long> getNumberOfCompanies() {
		return ResponseEntity.ok(companyService.getNumberOfCompanies());
	}

	@GetMapping("/companies/{id}/stock-exchanges")
	public ResponseEntity<List<StockExchangeEntity>> getAllStockExchangesByCompanyId(
			@PathVariable("id") Long companyId) {
		return ResponseEntity.ok(companyService.getStockExchangesByCompanyId(companyId));
	}

}
