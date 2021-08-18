package com.stockexchange.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;

import com.stockexchange.dao.StockExchangeRepository;
import com.stockexchange.entity.StockExchangeEntity;
import com.stockexchange.exception.StockExchangeAlreadyExistsException;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class StockExchangeServiceImplTests {

	@InjectMocks
	StockExchangeServiceImpl stockExchangeService;

	@Mock
	StockExchangeRepository stockExchangeRepository;

	@Test
	public void getAllStockExchangesTest() {
		StockExchangeEntity mockStockExchange1 = mock(StockExchangeEntity.class);
		StockExchangeEntity mockStockExchange2 = mock(StockExchangeEntity.class);
		List<StockExchangeEntity> stockExchanges = new ArrayList<>();
		stockExchanges.add(mockStockExchange2);
		stockExchanges.add(mockStockExchange1);
		Mockito.when(stockExchangeRepository.findAll()).thenReturn(stockExchanges);
		assertEquals(2, stockExchangeService.getAllStockExchange().size());
		verify(stockExchangeRepository).findAll();
	}

	@Test
	public void getNumberOfStockExchangeTest() {
		Mockito.when(stockExchangeRepository.count()).thenReturn(2L);
		assertEquals(2, stockExchangeService.getNumberOfStockExchange());
		verify(stockExchangeRepository).count();
	}

	@Test
	public void createStockExchangeTest() throws StockExchangeAlreadyExistsException {
		StockExchangeEntity mockStockExchange1 = mock(StockExchangeEntity.class);
		mockStockExchange1.setStockExchangeName("NSE");
		Mockito.when(stockExchangeRepository.findBystockExchangeName(mockStockExchange1.getStockExchangeName()))
				.thenReturn(null);
		Mockito.when(stockExchangeRepository.save(mockStockExchange1)).thenReturn(mockStockExchange1);
		assertThat(stockExchangeService.createStockExchange(mockStockExchange1)).isNotNull();
		verify(stockExchangeRepository).findBystockExchangeName(mockStockExchange1.getStockExchangeName());
		verify(stockExchangeRepository).save(mockStockExchange1);
	}

	@Test
	public void ShouldThrowErrorWhenCreateStockExchangeTest() throws StockExchangeAlreadyExistsException {
		StockExchangeEntity mockStockExchange1 = mock(StockExchangeEntity.class);
		mockStockExchange1.setStockExchangeName("NSE");
		Mockito.when(stockExchangeRepository.findBystockExchangeName(mockStockExchange1.getStockExchangeName()))
				.thenReturn(mockStockExchange1);
		assertThrows(StockExchangeAlreadyExistsException.class,
				() -> stockExchangeService.createStockExchange(mockStockExchange1));
		verify(stockExchangeRepository).findBystockExchangeName(mockStockExchange1.getStockExchangeName());
		verify(stockExchangeRepository, never()).save(mockStockExchange1);
	}

	@Test
	public void getStockExchangeByIdTest() {
		StockExchangeEntity mockStockExchange1 = mock(StockExchangeEntity.class);
		Mockito.when(stockExchangeRepository.findById(1L)).thenReturn(Optional.of(mockStockExchange1));
		assertThat(stockExchangeService.getStockExchangeById(1L)).isNotNull();
		verify(stockExchangeRepository).findById(1L);
	}

	@Test
	public void ShouldThrowErrorWhenGetStockExchangeByIdTest() {
		Mockito.when(stockExchangeRepository.findById(1L)).thenReturn(Optional.empty());
		assertThrows(ObjectNotFoundException.class, () -> stockExchangeService.getStockExchangeById(1L));
		verify(stockExchangeRepository).findById(1L);
	}

	@Test
	public void getStockExchangeByNameTest() {
		StockExchangeEntity mockStockExchange1 = mock(StockExchangeEntity.class);
		mockStockExchange1.setStockExchangeName("NSE");
		Mockito.when(stockExchangeRepository.findBystockExchangeName(mockStockExchange1.getStockExchangeName()))
				.thenReturn(mockStockExchange1);
		assertThat(stockExchangeService.getStockExchangeByName(mockStockExchange1.getStockExchangeName())).isNotNull();
		verify(stockExchangeRepository).findBystockExchangeName(mockStockExchange1.getStockExchangeName());
	}

	@Test
	public void ShouldThrowErrorWhenGetStockExchangeByNameTest() {
		StockExchangeEntity mockStockExchange1 = mock(StockExchangeEntity.class);
		mockStockExchange1.setStockExchangeName("NSE");
		Mockito.when(stockExchangeRepository.findBystockExchangeName(mockStockExchange1.getStockExchangeName()))
				.thenReturn(null);
		String stockExchangeName = mockStockExchange1.getStockExchangeName();
		assertThrows(ObjectNotFoundException.class,
				() -> stockExchangeService.getStockExchangeByName(stockExchangeName));
		verify(stockExchangeRepository).findBystockExchangeName(mockStockExchange1.getStockExchangeName());
	}

}
