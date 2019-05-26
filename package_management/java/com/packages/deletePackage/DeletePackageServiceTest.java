package com.packages.deletePackage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.packages.exceptions.UnknownMatchException;
import com.packages.model.JwtUser;
import com.packages.model.PackagesSenderHistory;
import com.packages.repositories.CommandsHistoryRepository;
class DeletePackageServiceTest {
	@Mock
	CommandsHistoryRepository cmdRepo;
	@InjectMocks
	DeletePackageService deletePackageService;
	JwtUser jt ;
	PackagesSenderHistory packagesSenderHistory;
	@BeforeEach
	void setUp() throws Exception {
		JwtUser.setUserName("user");
		packagesSenderHistory = new PackagesSenderHistory();
		packagesSenderHistory.setId(1);
		packagesSenderHistory.setEmailSender("user");
		packagesSenderHistory.setSenderName("user");
		packagesSenderHistory.setStatus("Ready");
		MockitoAnnotations.initMocks(this);
	}

	@Test
	@DisplayName("Exist by id test")
	void existsByIdTest() {
		when(cmdRepo.existsById(anyInt())).thenReturn(false);
		assertThrows(UnknownMatchException.class,()->deletePackageService.deletePackage(1));
	}
	@Test
	@DisplayName("Exist get email test")
	void getEmailTest() {
		PackagesSenderHistory packagesSenderFailedHistory =packagesSenderHistory;
		packagesSenderFailedHistory.setEmailSender("failuser");
		Optional<PackagesSenderHistory> senderOptionalProfile;
		senderOptionalProfile = Optional.of(packagesSenderFailedHistory);
		when(cmdRepo.existsById(anyInt())).thenReturn(true);
		when(cmdRepo.findById(anyInt())).thenReturn(senderOptionalProfile);
		assertThrows(UnknownMatchException.class,()->deletePackageService.deletePackage(1));
	}
	@Test
	@DisplayName("Exist get status test")
	void getStatusTest() {
		PackagesSenderHistory packagesNotReadyHistory =packagesSenderHistory;
		packagesNotReadyHistory.setStatus("NotReady");
		Optional<PackagesSenderHistory> senderOptionalProfile;
		senderOptionalProfile = Optional.of(packagesNotReadyHistory);
		when(cmdRepo.existsById(anyInt())).thenReturn(true);
		when(cmdRepo.findById(anyInt())).thenReturn(senderOptionalProfile);
		assertThrows(UnknownMatchException.class,()->deletePackageService.deletePackage(1));
	}
	@Test
	@DisplayName("Deleting the package test")
	void deteleThePackageTest() throws JSONException {
		Optional<PackagesSenderHistory> senderOptionalProfile;
		senderOptionalProfile = Optional.of(packagesSenderHistory);
		when(cmdRepo.existsById(anyInt())).thenReturn(true);
		when(cmdRepo.findById(anyInt())).thenReturn(senderOptionalProfile);
		JSONObject res = new JSONObject();
		res.put("message", "Success");
		assertEquals(res.toString(),deletePackageService.deletePackage(1));
	}
}
