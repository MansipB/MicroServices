package com.stock.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.stock.entity.StockPriceEntity;

@RestController
@CrossOrigin
public interface StockPriceRepository extends JpaRepository<StockPriceEntity, Long> {

	public List<StockPriceEntity> findByCompanyIdAndStockExchangeIdAndDateBetween(Long companyId, Long stockExchangeId,
			Date from, Date to, Sort sort);

	@Query(value = "SELECT  p.id , date, share_price,company_id,stock_exchange_id FROM stock_price as p INNER JOIN company as c on p.company_id=c.id where c.id = :companyId and p.stock_exchange_id = :stockExchangeId and p.date like %:format% Order By p.date", nativeQuery = true)
	public List<StockPriceEntity> findByCompanyIdAndDateContaining(@Param("companyId") Long companyId,
			@Param("stockExchangeId") Long stockExchangeId, @Param("format") String format);

	@Query("SELECT p FROM StockPriceEntity p INNER JOIN p.company c INNER JOIN c.sector s WHERE s.id = :id ")
	public List<StockPriceEntity> findBySectorId(@Param("id") Long id);

	public boolean existsByDate(Date date);
}
