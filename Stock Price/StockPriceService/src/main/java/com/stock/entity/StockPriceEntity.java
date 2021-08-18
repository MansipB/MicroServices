package com.stock.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "StockPrice")
public class StockPriceEntity {

	@Id
	@GeneratedValue
	private long id;

	@OneToOne(fetch = FetchType.EAGER, targetEntity = CompanyEntity.class, cascade = CascadeType.MERGE)
	@JoinColumn(name = "companyId")
	private CompanyEntity company;

	@OneToOne(fetch = FetchType.EAGER, targetEntity = StockExchangeEntity.class)
	@JoinColumn(name = "stockExchangeId")
	private StockExchangeEntity stockExchange;

	@JsonFormat(pattern = "dd-MM-yyyy")
	@Column(nullable = false)
	private Date date;

	@Column(nullable = false)
	private double sharePrice;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getSharePrice() {
		return sharePrice;
	}

	public void setSharePrice(double d) {
		this.sharePrice = d;
	}

	public StockPriceEntity() {
		super();
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

}
