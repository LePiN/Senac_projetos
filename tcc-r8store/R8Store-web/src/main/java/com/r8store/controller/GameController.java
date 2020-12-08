package com.r8store.controller;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import com.r8store.enums.Enum_Game;
import com.r8store.enums.Enum_Pages;
import com.r8store.model.dao.PointDAO;
import com.r8store.util.GameManager;

@Named
@ViewScoped
public class GameController extends Controller implements Serializable {

	private static final long serialVersionUID = 8591070972351901354L;
	
	private Double points;
	
	@Inject
	private UserController userController;
	
	@Inject
	private PointDAO pDAO;
	
	@PostConstruct
	public void init() {
	}
	
	public void createGamePage(ComponentSystemEvent event) {
		if (!this.verifyPageAccess(userController.getMeta(), Enum_Pages.PERSONPAGES)) {
			return;
		}	
		
		points = pDAO.findAccumulated(this.userController.getUsuarioLogado().getPerson().getId());
	}
	
	public boolean checkGame(String type) {
		return GameManager.checkGame(Enum_Game.valueOf(type), userController.getUsuarioLogado().getPerson(), new Date());
	}

	public Double getPoints() {
		return points;
	}

	public void setPoints(Double points) {
		this.points = points;
	}
	
}
