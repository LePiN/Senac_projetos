package com.r8store.model.dao;

import org.junit.Test;

import com.r8store.enums.Enum_Game;
import com.r8store.model.dao.PointDAO;
import com.r8store.model.entity.Point;
import com.r8store.util.GameManager;

public class GameDAOTest {

	@Test
	public void selectTest() {
		Point p = new PointDAO().findById(2l);
		GameManager.createGame(p, Enum_Game.RATE, 1);
	}
	
}
