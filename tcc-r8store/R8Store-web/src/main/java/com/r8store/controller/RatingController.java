package com.r8store.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import com.r8store.enums.Enum_Game;
import com.r8store.enums.Enum_Gender;
import com.r8store.enums.Enum_Pages;
import com.r8store.model.dao.PointDAO;
import com.r8store.model.dao.RatingDAO;
import com.r8store.model.dao.StoreDAO;
import com.r8store.model.entity.Point;
import com.r8store.model.entity.Rating;
import com.r8store.model.entity.Store;
import com.r8store.util.GameManager;

@Named
@ViewScoped
public class RatingController extends Controller implements Serializable {

	private static final long serialVersionUID = -674072893828135016L;
	
	private String token;
	private Rating rating;
	private Integer rate;
	private List<Rating> avaliations;
	private Rating selectedItem;
	private String employeeName;
	private Double points = 0d;
	
	private static final double POINTS = 1d;
	
	@Inject
	private UserController userController;
	
	@Inject
	private RatingDAO rDAO;
	
	@Inject
	private PointDAO pDAO;
	
	@Inject
	private StoreDAO sDAO;
	
	@PostConstruct
	public void init() {
		rating = new Rating();
	}
	
	public void createTokenPage(ComponentSystemEvent event) {
		if (!this.verifyPageAccess(userController.getMeta(), Enum_Pages.EMPLOYEEPAGES)) {
			return;
		}				
	}	
	
	public void createTokenStorePage(ComponentSystemEvent event) {
		if (!this.verifyPageAccess(userController.getMeta(), Enum_Pages.PERSONPAGES)) {
			return;
		}
		
		Map<String, String> params = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap();
		String token = params.get("success");
		
		if (token != null && !token.isEmpty()) {
			rating = rDAO.findByToken(token);
			if (rating != null) {
				Point p = pDAO.findByUserAndShopping(this.userController.getUsuarioLogado().getPerson().getId(), rating.getStore().getShoppingId());
				if (p != null) {
					points = p.getPoints();
				}
			}
		}		
	}
	
	public void createListRatingPage(ComponentSystemEvent event) {
		if (!this.verifyPageAccess(userController.getMeta(), Enum_Pages.PAGEDASHBOARD)) {
			return;
		}
		if (this.avaliations == null) {
			this.avaliations = rDAO.findByStore(userController.getStoreId());
		}
	}
	
	public void createRatingPage(ComponentSystemEvent event) {
		if (!this.verifyPageAccess(userController.getMeta(), Enum_Pages.PERSONPAGES)) {
			return;
		}
		
		Map<String, String> params = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap();
		this.rating.setToken(params.get("token"));
		this.rate = 1;
		
		this.rating = rDAO.findByToken(this.rating.getToken());
		
		if (this.rating == null || this.rating.getPerson() != null || this.rating.getToken() == null) {
			this.redirect(this.getAbsolutePath() + "/user/rating/tokenLoja.xhtml");
		}
	}
	
	public void filterAvaliations() {
		this.avaliations = rDAO.findByEmployee(this.userController.getStoreId(), employeeName);
		System.out.println("Tamanho: " + this.avaliations.size());
	}
	
	public void generateToken() {
		this.token = Integer.toHexString((new Date().toString() + 
				userController.getUsuarioLogado().getEmployee().getId()).hashCode()).toUpperCase();
		
		Rating rating = new Rating();
		
		rating.setId(0l);
		rating.setToken(this.token);
		rating.setEmployee(userController.getUsuarioLogado().getEmployee());
		rating.setStore(userController.getUsuarioLogado().getEmployee().getActualStore());
		
		rDAO.merge(rating);
	}
	
	public void createRate() {
		this.rating.setTotal(this.rate.doubleValue());
		this.rating.setPerson(userController.getUsuarioLogado().getPerson());
		this.rating.setPoints(POINTS);		
		
		rDAO.update(rating);
		
		Store s = new Store();
		if(this.rating.getStore().getShopping() == null) {
			s = this.rating.getStore();
		} else {
			s = this.rating.getStore().getShopping();
		}
		
		Point p = pDAO.findByUserAndShopping(userController.getUsuarioLogado().getPerson().getId(), s.getId());
		if (p == null) {
			p = new Point();
			p.setId(0l);
			p.setPoints(0d);
			p.setStore(s);
			p.setPerson(this.userController.getUsuarioLogado().getPerson());
		}
		p.setPoints(p.getPoints() + POINTS);
		p.setAccumulated(p.getAccumulated() + POINTS);
				
		p = pDAO.merge(p);
		GameManager.createGame(p, Enum_Game.RATE, POINTS);
		
		Store store = this.rating.getStore();
		Float media = rDAO.findTotalByStore(store.getId()).floatValue();
		System.out.println("Media: " + media);
		store.setRating(media);
		sDAO.merge(store);
		
		this.redirect(this.getAbsolutePath() + "/user/rating/tokenLoja.xhtml?success=" + this.rating.getToken());
	}
	
	public void rateStore() {
		if (this.token == null || this.token.isEmpty()) {
			this.addErrorMessage("Ops", "É necessário inserir um token");
			return;
		}
		
		this.rating = rDAO.findByToken(this.token);
		
		if (this.rating != null && this.rating.getPerson() != null) {
			this.addErrorMessage("Ops", "Este token já foi utilizado.");
			return;
		}
		
		if (this.rating == null) {
			this.addErrorMessage("Ops", "Nenhuma avaliação encontrada");
			return;
		}
		
		this.redirect(this.getAbsolutePath() + "/user/rating/avaliarLoja.xhtml?token=" + token);	
	}

	public UserController getUserController() {
		return userController;
	}

	public void setUserController(UserController userController) {
		this.userController = userController;
	}

	public Enum_Gender[] genders() {
		return Enum_Gender.values();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public List<Rating> getAvaliations() {
		return avaliations;
	}

	public void setAvaliations(List<Rating> avaliations) {
		this.avaliations = avaliations;
	}

	public Rating getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(Rating selectedItem) {
		this.selectedItem = selectedItem;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Double getPoints() {
		return points;
	}

	public void setPoints(Double points) {
		this.points = points;
	}

}

