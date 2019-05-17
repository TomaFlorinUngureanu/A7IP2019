package com.accountManagement.addCards;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accountManagement.exceptions.UnknownMatchException;
import com.accountManagement.model.Cards;
import com.accountManagement.model.JwtUser;
import com.accountManagement.repositories.CardsRepository;

import net.minidev.json.JSONObject;

@Service
public class AddCardService {

	@Autowired
	private CardsRepository cardsRepo;

	public String addCard(Cards card) {
		
		if(card.getCardNumber()==null || card.getCountry()==null || card.getCvv()==null 
				|| card.getMm()==null || card.getYy()==null || card.getZipCode()==null )
			throw new UnknownMatchException ("Invalid data");
		
		card.setEmailSender(JwtUser.getUserName());
		
		cardsRepo.save(card);
		
		JSONObject json = new JSONObject();
		json.put("message", "Success");
		return json.toString();
	} 
	
	
}
