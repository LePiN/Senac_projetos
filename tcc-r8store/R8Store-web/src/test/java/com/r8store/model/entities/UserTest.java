package com.r8store.model.entities;

/*
 * @autor: Leandro Ferreira
 * 
 */
import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import com.r8store.model.entity.User;
import junit.framework.TestCase;

public class UserTest {

	private User user = new User();
	private User user1 = new User();
	
	
	@Before
	public void setUp() throws Exception {
		user.setLogin("Leandro");
		user.setPassword("250796Ls");
		user.setConfPassword("250796Ls");
		
		/* Setar outro login para fazer a comparacao de login*/
		user1.setLogin("Leonardo");
		
	}
	
	@Test
	public void testValidateRegister() {
		assertNotNull("Login nao pode ser nulo",user.getLogin());
		assertNotNull("Password não pode ser nulo",user.getPassword());
		assertNotNull("Confirmação de Password não pode ser nulo",user.getConfPassword());
		assertTrue("Login nao pode ser vazio",user.getLogin() != "");
		assertNotSame("Login não pode ser igual a senha",user.getLogin(),user.getPassword());
		assertEquals("As senhas deven ser iguais", user.getPassword(), user.getConfPassword());
		assertFalse("Senha não ter menos que 6 caracteres", user.getPassword().length() < 6);
		assertTrue("Login deve ter mais de 5 caracteres", user.getLogin().length() > 5);
		assertNotSame("Login ja existe", user.getLogin(), user1.getLogin());
	}
	
}






