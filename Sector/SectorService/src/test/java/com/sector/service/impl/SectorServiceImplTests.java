package com.sector.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import com.sector.dao.SectorRepository;
import com.sector.entity.SectorEntity;
import com.sector.exception.SectorAlreadyExistsException;

@ExtendWith(MockitoExtension.class)
class SectorServiceImplTests {

	@InjectMocks
	SectorServiceImpl sectorService;

	@Mock
	SectorRepository sectorRepository;

	@BeforeEach
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
	}

	@Test
	void createSector() throws SectorAlreadyExistsException {
		SectorEntity sector = new SectorEntity();
		sector.setSectorName("finance");
		Mockito.when(sectorRepository.findBysectorName(sector.getSectorName())).thenReturn(null);
		sectorService.createsector(sector);
		verify(sectorRepository).save(sector);
		verify(sectorRepository).findBysectorName(sector.getSectorName());
	}

	@Test
	void ShouldThrowErrorWhencreateSector() throws SectorAlreadyExistsException {
		SectorEntity sector = new SectorEntity();
		sector.setSectorName("finance");
		Mockito.when(sectorRepository.findBysectorName(sector.getSectorName())).thenReturn(sector);
		assertThrows(SectorAlreadyExistsException.class, () -> {
			sectorService.createsector(sector);
		});
		verify(sectorRepository, never()).save(sector);
	}

	@Test
	void getAllSectorsTest() {
		SectorEntity sector1 = mock(SectorEntity.class);
		SectorEntity sector2 = mock(SectorEntity.class);
		List<SectorEntity> sectors = new ArrayList<>();
		sectors.add(sector1);
		sectors.add(sector2);
		Mockito.when(sectorRepository.findAll(Sort.by(Sort.Direction.ASC, "sectorName"))).thenReturn(sectors);
		assertEquals(2, sectorService.getAllSectors().size());
		verify(sectorRepository).findAll(Sort.by(Sort.Direction.ASC, "sectorName"));
	}

	@Test
	void deleteSectorTest() {
		SectorEntity sector = mock(SectorEntity.class);
		Mockito.when(sectorRepository.findById(1L)).thenReturn(Optional.of(sector));
		sectorService.deleteSector(1L);
		verify(sectorRepository).findById(1L);
		verify(sectorRepository).deleteById(1L);
	}

	@Test
	void ShouldThrowErrorWhenDeleteSectorTest() {
		Mockito.when(sectorRepository.findById(1L)).thenReturn(Optional.empty());
		assertThrows(ObjectNotFoundException.class, () -> {
			sectorService.deleteSector(1L);
		});
		verify(sectorRepository, never()).deleteById(1L);
	}

}
