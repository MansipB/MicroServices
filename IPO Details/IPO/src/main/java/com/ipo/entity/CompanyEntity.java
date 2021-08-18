package com.ipo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "company")
public class CompanyEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "cname", nullable = false)
	private String companyName;

	private Boolean active = true;

	@OneToOne(mappedBy = "company")
	@JsonIgnore
	private IpoDetailEntity ipo;

	@ManyToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinTable(name = "companyStockDetails", joinColumns = @JoinColumn(name = "companyId"), inverseJoinColumns = @JoinColumn(name = "stockExchangeId"))
	private List<StockExchangeEntity> stockExchanges;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public IpoDetailEntity getIpo() {
		return ipo;
	}

	public void setIpo(IpoDetailEntity ipo) {
		this.ipo = ipo;
	}

	public List<StockExchangeEntity> getStockExchanges() {
		return stockExchanges;
	}

	public void setStockExchanges(List<StockExchangeEntity> stockExchanges) {
		this.stockExchanges = stockExchanges;
	}

	public CompanyEntity() {
		super();
	}

	public CompanyEntity(long id, String companyName, Boolean active) {
		super();
		this.id = id;
		this.companyName = companyName;
		this.active = active;

	}

}
