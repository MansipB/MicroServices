package com.upload.entity;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class StockPriceEntity {

	private String stockExchangeName;
	private Long companyId;
	private Date date;
	private String time;
	private double sharePrice;

	public String getStockExchangeName() {
		return stockExchangeName;
	}

	public void setStockExchangeName(String stockExchangeName) {
		this.stockExchangeName = stockExchangeName;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String string) {
		this.time = string;
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

}
