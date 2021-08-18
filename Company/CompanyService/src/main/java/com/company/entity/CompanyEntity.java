package com.company.entity;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "company")
public class CompanyEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "cname", nullable = false)
	private String companyName;

	@Column(nullable = false)
	private String ceo;
	private Boolean active = true;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "companyId")
	private List<Director> directors;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private SectorEntity sector;

	@Column(length = 2000)
	private String description;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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

	public String getCeo() {
		return ceo;
	}

	public void setCeo(String ceo) {
		this.ceo = ceo;
	}

	public List<Director> getDirectors() {
		return directors;
	}

	public void setDirectors(List<Director> directors) {
		this.directors = directors;
	}

	public SectorEntity getSector() {
		return sector;
	}

	public void setSector(SectorEntity sector) {
		this.sector = sector;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
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

}
