package com.company.service;

import java.util.List;

import com.company.entity.CompanyEntity;
import com.company.entity.StockExchangeEntity;
import com.company.exception.CompanyAlreadyExistsException;

public interface CompanyService {

	public CompanyEntity createCompany(CompanyEntity company) throws CompanyAlreadyExistsException;

	public CompanyEntity getCompanyById(Long companyId);

	public List<CompanyEntity> getAllCompanies();

	void deactivateCompanyById(Long companyId);

	long getNumberOfCompanies();

	List<StockExchangeEntity> getStockExchangesByCompanyId(Long CompanyId);
}
