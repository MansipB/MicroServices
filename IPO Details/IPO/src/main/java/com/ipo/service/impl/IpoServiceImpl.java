package com.ipo.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipo.dao.IpoRepository;
import com.ipo.entity.IpoDetailEntity;
import com.ipo.exception.IpoAlreadyExistsException;
import com.ipo.service.IpoService;

@Service
public class IpoServiceImpl implements IpoService {

	@Autowired
	IpoRepository ipoRepository;

	@Override
	public IpoDetailEntity addIpo(IpoDetailEntity ipo) throws IpoAlreadyExistsException {
		Optional<IpoDetailEntity> existingIpo = ipoRepository.findById(ipo.getId());
		if (existingIpo.isPresent()) {
			throw new IpoAlreadyExistsException();
		}
		return ipoRepository.save(ipo);
	}

	@Override
	public List<IpoDetailEntity> getAllIpos() {
		return ipoRepository.findAll();
	}

	@Override
	public List<IpoDetailEntity> getAllUpcomingIpos() {
		return ipoRepository.findByIssueOpenDateGreaterThanOrderByIssueOpenDateAsc(new Date());
	}

}
