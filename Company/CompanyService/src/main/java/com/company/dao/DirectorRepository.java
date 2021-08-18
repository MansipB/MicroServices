package com.company.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.entity.Director;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {

}
