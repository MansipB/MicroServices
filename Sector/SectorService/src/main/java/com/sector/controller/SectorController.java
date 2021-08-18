package com.sector.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sector.entity.SectorEntity;
import com.sector.service.impl.SectorServiceImpl;

@RestController
@CrossOrigin
public class SectorController {

	@Autowired
	private SectorServiceImpl sectorService;

	@PostMapping("/sectors/add")
	public ResponseEntity<Object> addSector(@RequestBody SectorEntity sector) {
		try {
			SectorEntity createdSector = sectorService.createsector(sector);
			return ResponseEntity.status(HttpStatus.CREATED).body(createdSector.getId());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@GetMapping("/sectors")
	public ResponseEntity<List<SectorEntity>> getAllSectors() {
		List<SectorEntity> allSectors = sectorService.getAllSectors();
		if (allSectors.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(allSectors);
	}

	@DeleteMapping("/sectors/{id}")
	public ResponseEntity<String> deleteSector(@PathVariable("id") Long sectorId) {
		sectorService.deleteSector(sectorId);
		return ResponseEntity.ok().body("Sector with Id : " + sectorId + " succesfully Deleted");
	}
}
