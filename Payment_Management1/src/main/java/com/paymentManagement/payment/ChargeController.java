package com.paymentManagement.payment;

import com.paymentManagement.payment.ChargeRequest.Currency;
import com.paymentManagement.repositories.PaymentsRepository;
import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Token;
import com.stripe.model.Transfer;

import lombok.extern.java.Log;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//@Log
@RestController
public class ChargeController {

    @Autowired
    StripeService paymentsService;
    
    @Autowired
    PaymentsRepository paymentsRepo;

    @PostMapping("/payment/charge")
    public Payments charge(@RequestBody ChargeRequest chargeRequest) throws StripeException {
    	Payments model= new Payments();
        chargeRequest.setDescription("Simulation payment");
        chargeRequest.setCurrency(Currency.EUR);
         chargeRequest.setAmount(chargeRequest.getAmount()/100);
        Stripe.apiKey = "sk_test_yFbrSFbItspR2o3qw3J3bi1i00TJTbo5wo";
      
        Map<String, Object> tokenParams = new HashMap<String, Object>();
        Map<String, Object> cardParams = new HashMap<String, Object>();
        cardParams.put("number", "4242424242424242");
        cardParams.put("exp_month", 5);
        cardParams.put("exp_year", 2020);
        cardParams.put("cvc", "314");
        tokenParams.put("card", cardParams);
                chargeRequest.setStripeToken(Token.create(tokenParams).getId());


        Charge charge = paymentsService.charge(chargeRequest);
        model.setAmount(chargeRequest.getAmount());
        model.setCurrency(chargeRequest.getCurrency());
        model.setStatus(charge.getStatus());
        model.setChargeId (charge.getId());
        model.setBalanceTransactionId(charge.getBalanceTransaction());
        paymentsRepo.save(model);
        return model;
    }
    
    @GetMapping("payment/transfer")
    public void transfer() throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
    	Transfer transfer = paymentsService.transfer();
    }

    @ExceptionHandler(StripeException.class)
    public String handleError(Model model, StripeException ex) {
        model.addAttribute("error", ex.getMessage());
        return ex.getMessage();
    }
}