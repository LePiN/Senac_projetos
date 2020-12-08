package com.r8store.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import com.r8store.enums.Enum_Pages;
import com.r8store.model.dao.OfferDAO;
import com.r8store.model.dao.ProductDAO;
import com.r8store.model.entity.Offer;
import com.r8store.model.entity.Product;

@Named
@ViewScoped
public class OfferController extends Controller implements Serializable {

	private static final long serialVersionUID = 8770630066272063822L;

	@Inject
	private UserController userController;
	
	private Offer offer;
	
	private Offer selectedOffer;
	
	private List<Offer> offers;
	
	private List<Product> products;
	
	@Inject
	private OfferDAO oDAO;
	
	@Inject
	private ProductDAO pDAO;
	
	@PostConstruct
	public void init() {
		this.offer = new Offer();
	}
	
	public void registerAction() {
		try {
			Map<String, String> validation = this.offer.validate();
			if (validation.isEmpty()) {
				oDAO.merge(this.offer);
				this.offer = new Offer();
				this.addMessage("Sucesso", "Cadastro realizado com sucesso.");
				this.setErrors(null);				
				return;
			} else {
				this.setErrors(validation); 
				return;
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public void createAddOfferPage(ComponentSystemEvent event) {
		if (!this.verifyPageAccess(userController.getMeta(), Enum_Pages.EMPLOYEEPAGES)) {
			return;
		}
		this.products = pDAO.findProductsToOffer(userController.getStoreId());
	}
	
	public void createListOfferPage(ComponentSystemEvent event){
		if (!this.verifyPageAccess(userController.getMeta(), Enum_Pages.EMPLOYEEPAGES)) {
			return;
		}
		if (this.userController.getUsuarioLogado().getStore() != null) {
			this.offers = oDAO.findOffersByStore(this.userController.getUsuarioLogado().getStore().getId());
		} else {
			this.offers = oDAO.findOffersByStore(this.userController.getUsuarioLogado().getEmployee().getActualStore().getId());
		}		
	}
	
	public void delete() {		
		oDAO.removeById(this.selectedOffer.getId());
		
		offers.remove(this.selectedOffer);
		this.addMessage("Sucesso", "Sua exclusão foi realizada com sucesso.");
		
		return;
	}	
	
	public void update() {			
		Map<String, String> validation = this.selectedOffer.validate();
		
		if (validation.isEmpty()) {
			oDAO.merge(this.selectedOffer);
			this.selectedOffer = new Offer();
			this.addMessage("Sucesso", "Sua alteração foi realizada com sucesso.");
			this.executeCommand("PF('editDialog').hide()");			
			return;
		} else {
			this.setErrors(validation);
			return;
		}
		
	}	

	public UserController getUserController() {
		return userController;
	}

	public void setUserController(UserController userController) {
		this.userController = userController;
	}

	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	public List<Offer> getOffers() {
		return offers;
	}

	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}

	public Offer getSelectedOffer() {
		return selectedOffer;
	}

	public void setSelectedOffer(Offer selectedOffer) {
		this.selectedOffer = selectedOffer;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
}
