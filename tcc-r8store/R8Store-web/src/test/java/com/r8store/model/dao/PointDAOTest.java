package com.r8store.model.dao;

import org.junit.Test;

import com.r8store.model.dao.PointDAO;

public class PointDAOTest {
	
	@Test
	public void testFindAccumulated() {
		PointDAO pDAO = new PointDAO();	
		Double points = pDAO.findAccumulated(1l);
		System.out.println(points);
	}
	
}
