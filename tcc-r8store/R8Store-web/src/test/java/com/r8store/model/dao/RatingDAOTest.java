package com.r8store.model.dao;

import org.junit.Test;

public class RatingDAOTest {
	
	@Test
	public void testTotal() {
		RatingDAO rDAO = new RatingDAO();
		Float media = rDAO.findTotalByStore(2l).floatValue();
		System.out.println(media);
	}
	
}
