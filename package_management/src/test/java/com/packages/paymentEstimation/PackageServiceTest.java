package com.packages.paymentEstimation;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.packages.exceptions.UnknownMatchException;
import com.packages.repositories.CommandsHistoryRepository;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
class PackageServiceTest {
	@InjectMocks
	PackageService packageService;
	@Mock
	CommandsHistoryRepository commandsHistoryRepository;
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void existByIdTest() {
		when(commandsHistoryRepository.existsById(anyInt())).thenReturn(false);
		assertThrows(UnknownMatchException.class,()->packageService.EstimatePrice(1, "Whatever"));
	}

}
