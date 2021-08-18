package com.sector.service;

import java.util.List;

import com.sector.entity.SectorEntity;
import com.sector.exception.SectorAlreadyExistsException;

public interface SectorService {
	public SectorEntity createsector(SectorEntity sector) throws SectorAlreadyExistsException;

	public List<SectorEntity> getAllSectors();

	public void deleteSector(Long sectorId);

}
