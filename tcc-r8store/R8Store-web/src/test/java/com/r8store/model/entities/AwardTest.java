package com.r8store.model.entities;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import com.r8store.model.entity.Award;

/*
 * @author
 * Lucas De Martino Macedo
 */

public class AwardTest {

	// classe de teste
	private Award award;

	// testando insert
	@Before
	public void setUp() throws Exception {

		award = new Award();
		Date dt = new Date();
		
		// nome/titulo do premio
		award.setName("Smartphone LG S3");
		// descricao do premio
		award.setDescription( "Lorem ipsum aenean phasellus laoreet "
							+ "phasellus cursus elementum felis ligula,"
							+ " auctor hac scelerisque at rutrum "
							+ "leo blandit etiam, consectetur accumsan");
		// quantidade disponivel
		award.setQuantity(100);
		// pontos nescessarios
		award.setPoints(30);
		//data do premio
		award.setDate(dt);
		
	}
	
	// testando validacao
	@Test
	public void testValidade() {

		assertNotNull("Nome / titulo do premio nao pode ser nulo", award.getName());
		assertNotNull("Quantidade do premio nao pode ser nulo", award.getQuantity());
		assertNotNull("A Descricao do premio nao pode ser nulo", award.getDescription());
		assertNotNull("O pontos do premio nao pode ser nulo", award.getPoints());
		assertNotNull("A data do premio nao pode ser nulo", award.getDate());

		assertTrue("Nome nao pode ser vazio", award.getName() != "");
		assertFalse("Quantidade nao pode ser menor que 0", award.getQuantity() < 0);
		assertFalse("Pontos nao pode ser menor que 0", award.getPoints() < 0);
		assertTrue("A Descricao do premio nao pode ser vazia", award.getDescription() != "");
	}
}
