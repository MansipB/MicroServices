package com.ipo.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ipo.entity.IpoDetailEntity;

public interface IpoRepository extends JpaRepository<IpoDetailEntity, Long> {

	public List<IpoDetailEntity> findByIssueOpenDateGreaterThanOrderByIssueOpenDateAsc(Date date);
}
