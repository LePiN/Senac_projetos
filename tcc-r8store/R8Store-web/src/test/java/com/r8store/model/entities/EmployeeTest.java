package com.r8store.model.entities;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;
import com.r8store.model.entity.Employee;

// Matheus
public class EmployeeTest {
	Employee emp = new Employee();
	Employee emp1 = new Employee();
	@Before
	public void setUp() throws Exception{
		emp1.setEmail("emp@gmail.com");
		emp1.setTel("22222222");
		
		emp.setEmail("emp@gmail.com");
		emp.setName("Nome emp");
		emp.setTel("11111111");
		
	}
	@Test
	public void testValidade() {
		assertNotNull("Nome nao pode ser nulo",emp.getName());
		assertNotNull("Email nao pode ser nulo",emp.getEmail());
		assertNotNull("Descrição nao pode ser nula",emp.getTel());
		
		assertTrue("Nome nao pode ser vazio", emp.getName() != "");
		assertTrue("Email nao pode ser vazio", emp.getEmail() != "");
		assertTrue("Nome nao pode ser vazio", emp.getTel() != "");
		assertTrue("Nome nao pode ser igual ao email", emp.getName() != emp.getEmail());
		
		assertTrue("Email ja existe",emp.getEmail() != emp1.getEmail());
		assertTrue("Telefone ja existe",emp.getTel() != emp1.getTel());
	}
}
