package com.ipo.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "Ipo_details")
public class IpoDetailEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private CompanyEntity company;
	private Double openingPricePerShare;
	private Long totalNumberOfShares;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date issueOpenDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date issueCloseDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public CompanyEntity getCompany() {
		return company;
	}

	public void setCompany(CompanyEntity company) {
		this.company = company;
	}

	public Double getOpeningPricePerShare() {
		return openingPricePerShare;
	}

	public void setOpeningPricePerShare(Double openingPricePerShare) {
		this.openingPricePerShare = openingPricePerShare;
	}

	public Long getTotalNumberOfShares() {
		return totalNumberOfShares;
	}

	public void setTotalNumberOfShares(Long totalNumberOfShares) {
		this.totalNumberOfShares = totalNumberOfShares;
	}

	public Date getIssueOpenDate() {
		return issueOpenDate;
	}

	public void setIssueOpenDate(Date issueOpenDate) {
		this.issueOpenDate = issueOpenDate;
	}

	public Date getIssueCloseDate() {
		return issueCloseDate;
	}

	public void setIssueCloseDate(Date issueCloseDate) {
		this.issueCloseDate = issueCloseDate;
	}

	public IpoDetailEntity() {
		super();
	}

	public IpoDetailEntity(long id, CompanyEntity company, Double openingPricePerShare, Long totalNumberOfShares,
			Date issueOpenDate, Date issueCloseDate) {
		super();
		this.id = id;
		this.company = company;
		this.openingPricePerShare = openingPricePerShare;
		this.totalNumberOfShares = totalNumberOfShares;
		this.issueOpenDate = issueOpenDate;
		this.issueCloseDate = issueCloseDate;
	}

}
