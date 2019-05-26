package com.accountManagement.deleteCard;

import com.accountManagement.addCards.AddCardService;
import com.accountManagement.exceptions.UnknownMatchException;
import com.accountManagement.model.Cards;
import com.accountManagement.repositories.CardsRepository;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class DeleteCardServiceTest {
    @Mock
    private CardsRepository cardsRepo;

    private Cards cardTest;
    String cardNumber;
    @InjectMocks
    DeleteCardService deleteCardService;

    @BeforeEach
    void setUp() throws Exception {
        int id = 2;
        String emailSender = "gigel@yahoo.com";
        cardNumber = "1234";
        String mm = "Jun";
        String yy = "1996";
        String cvv = "123";
        String country = "ROMANIA";
        String zipCode = "12343";
        cardTest = new Cards(id, emailSender, cardNumber, mm, yy, cvv, country, zipCode);
        MockitoAnnotations.initMocks(this);
    }
    @Test
    @DisplayName("add service with invalid cardNumber")
    void testAddCard_invalid_cardNumber() {

        Boolean exists = cardsRepo.existsByCardNumber(cardNumber);
        JSONObject json = new JSONObject();
        json.put("message", "Success");
        assertThrows(UnknownMatchException.class, () -> deleteCardService.deleteCard(cardNumber));

    }
    @Test
    @DisplayName("add service with no card saved")
    void testAddCard_no_card_saved() {
        when(cardsRepo.existsById(anyInt())).thenReturn(true);

        JSONObject json = new JSONObject();
        json.put("message", "Success");
        assertThrows(UnknownMatchException.class, () -> deleteCardService.deleteCard(cardNumber));

//        assertTrue(json.toString().equals(result));
    }

}