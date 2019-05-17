package com.accountManagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accountManagement.model.Cards;

public interface CardsRepository extends JpaRepository<Cards,Integer>{

	public List<Cards> findAllByEmailSender(String email_sender);
	public Boolean existsByEmailSender(String email_sender); 
	public Boolean existsByCardNumber(String cardNumber); 
	public Cards findByCardNumber(String card_number);
}
