package com.ipo.service;

import java.util.List;

import com.ipo.entity.IpoDetailEntity;
import com.ipo.exception.IpoAlreadyExistsException;

public interface IpoService {

	IpoDetailEntity addIpo(IpoDetailEntity ipo) throws IpoAlreadyExistsException;

	List<IpoDetailEntity> getAllIpos();

	List<IpoDetailEntity> getAllUpcomingIpos();
}
