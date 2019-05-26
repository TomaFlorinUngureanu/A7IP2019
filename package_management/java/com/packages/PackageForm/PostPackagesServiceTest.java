package com.packages.PackageForm;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.packages.exceptions.UnknownMatchException;
import com.packages.model.PackagesSenderHistory;
import com.packages.model.RegisterPackage;
import com.packages.repositories.CommandsHistoryRepository;

class PostPackagesServiceTest {
	@InjectMocks
	PostPackagesService postPS;
	@Mock
	CommandsHistoryRepository cmdHistRepo;
	RegisterPackage form = new RegisterPackage();
	@BeforeEach
	void setUp() throws Exception {
		form.setEmailSender("emailSender");
		form.setHeight(10);
		form.setKilograms(20);
		form.setLength(30);
		form.setNamePackage("Package");
		form.setPhoneNumberReceiver("0800800800");
		form.setPhoneNumberSender("0900900900");
		form.setReceiverAdress("adresaReceiver");
		form.setReceiverName("Auras");
		form.setSenderAdress("adresaSender");
		form.setSenderName("Stegarul");
		form.setWidth(40);
		MockitoAnnotations.initMocks(this);
	}

	@Test
	@DisplayName("Form data Test")
	void formTest() {
		RegisterPackage packageESNull =form;
		packageESNull.setEmailSender(null);
		RegisterPackage packageHNull =form;
		packageHNull.setHeight(0);
		RegisterPackage packageKGNull =form;
		packageKGNull.setKilograms(0);
		RegisterPackage packageLNull =form;
		packageLNull.setLength(0);
		RegisterPackage packageNPNull =form;
		packageNPNull.setNamePackage(null);
		RegisterPackage packagePNRNull =form;
		packagePNRNull.setPhoneNumberReceiver(null);
		RegisterPackage packagePNSNull =form;
		packagePNSNull.setPhoneNumberSender(null);
		RegisterPackage packageRAdNull =form;
		packageRAdNull.setReceiverAdress(null);
		RegisterPackage packageRNNull =form;
		packageRNNull.setReceiverName(null);
		RegisterPackage packageSANull =form;
		packageSANull.setSenderAdress(null);
		RegisterPackage packageWNull =form;
		packageWNull.setWidth(0);
		assertThrows(UnknownMatchException.class,()->postPS.postPackage(packageESNull, "Whatever"));
		assertThrows(UnknownMatchException.class,()->postPS.postPackage(packageHNull, "Whatever"));
		assertThrows(UnknownMatchException.class,()->postPS.postPackage(packageKGNull, "Whatever"));
		assertThrows(UnknownMatchException.class,()->postPS.postPackage(packageLNull, "Whatever"));
		assertThrows(UnknownMatchException.class,()->postPS.postPackage(packageNPNull, "Whatever"));
		assertThrows(UnknownMatchException.class,()->postPS.postPackage(packagePNRNull, "Whatever"));
		assertThrows(UnknownMatchException.class,()->postPS.postPackage(packagePNSNull, "Whatever"));
		assertThrows(UnknownMatchException.class,()->postPS.postPackage(packageRAdNull, "Whatever"));
		assertThrows(UnknownMatchException.class,()->postPS.postPackage(packageRNNull, "Whatever"));
		assertThrows(UnknownMatchException.class,()->postPS.postPackage(packageSANull, "Whatever"));
		assertThrows(UnknownMatchException.class,()->postPS.postPackage(packageWNull, "Whatever"));
		
		
		
	}

}
