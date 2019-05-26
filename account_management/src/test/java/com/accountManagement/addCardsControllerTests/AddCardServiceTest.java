package com.accountManagement.addCardsControllerTests;

import com.accountManagement.addCards.AddCardService;
import com.accountManagement.exceptions.UnknownMatchException;
import com.accountManagement.login.JwtGenerator;
import com.accountManagement.login.LoginService;
import com.accountManagement.model.Cards;
import com.accountManagement.model.Users;
import com.accountManagement.repositories.CardsRepository;
import com.accountManagement.repositories.UsersRepository;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCrypt;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class AddCardServiceTest {

    @Mock
    private CardsRepository cardsRepo;
    private Cards cardTest;
    @InjectMocks
    AddCardService addCardService;

    @BeforeEach
    void setUp() throws Exception {
        int id = 2;
        String emailSender = "gigel@yahoo.com";
        String cardNumber = "1234";
        String mm = "Jun";
        String yy = "1996";
        String cvv = "123";
        String country = "ROMANIA";
        String zipCode = "12343";
        cardTest = new Cards(id, emailSender, cardNumber, mm, yy, cvv, country, zipCode);
        MockitoAnnotations.initMocks(this);
    }
    @Test
    @DisplayName("add service with valid data")
    void testAddCard_valid() {
        when(cardsRepo.save(cardTest)).thenReturn(cardTest);
        String result = addCardService.addCard(cardTest);
        JSONObject json = new JSONObject();
        json.put("message", "Success");
        assertTrue(json.toString().equals(result));
    }
    @Test
    @DisplayName("add service with invalid Country")
    void testAddCard_invalid_Country() {
        when(cardsRepo.save(cardTest)).thenReturn(cardTest);

        cardTest.setCountry(null);

        assertThrows(UnknownMatchException.class, () -> addCardService.addCard(cardTest));
    }
    @Test
    @DisplayName("add service with invalid CardNumber")
    void testAddCard_invalid_CardNumber() {
        when(cardsRepo.save(cardTest)).thenReturn(cardTest);

        cardTest.setCardNumber(null);

        assertThrows(UnknownMatchException.class, () -> addCardService.addCard(cardTest));
    }
    @Test
    @DisplayName("add service with invalid Cvv")
    void testAddCard_invalid_Cvv() {
        when(cardsRepo.save(cardTest)).thenReturn(cardTest);

        cardTest.setCvv(null);

        assertThrows(UnknownMatchException.class, () -> addCardService.addCard(cardTest));
    }
    @Test
    @DisplayName("add service with invalid Mm")
    void testAddCard_invalid_Mm() {
        when(cardsRepo.save(cardTest)).thenReturn(cardTest);

        cardTest.setMm(null);

        assertThrows(UnknownMatchException.class, () -> addCardService.addCard(cardTest));
    }
    @Test
    @DisplayName("add service with invalid Yy")
    void testAddCard_invalid_Yy() {
        when(cardsRepo.save(cardTest)).thenReturn(cardTest);

        cardTest.setYy(null);

        assertThrows(UnknownMatchException.class, () -> addCardService.addCard(cardTest));
    }
    @Test
    @DisplayName("add service with invalid ZipCode")
    void testAddCard_invalid_ZipCode() {
        when(cardsRepo.save(cardTest)).thenReturn(cardTest);

        cardTest.setZipCode(null);

        assertThrows(UnknownMatchException.class, () -> addCardService.addCard(cardTest));
    }
//    @Test
//    void addCard() {
//    }
}