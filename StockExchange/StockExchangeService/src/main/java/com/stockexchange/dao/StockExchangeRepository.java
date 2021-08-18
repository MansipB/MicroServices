package com.stockexchange.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stockexchange.entity.StockExchangeEntity;

@Repository
public interface StockExchangeRepository extends JpaRepository<StockExchangeEntity, Long> {
	public StockExchangeEntity findBystockExchangeName(String stockExchangeName);

}
