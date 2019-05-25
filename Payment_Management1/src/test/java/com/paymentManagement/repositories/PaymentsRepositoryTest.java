package com.paymentManagement.repositories;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.paymentManagement.payment.ChargeRequest.Currency;
import com.paymentManagement.payment.Payments;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PaymentsRepositoryTest {

	private Payments payment;
	private int id;
	
	@Autowired
	PaymentsRepository repo;
	
	@BeforeEach
	public void setUp() throws Exception {
		
		payment = new Payments();
		payment.setAmount(100);
		payment.setBalanceTransactionId("zxcvbn");
		payment.setChargeId("mnbvc");
		payment.setStatus("succeeded");
		payment.setCurrency(Currency.EUR);
		
		repo.save(payment);
		id = repo.findAll().get(repo.findAll().size()-1).getId();

	}
	@AfterEach
	void deleteData() {
		repo.deleteById(id);
	}

	@Test
	@DisplayName("saveData test")
	public void testSave() {
		assertThat(repo.existsById(id),is(true));
		repo.deleteById(id);
		assertThat(repo.existsById(id),is(false));
		repo.save(payment);
		id = repo.findAll().get(repo.findAll().size()-1).getId();
	}
	
	@Test
	@DisplayName("find by id test")
	public void testFindBYId() {
		assertThat(repo.findById(id).get().getAmount(),is(payment.getAmount()));
		assertThat(repo.findById(id).get().getBalanceTransactionId(),is(payment.getBalanceTransactionId()));
		assertThat(repo.findById(id).get().getChargeId(),is(payment.getChargeId()));
		assertThat(repo.findById(id).get().getCurrency(),is(payment.getCurrency()));
		assertThat(repo.findById(id).get().getStatus(),is(payment.getStatus()));
	}

}
