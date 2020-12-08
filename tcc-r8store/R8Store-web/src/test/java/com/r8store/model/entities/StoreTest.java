package com.r8store.model.entities;

import com.r8store.model.entity.Store;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe com um exemplo de teste JUnit para classe de entidades Store.
 * 
 * @author Leandro Pieper Nunes
 *
 */

public class StoreTest {
	
	private Store loja_01;
	private Store loja_02;

	@Before
	public void setUp() throws Exception{
		
		loja_01 = new Store();
			
		loja_01.setName("Loxinha Da Esquina");
		loja_01.setEmail("Lojinha@outlook.com");
		loja_01.setCnpj("11122233344");		
		loja_01.setInscricaoEstadual("543543543");
		
		loja_02.setName("Feirinha da Praça");
		loja_02 = new Store();
		loja_02.setCnpj("55566677788");
		loja_02.setEmail("Feirinha@gmail.com");
		loja_02.setInscricaoEstadual("987987987");
	}

	@Test
	public void testeMetodoValidate() {
		
		assertNotNull(loja_01.getCnpj());
		assertNotNull(loja_01.getInscricaoEstadual());
		assertNotNull(loja_01.getEmail());
		assertNotNull(loja_01.getName());
		
		assertTrue("CNPJ possui o número errado de caracteres", loja_01.getCnpj().length() == 14);
		assertNotSame("CNPJ já registrado na base", loja_01.getCnpj() , loja_02.getCnpj());
		assertTrue("Inscrição Estadual possui o número errado de caracteres", loja_01.getInscricaoEstadual().length() == 9);
		assertNotSame("Inscrição Estadual já registrado na base", loja_01.getInscricaoEstadual() , loja_02.getInscricaoEstadual());
		assertNotSame("Email já registrado na base", loja_01.getEmail() , loja_02.getEmail());		
	
	}
	
 }

