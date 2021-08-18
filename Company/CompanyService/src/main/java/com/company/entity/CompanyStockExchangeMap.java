package com.company.entity;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "CompanyStockDetails")
public class CompanyStockExchangeMap {

	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false)
	private String companyCode;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "companyId")
	private CompanyEntity company;

	@ManyToOne(fetch = FetchType.LAZY)
	private StockExchangeEntity stockExchange;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public CompanyEntity getCompany() {
		return company;
	}

	public void setCompany(CompanyEntity company) {
		this.company = company;
	}

	public StockExchangeEntity getStockExchange() {
		return stockExchange;
	}

	public void setStockExchange(StockExchangeEntity stockExchange) {
		this.stockExchange = stockExchange;
	}

	public CompanyStockExchangeMap() {
		super();
	}

}
