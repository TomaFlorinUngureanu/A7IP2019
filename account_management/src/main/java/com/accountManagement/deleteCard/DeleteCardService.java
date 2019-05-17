package com.accountManagement.deleteCard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accountManagement.exceptions.UnknownMatchException;
import com.accountManagement.model.Cards;
import com.accountManagement.model.JwtUser;
import com.accountManagement.repositories.CardsRepository;

import net.minidev.json.JSONObject;

@Service
public class DeleteCardService {
	
	@Autowired
	private CardsRepository cardsRepo;

	public String deleteCard(String cardNumber) {
		
		
		if(!cardsRepo.existsByCardNumber(cardNumber)) throw new UnknownMatchException ("Invalid card");
		
		Cards card=new Cards();
		card=cardsRepo.findByCardNumber(cardNumber);
		
		if(!card.getEmailSender().equals(JwtUser.getUserName())) throw new UnknownMatchException ("You don't have this card saved");
		
		cardsRepo.delete(card);
		
		JSONObject json = new JSONObject();
		json.put("message", "Success");
		return json.toString();
	}
	
}
