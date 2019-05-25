package com.rating.setRating;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.rating.exceptions.UnknownMatchException;
import com.rating.model.JwtUser;
import com.rating.model.PackageSender;
import com.rating.model.RatingForm;
import com.rating.repositories.RatingRepository;

import io.restassured.RestAssured;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyInt;

class SetRatingServiceTest {

	@Mock
	RatingRepository ratingRepo;
	@InjectMocks
	SetRatingService setRatingService;
	RatingForm ratingForm;

	@BeforeEach
	void setUp() throws Exception {
		ratingForm = new RatingForm();
		ratingForm.setEmailDriver("user");
		ratingForm.setIdPackage(1);
		ratingForm.setRating(1);
		JwtUser.setUserName("Utilizator");
		MockitoAnnotations.initMocks(this);
	}

	@Test
	@DisplayName("Rating Form Test")
	void RatingFormTest() {
		RatingForm ratingNullEmailForm=ratingForm;
		ratingNullEmailForm.setEmailDriver(null);
		RatingForm ratingNullIdPackageForm = ratingForm;
		ratingNullIdPackageForm.setId(0);
		RatingForm ratingNullRatingForm = ratingForm;
		ratingNullRatingForm.setRating(0);
		assertAll(
		() -> assertThrows(UnknownMatchException.class,()->setRatingService.setRating(ratingNullEmailForm,"Whatever")),
		() -> assertThrows(UnknownMatchException.class,()->setRatingService.setRating(ratingNullIdPackageForm,"Whatever")),
		() -> assertThrows(UnknownMatchException.class,()->setRatingService.setRating(ratingNullRatingForm,"Whatever"))
		);
	}
	@Test
	@DisplayName("Exist Id Package Test")
	void ExistIdPackageTest() {

		when(ratingRepo.existsByIdPackage(anyInt())).thenReturn(false);
		assertThrows(UnknownMatchException.class,() -> setRatingService.setRating(ratingForm, "Whatever"));
		
	}
	@Test
	@DisplayName("Package Sender Test")
	void PackageSenderTest() {
		PackageSender packageSender = new PackageSender();
		packageSender.setEmailSender("Utilizator");
		packageSender.setStatus("Delivered");
		assertAll(
		()-> assertEquals(packageSender.getEmailSender(),JwtUser.getUserName()),
		()-> assertEquals(packageSender.getStatus(),"Delivered")
		);
	}
}
