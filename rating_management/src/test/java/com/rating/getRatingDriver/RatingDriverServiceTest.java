package com.rating.getRatingDriver;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.rating.exceptions.UnknownMatchException;
import com.rating.model.RatingForm;
import com.rating.repositories.RatingRepository;

import net.minidev.json.JSONObject;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
class RatingDriverServiceTest {

	@Mock
	RatingRepository ratingRepo;
	@InjectMocks
	RatingDriverService ratingDriverService;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	@DisplayName("Testing exists by email")
	void getRatingTest() {
		when(ratingRepo.existsByEmailDriver(anyString())).thenReturn(false);
		assertThrows(UnknownMatchException.class, () -> ratingDriverService.getRating("altceva"));
	}
	
	@Test
	@DisplayName("Testing return the rating of 1")
	void getTheRatingRetunOf1Test() {
		List<RatingForm> listRatingForm= new ArrayList<>();;
		RatingForm ratingForm = new RatingForm();
		ratingForm.setRating(1);
		ratingForm.setEmailDriver("user");
		listRatingForm.add(ratingForm);
		when(ratingRepo.existsByEmailDriver(anyString())).thenReturn(true);
		when(ratingRepo.findAllByEmailDriver(anyString())).thenReturn(listRatingForm);
		when(ratingRepo.countByEmailDriver(anyString())).thenReturn(1L);
		JSONObject json = new JSONObject();
		json.put("rating", 1.0);
		assertEquals(json.toString(),ratingDriverService.getRating("user"));
	}
	@Test
	@DisplayName("Testing return the rating of 2")
	void getTheRatingRetunOf2Test() {
		List<RatingForm> listRatingForm= new ArrayList<>();;
		RatingForm ratingForm = new RatingForm();
		ratingForm.setRating(2);
		ratingForm.setEmailDriver("user");
		listRatingForm.add(ratingForm);
		when(ratingRepo.existsByEmailDriver(anyString())).thenReturn(true);
		when(ratingRepo.findAllByEmailDriver(anyString())).thenReturn(listRatingForm);
		when(ratingRepo.countByEmailDriver(anyString())).thenReturn(1L);
		JSONObject json = new JSONObject();
		json.put("rating", 2.0);
		assertEquals(json.toString(),ratingDriverService.getRating("user"));
	}
	@Test
	@DisplayName("Testing return the rating of 0")
	void getTheRatingRetunOf0Test() {
		List<RatingForm> listRatingForm= new ArrayList<>();;
		RatingForm ratingForm = new RatingForm();
		ratingForm.setRating(0);
		ratingForm.setEmailDriver("user");
		listRatingForm.add(ratingForm);
		when(ratingRepo.existsByEmailDriver(anyString())).thenReturn(true);
		when(ratingRepo.findAllByEmailDriver(anyString())).thenReturn(listRatingForm);
		when(ratingRepo.countByEmailDriver(anyString())).thenReturn(1L);
		JSONObject json = new JSONObject();
		json.put("rating", 0.0);
		assertEquals(json.toString(),ratingDriverService.getRating("user"));
	}
}
