package com.company.service.impl;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.dao.CompanyRepository;
import com.company.dao.DirectorRepository;
import com.company.entity.CompanyEntity;
import com.company.entity.Director;
import com.company.entity.StockExchangeEntity;
import com.company.exception.CompanyAlreadyExistsException;
import com.company.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	DirectorRepository directorRepository;

	@Override
	public CompanyEntity createCompany(CompanyEntity company) throws CompanyAlreadyExistsException {
		CompanyEntity existingCompany = companyRepository.findBycompanyName(company.getCompanyName());
		if (existingCompany != null) {
			throw new CompanyAlreadyExistsException();
		}
		CompanyEntity savedCompany = companyRepository.save(company);
		List<Director> directors = company.getDirectors();
		for (Director d : directors) {
			directorRepository.save(d);
		}
		return savedCompany;
	}

	@Override
	public CompanyEntity getCompanyById(Long companyId) {
		Optional<CompanyEntity> company = companyRepository.findById(companyId);
		if (!company.isPresent()) {
			throw new ObjectNotFoundException(companyId, "Company");
		}
		return company.get();
	}

	@Override
	public void deactivateCompanyById(Long companyId) {
		Optional<CompanyEntity> company = companyRepository.findById(companyId);
		if (!company.isPresent()) {
			throw new ObjectNotFoundException(companyId, "Company");
		}
		CompanyEntity companyEntity = company.get();
		companyEntity.setActive(false);

	}

	@Override
	public List<CompanyEntity> getAllCompanies() {
		return companyRepository.findAll();

	}

	@Override
	public long getNumberOfCompanies() {
		return companyRepository.count();
	}

	@Override
	public List<StockExchangeEntity> getStockExchangesByCompanyId(Long CompanyId) {
		CompanyEntity company = companyRepository.getById(CompanyId);
		return company.getStockExchanges();
	}

}
