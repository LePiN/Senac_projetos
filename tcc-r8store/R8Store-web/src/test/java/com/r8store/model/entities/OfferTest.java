package com.r8store.model.entities;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import com.r8store.model.entity.Offer;


// Gabriel
public class OfferTest {
	private Offer offer;

	@Before
	public void setUp() throws Exception{
		offer = new Offer();
		
		offer.setTitle("Liquidação");
		offer.setDescription("Liquidação de sexta a domingo");
		offer.setDiscount(10.50);
		
	}
	@Test
	public void testValidade() {
		
		assertNotNull("Titulo nao pode ser nulo",offer.getTitle());
		assertNotNull("Desconto nao pode ser nulo nao pode ser nula",offer.getDiscount());
		assertNotNull("Descrição nao pode ser nula",offer.getDescription());
		
		assertTrue("Nome nao pode ser vazio",offer.getTitle() != "");
		assertFalse("Quantidade nao pode ser menor que 0",offer.getDiscount() < 0);
		
		assertTrue("Descrião nao pode ser vazia",offer.getDescription() != "");
	}
}
