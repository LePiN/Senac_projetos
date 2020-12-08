package com.r8store.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.primefaces.model.UploadedFile;

import com.r8store.enums.Enum_Pages;
import com.r8store.model.dao.AwardDAO;
import com.r8store.model.dao.PersonAwardDAO;
import com.r8store.model.dao.PointDAO;
import com.r8store.model.dao.ProductDAO;
import com.r8store.model.entity.Award;
import com.r8store.model.entity.Person;
import com.r8store.model.entity.PersonAward;
import com.r8store.model.entity.Point;
import com.r8store.model.entity.Product;

@Named
@ViewScoped
public class AwardController extends Controller implements Serializable {

	private static final long serialVersionUID = -2060753105517046144L;

	@Inject
	private UserController userController;
	
	@Inject
	private AwardDAO aDAO;
	
	@Inject
	private ProductDAO pDAO;
	
	@Inject
	private PointDAO pointDAO;
	
	@Inject
	private PersonAwardDAO paDAO;
	
	private Award award;
	private Award selectedAward;
	private List<Product> products;
	private List<Award> awards;
	private String code;
	private PersonAward personAward;
	private Person person;
	private Double points;
	
	private UploadedFile file;
		
	@PostConstruct
	public void init() {
		award = new Award();
		selectedAward = new Award();
		this.person = userController.getUsuarioLogado().getPerson();
	}
	
	public void createAddAwardPage(ComponentSystemEvent event) {
		if (!this.verifyPageAccess(userController.getMeta(), Enum_Pages.EMPLOYEEPAGES)) {
			return;
		}
		if(this.userController.getUsuarioLogado().getEmployee() != null) {
			this.products = pDAO.findProductsToAward(this.userController.getUsuarioLogado().getEmployee().getActualStore().getId());
		} else {
			this.products = pDAO.findProductsToAward(this.userController.getUsuarioLogado().getStore().getId());
		}
	}
	
	public void createConfirmPage(ComponentSystemEvent event) {
		if (!this.verifyPageAccess(userController.getMeta(), Enum_Pages.EMPLOYEEPAGES)) {
			return;
		}
	}
	
	public void createListAwardPage(ComponentSystemEvent event) {
		if (!this.verifyPageAccess(userController.getMeta(), Enum_Pages.EMPLOYEEPAGES)) {
			return;
		}
		if (this.userController.getUsuarioLogado().getStore() != null) {
			awards = aDAO.findByStore(this.userController.getUsuarioLogado().getStore().getId());
		} else {
			awards = aDAO.findByStore(this.userController.getUsuarioLogado().getEmployee().getActualStore().getId());
		}
		
		if(this.getErrors() != null && this.getErrors().size() > 0) {
			this.executeCommand("PF('editDialog').show()");	
		}
	}
	
	public void showAward(ComponentSystemEvent event){
		if (!this.verifyPageAccess(userController.getMeta(), Enum_Pages.PERSONPAGES)) {
			return;
		}
		
		Map<String, String> params = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap();
				
		this.award = aDAO.findById(Long.parseLong(params.get("id")));
		Point point = this.person.pointByShop(this.award.getStore().getShopping().getId());
		if (point == null) {
			point = new Point();
			point.setPoints(0d);
			point.setAccumulated(0d);
			point.setPerson(this.person);
			point.setStore(this.award.getStore().getShopping());
			point = pointDAO.merge(point);
		}
		points = point.getPoints();
	}
	
	public void registerAction() {
		try {
			Map<String, String> validation = award.validate(false);
			if(this.file.getFileName() == null || this.file.getFileName().isEmpty()) {
				validation.put("image", "É necessário inserir uma imagem");
			}	
			
			if (validation.isEmpty()) {
				if(this.userController.getUsuarioLogado().getEmployee() != null) {
					award.setStore(this.userController.getUsuarioLogado().getEmployee().getActualStore());
				} else {
					award.setStore(this.userController.getUsuarioLogado().getStore());
				}
				this.award = aDAO.merge(award);
				
				award.setFile(this.file);
				award.upload("imagemPrincipal");
				
				this.addMessage("Sucesso", "Cadastro realizado com sucesso.");
				award = new Award();
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
	
	public void delete() {		
		aDAO.removeById(this.selectedAward.getId());
		
		awards.remove(this.selectedAward);
		this.addMessage("Sucesso", "Sua exclusão foi realizada com sucesso.");
		
		return;
	}	
	
	public void update() {			
		Map<String, String> validation = this.selectedAward.validate(true);
		
		if (validation.isEmpty()) {
			aDAO.merge(this.selectedAward);
			
			if(this.file != null && this.file.getFileName() != null && !this.file.getFileName().isEmpty()) {
				selectedAward.setFile(this.file);
				selectedAward.upload("imagemPrincipal");
			}
			
			this.selectedAward = new Award();
			this.addMessage("Sucesso", "Sua alteração foi realizada com sucesso.");
			this.setErrors(null);
			this.executeCommand("PF('editDialog').hide()");			
			return;
		} else {
			this.setErrors(validation);
			return;
		}
		
	}	
	
	public void findCode() {
		if (this.code != null && !this.code.isEmpty()) {
			personAward = paDAO.findByCode(this.code);
			if (personAward == null) {
				this.addErrorMessage("Ops", "Nenhum prêmio foi encontrado com este código");
			}
		} else {
			personAward = null;
			this.addErrorMessage("Ops", "É necessário inserir um token");
		}
		
	}
	
	public void finalizeAward() {
		if (personAward != null) {
			personAward.setFinalized(true);
			paDAO.merge(personAward);
			personAward = null;
			this.addMessage("Sucesso", "O prêmio de código #" + this.code.toUpperCase() + " foi finalizado.");
		}
	}

	public UserController getUserController() {
		return userController;
	}

	public void setUserController(UserController userController) {
		this.userController = userController;
	}

	public Award getAward() {
		return award;
	}

	public void setAward(Award award) {
		this.award = award;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<Award> getAwards() {
		return awards;
	}

	public void setAwards(List<Award> awards) {
		this.awards = awards;
	}

	public Award getSelectedAward() {
		return selectedAward;
	}

	public void setSelectedAward(Award selectedAward) {
		this.selectedAward = selectedAward;
		this.setErrors(null);
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public PersonAward getPersonAward() {
		return personAward;
	}

	public void setPersonAward(PersonAward personAward) {
		this.personAward = personAward;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Double getPoints() {
		return points;
	}

	public void setPoints(Double points) {
		this.points = points;
	}
	
}
