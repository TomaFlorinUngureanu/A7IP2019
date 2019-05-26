package com.packages.modifyPackageInformations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

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

class ModifyPackageInformationsServiceTest {
	
	@InjectMocks
	ModifyPackageInformationsService modifyPIS;
	@Mock
	CommandsHistoryRepository cmdHistRepo;
	PackagesSenderHistory packageSH = new PackagesSenderHistory();
	@BeforeEach
	void setUp() throws Exception {
		JwtUser.setUserName("email");
		packageSH.setId(1);
		packageSH.setEmailDriver("emailDriver");
		packageSH.setEmailSender("emailSender");
		packageSH.setHeight(10);
		packageSH.setKilograms(20);
		packageSH.setLength(30);
		packageSH.setNamePackage("Package");
		packageSH.setPhoneNumberReceiver("0800800800");
		packageSH.setPhoneNumberSender("0900900900");
		packageSH.setPin(2);
		packageSH.setReceiverAdress("adresaReceiver");
		packageSH.setReceiverName("Auras");
		packageSH.setSenderAdress("adresaSender");
		packageSH.setSenderName("Stegarul");
		packageSH.setStatus("Activ");
		packageSH.setWidth(40);
		MockitoAnnotations.initMocks(this);
	}

	@Test
	@DisplayName("Get id test")
	void getIDtest() {
		PackagesSenderHistory nullPackageId = packageSH;
		nullPackageId.setId(null);
		assertThrows(UnknownMatchException.class,()->modifyPIS.changePackage(nullPackageId,"Whatever"));
	}
	@Test
	@DisplayName("Exist by id test")
	void existByIdTest() {
	when(cmdHistRepo.existsById(anyInt())).thenReturn(false);
	assertThrows(UnknownMatchException.class,()->modifyPIS.changePackage(packageSH,"Whatever"));
	}
	@Test
	@DisplayName("Status not equal test")
	void statusNotEqualTest() {
		Optional<PackagesSenderHistory> optionalPSH;
		optionalPSH = Optional.of(packageSH);
		when(cmdHistRepo.existsById(anyInt())).thenReturn(true);
		when(cmdHistRepo.findById(anyInt())).thenReturn(optionalPSH);
		assertThrows(UnknownMatchException.class,()->modifyPIS.changePackage(packageSH,"Whatever"));
	}
	@Test
	@DisplayName("Email Sender test")
	void emailSenderTest() {
		Optional<PackagesSenderHistory> optionalPSH;
		optionalPSH = Optional.of(packageSH);
		when(cmdHistRepo.existsById(anyInt())).thenReturn(true);
		when(cmdHistRepo.findById(anyInt())).thenReturn(optionalPSH);
		assertThrows(UnknownMatchException.class,()->modifyPIS.changePackage(packageSH,"Whatever"));
	}
	/*@Test // Mai trebuie rezolvat cu connexiunea pentru testing
	@DisplayName("Change Package Test")
	void changePackageTest() throws JSONException {
		JwtUser.setUserName("emailSender");
		PackagesSenderHistory packageReady = packageSH;
		packageReady.setStatus("Ready");
		Optional<PackagesSenderHistory> optionalPSH;
		optionalPSH = Optional.of(packageReady);
		when(cmdHistRepo.existsById(anyInt())).thenReturn(true);
		when(cmdHistRepo.findById(anyInt())).thenReturn(optionalPSH);
		JSONObject res = new JSONObject();
		res.put("message", "Success");
		assertEquals(res.toString(),assertThrows(UnknownMatchException.class,()->modifyPIS.changePackage(packageReady,"Whatever")));
	}*/ 
}
