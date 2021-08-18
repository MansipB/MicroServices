package com.ipo.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ipo.dao.IpoRepository;
import com.ipo.entity.CompanyEntity;
import com.ipo.entity.IpoDetailEntity;
import com.ipo.exception.IpoAlreadyExistsException;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class IpoServiceImplTests {

	@InjectMocks
	IpoServiceImpl ipoService;

	@Mock
	IpoRepository ipoRepository;

	private CompanyEntity mockCompany;
	private IpoDetailEntity mockIpo;
	private List<IpoDetailEntity> mockIpos;

	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getAllIposTest() {
		CompanyEntity mockCompany = new CompanyEntity(1L, "Zomato Ltd", true);
		IpoDetailEntity mockIpo = new IpoDetailEntity(1L, mockCompany, null, null, null, null);
		List<IpoDetailEntity> mockIpos = new ArrayList<IpoDetailEntity>();
		mockIpos.add(mockIpo);
		Mockito.when(ipoRepository.findAll()).thenReturn(mockIpos);
		List<IpoDetailEntity> allIpos = ipoService.getAllIpos();
		assertEquals(allIpos, mockIpos);
		verify(ipoRepository, times(1)).findAll();
	}

	@Test
	public void addIpoTest() throws IpoAlreadyExistsException {
		IpoDetailEntity mockIpo = new IpoDetailEntity(1L, mockCompany, null, null, null, null);
		Mockito.when(ipoRepository.findById(1L)).thenReturn(Optional.empty());
		Mockito.when(ipoRepository.save(mockIpo)).thenReturn(mockIpo);
		assertTrue(ipoService.addIpo(mockIpo).getId() > 0);
		verify(ipoRepository, times(1)).findById(1L);
		verify(ipoRepository).save(mockIpo);
	}

	@Test
	public void ShouldThrowErrorWhenAddIpoTest() throws IpoAlreadyExistsException {
		IpoDetailEntity mockIpo = new IpoDetailEntity(1L, mockCompany, null, null, null, null);
		Mockito.when(ipoRepository.findById(1L)).thenReturn(Optional.of(mockIpo));
		assertThrows(IpoAlreadyExistsException.class, () -> {
			ipoService.addIpo(mockIpo);
		});
		verify(ipoRepository, times(1)).findById(1L);
		verify(ipoRepository, never()).save(mockIpo);
	}

	@Test
	public void getAllUpcomingIposTest() throws ParseException {
		CompanyEntity mockCompany = new CompanyEntity(1L, "Zomato Ltd", true);
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		Date d1 = df.parse("12-10-2011");
		Date d3 = df.parse("01-02-2011");
		IpoDetailEntity mockIpo = new IpoDetailEntity(1L, mockCompany, null, null, d1, null);
		List<IpoDetailEntity> expectedMockIpos = new ArrayList<IpoDetailEntity>();
		expectedMockIpos.add(mockIpo);
		Mockito.when(ipoRepository.findByIssueOpenDateGreaterThanOrderByIssueOpenDateAsc(new Date()))
				.thenReturn(expectedMockIpos);
		List<IpoDetailEntity> allUpcomingIpos = ipoService.getAllUpcomingIpos();
		assertEquals(expectedMockIpos, allUpcomingIpos);

	}

}
