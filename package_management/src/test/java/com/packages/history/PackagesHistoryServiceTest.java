package com.packages.history;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.packages.exceptions.UnknownMatchException;
import com.packages.model.JwtUser;
import com.packages.model.PackagesDriverHistory;
import com.packages.model.PackagesSenderHistory;
import com.packages.repositories.CommandsHistoryRepository;

class PackagesHistoryServiceTest {
	@InjectMocks
	PackagesHistoryService packagesHistoryService;
	@Mock
	CommandsHistoryRepository cmdHistRepo;
	PackagesSenderHistory packageSH = new PackagesSenderHistory();
	PackagesDriverHistory packageDH = new PackagesDriverHistory();
	@BeforeEach
	void setUp() throws Exception {
		JwtUser.setUserName("emailSender");
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
		
		packageDH.setId(1);
		packageDH.setHeight(10);
		packageDH.setKilograms(20);
		packageDH.setLength(30);
		packageDH.setNamePackage("Package");
		packageDH.setPhoneNumberReceiver("0800800800");
		packageDH.setPhoneNumberSender("0900900900");
		packageDH.setReceiverAdress("adresaReceiver");
		packageDH.setReceiverName("Auras");
		packageDH.setSenderAdress("adresaSender");
		packageDH.setSenderName("Stegarul");
		packageDH.setWidth(40);
		
		MockitoAnnotations.initMocks(this);
	}

	@Test
	@DisplayName("Exist id test")
	void existByIdTest() {
		when(cmdHistRepo.existsById(anyInt())).thenReturn(false);
		assertThrows(UnknownMatchException.class,()->packagesHistoryService.getPackageSender(1));
	}
	@Test
	@DisplayName("Get the sender test")
	void getSenderPackageTest() {
		Optional<PackagesSenderHistory> optionalPSH;
		optionalPSH = Optional.of(packageSH);
		when(cmdHistRepo.existsById(anyInt())).thenReturn(true);
		when(cmdHistRepo.findById(anyInt())).thenReturn(optionalPSH);
		assertEquals(packagesHistoryService.getPackageSender(1).getId(),packageSH.getId());
		assertEquals(packagesHistoryService.getPackageSender(1).getEmailDriver(),packageSH.getEmailDriver());
		assertEquals(packagesHistoryService.getPackageSender(1).getEmailSender(),packageSH.getEmailSender());
		assertEquals(packagesHistoryService.getPackageSender(1).getHeight(),packageSH.getHeight());
		assertEquals(packagesHistoryService.getPackageSender(1).getKilograms(),packageSH.getKilograms());
		assertEquals(packagesHistoryService.getPackageSender(1).getLength(),packageSH.getLength());
		assertEquals(packagesHistoryService.getPackageSender(1).getNamePackage(),packageSH.getNamePackage());
		assertEquals(packagesHistoryService.getPackageSender(1).getPhoneNumberReceiver(),packageSH.getPhoneNumberReceiver());
		assertEquals(packagesHistoryService.getPackageSender(1).getPhoneNumberSender(),packageSH.getPhoneNumberSender());
		assertEquals(packagesHistoryService.getPackageSender(1).getPin(),packageSH.getPin());
		assertEquals(packagesHistoryService.getPackageSender(1).getReceiverAdress(),packageSH.getReceiverAdress());
		assertEquals(packagesHistoryService.getPackageSender(1).getReceiverName(),packageSH.getReceiverName());
		assertEquals(packagesHistoryService.getPackageSender(1).getSenderAdress(),packageSH.getSenderAdress());
		assertEquals(packagesHistoryService.getPackageSender(1).getSenderName(),packageSH.getSenderName());
		assertEquals(packagesHistoryService.getPackageSender(1).getStatus(),packageSH.getStatus());
		assertEquals(packagesHistoryService.getPackageSender(1).getWidth(),packageSH.getWidth());
	}
	@Test
	@DisplayName("Exist by email sender Test")
	void existsByEmailSenderTest() {
		when(cmdHistRepo.existsByEmailSender(anyString())).thenReturn(false);
		assertThrows(UnknownMatchException.class,()->packagesHistoryService.gePackagesHistorySender());
	}
	@Test
	@DisplayName("Get the Sender History")
	void getSenderHistoryTest() {
		List<PackagesSenderHistory> listPackagSender = new ArrayList<>();
		listPackagSender.add(packageSH);
		when(cmdHistRepo.existsByEmailSender(anyString())).thenReturn(true);
		when(cmdHistRepo.findAllByEmailSender(anyString())).thenReturn(listPackagSender);
		assertEquals(packagesHistoryService.gePackagesHistorySender(),listPackagSender);
	}
	@Test
	@DisplayName("Exist by email driver test")
	void existByEmailDriverTest() {
		when(cmdHistRepo.existsByEmailDriver(anyString())).thenReturn(false);
		assertThrows(UnknownMatchException.class,()->packagesHistoryService.gePackagesHistoryDriver());
	}
	@Test
	@DisplayName("Get the Driver History")
	void getDriverHistoryTest() {
		List<PackagesDriverHistory> listPackageDriver = new ArrayList<PackagesDriverHistory>();
		listPackageDriver.add(packageDH);
		List<PackagesSenderHistory> listPackagSender = new ArrayList<PackagesSenderHistory>();
		listPackagSender.add(packageSH);
		when(cmdHistRepo.existsByEmailDriver(anyString())).thenReturn(true);
		when(cmdHistRepo.findAllByEmailDriver(anyString())).thenReturn(listPackagSender);
		assertEquals(packagesHistoryService.gePackagesHistoryDriver().get(0).getId(),listPackageDriver.get(0).getId());
		assertEquals(packagesHistoryService.gePackagesHistoryDriver().get(0).getHeight(),listPackageDriver.get(0).getHeight());
		assertEquals(packagesHistoryService.gePackagesHistoryDriver().get(0).getKilograms(),listPackageDriver.get(0).getKilograms());
		assertEquals(packagesHistoryService.gePackagesHistoryDriver().get(0).getLength(),listPackageDriver.get(0).getLength());
		assertEquals(packagesHistoryService.gePackagesHistoryDriver().get(0).getNamePackage(),listPackageDriver.get(0).getNamePackage());
		assertEquals(packagesHistoryService.gePackagesHistoryDriver().get(0).getPhoneNumberReceiver(),listPackageDriver.get(0).getPhoneNumberReceiver());
		assertEquals(packagesHistoryService.gePackagesHistoryDriver().get(0).getPhoneNumberSender(),listPackageDriver.get(0).getPhoneNumberSender());
		assertEquals(packagesHistoryService.gePackagesHistoryDriver().get(0).getReceiverAdress(),listPackageDriver.get(0).getReceiverAdress());
		assertEquals(packagesHistoryService.gePackagesHistoryDriver().get(0).getReceiverName(),listPackageDriver.get(0).getReceiverName());
		assertEquals(packagesHistoryService.gePackagesHistoryDriver().get(0).getSenderAdress(),listPackageDriver.get(0).getSenderAdress());
		assertEquals(packagesHistoryService.gePackagesHistoryDriver().get(0).getSenderName(),listPackageDriver.get(0).getSenderName());
		assertEquals(packagesHistoryService.gePackagesHistoryDriver().get(0).getWidth(),listPackageDriver.get(0).getWidth());
		
		
	}
}
