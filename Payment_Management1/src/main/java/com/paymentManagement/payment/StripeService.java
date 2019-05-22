package com.paymentManagement.payment;

import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Account;
import com.stripe.model.Charge;
import com.stripe.model.Token;
import com.stripe.model.Transfer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class StripeService {

    @Value("sk_test_yFbrSFbItspR2o3qw3J3bi1i00TJTbo5wo")
    String secretKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }

    public Charge charge(ChargeRequest chargeRequest)
            throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", chargeRequest.getAmount());
        chargeParams.put("currency", chargeRequest.getCurrency());
        chargeParams.put("description", chargeRequest.getDescription());
        chargeParams.put("source", chargeRequest.getStripeToken());
        return Charge.create(chargeParams);
    }
    
    public Transfer transfer() throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
    	

        Map<String, Object> cardParams = new HashMap<String, Object>();
        cardParams.put("number", "4242424242424242");
        cardParams.put("exp_month", 5);
        cardParams.put("exp_year", 2020);
        cardParams.put("cvc", "314");

            //    chargeRequest.setStripeToken(Token.create(tokenParams).getId());
    	
        Map<String, Object> tokenParams = new HashMap<String, Object>();
        Map<String, Object> accountParams = new HashMap<String, Object>();
        accountParams.put("type", "custom");
        accountParams.put("country", "US");
        accountParams.put("business_type", "company");
        accountParams.put("requested_capabilities", Arrays.asList("card_payments"));
        Account.create(accountParams);
        tokenParams.put("account", accountParams);
        
        Stripe.apiKey = "sk_test_yFbrSFbItspR2o3qw3J3bi1i00TJTbo5wo";
        
        
    	Map<String, Object> transferParams = new HashMap<String, Object>();
    	transferParams.put("amount", 400);
    	transferParams.put("currency", "usd");
    	transferParams.put("destination", Token.create(tokenParams).getId());
    	transferParams.put("transfer_group", "ORDER_95");

    	return Transfer.create(transferParams);
    	 
    	 
    }
}