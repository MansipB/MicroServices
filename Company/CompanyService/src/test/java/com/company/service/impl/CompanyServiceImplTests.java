package com.company.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import com.company.dao.CompanyRepository;
import com.company.entity.CompanyEntity;
import com.company.entity.Director;
import com.company.exception.CompanyAlreadyExistsException;

@RunWith(SpringRunner.class)
public class CompanyServiceImplTests {
	@InjectMocks
	CompanyServiceImpl companyService;

	@Mock
	CompanyRepository companyRepository;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void createCompanyTest() throws CompanyAlreadyExistsException {
		CompanyEntity mockCompany = mock(CompanyEntity.class);
		Director director = mock(Director.class);
		mockCompany.setCompanyName("SocGen");
		mockCompany.setDirectors(null);
		String companyName = mockCompany.getCompanyName();
		Mockito.when(companyRepository.findBycompanyName(companyName)).thenReturn(null);
		Mockito.when(companyRepository.save(mockCompany)).thenReturn(mockCompany);
		assertThat(companyService.createCompany(mockCompany)).isNotNull();
		verify(companyRepository).findBycompanyName(companyName);
		verify(companyRepository).save(mockCompany);
	}

	@Test
	public void ShouldThrowErrorWhenCreateCompanyTest() {
		CompanyEntity mockCompany = mock(CompanyEntity.class);
		mockCompany.setCompanyName("SocGen");
		String companyName = mockCompany.getCompanyName();
		Mockito.when(companyRepository.findBycompanyName(companyName)).thenReturn(mockCompany);
		assertThrows(CompanyAlreadyExistsException.class, () -> companyService.createCompany(mockCompany));
		verify(companyRepository).findBycompanyName(companyName);
		verify(companyRepository, never()).save(mockCompany);
	}

	@Test
	public void getCompanyByIdTest() {
		CompanyEntity mockCompany = mock(CompanyEntity.class);
		Mockito.when(companyRepository.findById(1L)).thenReturn(Optional.of(mockCompany));
		assertThat(companyService.getCompanyById(1L)).isNotNull();
		verify(companyRepository).findById(1L);
	}

	@Test
	public void ShouldThrowErrorWhenGetCompanyByIdTest() {
		Mockito.when(companyRepository.findById(1L)).thenReturn(Optional.empty());
		assertThrows(ObjectNotFoundException.class, () -> companyService.getCompanyById(1L));
		verify(companyRepository).findById(1L);
	}

	@Test
	public void deactivateCompanyByIdTest() {
		CompanyEntity mockCompany = mock(CompanyEntity.class);
		mockCompany.setActive(true);
		Mockito.when(companyRepository.findById(1L)).thenReturn(Optional.of(mockCompany));
		companyService.deactivateCompanyById(1L);
		assertThat(mockCompany.getActive()).isFalse();
		verify(companyRepository).findById(1L);
	}

	@Test
	public void ShouldThrowErrorWhenDeactivateCompanyByIdTest() {
		Mockito.when(companyRepository.findById(1L)).thenReturn(Optional.empty());
		companyService.deactivateCompanyById(1L);
		assertThrows(ObjectNotFoundException.class, () -> companyService.getCompanyById(1L));
		verify(companyRepository).findById(1L);
	}

}
