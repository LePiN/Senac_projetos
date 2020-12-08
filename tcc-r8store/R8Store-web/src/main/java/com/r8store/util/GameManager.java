package com.r8store.util;

import java.util.Date;
import java.util.List;

import com.r8store.enums.Enum_Game;
import com.r8store.model.dao.GameDAO;
import com.r8store.model.entity.Game;
import com.r8store.model.entity.Person;
import com.r8store.model.entity.Point;

public class GameManager {
	
	public static void createGame(Point point, Enum_Game type, double points) {
		switch(type) {
			case RATE:
				createGeneric(point, type, points);
				break;
			case AWARD:	
				createGeneric(point, type, points);
				break;
			case CHECKIN:
				createGeneric(point, type, points);
				break;
		}
	}
	
	public static boolean checkGame(Enum_Game type, Person person, Date date) {
		switch(type) {
			case RATE:
				return checkGeneric(type, person, date);
			case AWARD:
				return checkGeneric(type, person, date);
			case CHECKIN:
				return checkGeneric(type, person, date);
		}	
		return false;
	}
	
	private static void createGeneric(Point point, Enum_Game type, double points) {
		GameDAO gDAO = new GameDAO();
		List<Game> games = gDAO.findByDate(point.getPerson().getId(), type, new Date());
		Game game = new Game();						
		
		game.setPoint(point);
		game.setType(type);
		game.setDate(new Date());
		game.setPoints(points);
		
		gDAO.merge(game);
		
		if (games.size() == 4) {
			gDAO.updateByPerson(point.getPerson().getId(), 5d);
		}
	}	
	
	private static boolean checkGeneric(Enum_Game type, Person person, Date date) {
		GameDAO gDAO = new GameDAO();
		List<Game> games = gDAO.findByDate(person.getId(), type, new Date());
		
		if (games.size() >= 5) {
			return true;
		}
		return false;
	}
}
