package com.r8store.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Base64;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.omnifaces.cdi.ViewScoped;

import com.r8store.enums.Enum_Type_User;
import com.r8store.util.Cryptography;

import com.r8store.model.dao.EmployeeDAO;
import com.r8store.model.dao.PersonDAO;
import com.r8store.model.dao.StoreDAO;
import com.r8store.model.dao.UserDAO;
import com.r8store.model.entity.Employee;
import com.r8store.model.entity.Person;
import com.r8store.model.entity.Store;
import com.r8store.model.entity.User;

@Named
@ViewScoped
public class LoginController extends Controller implements Serializable {

	private static final long serialVersionUID = 4177389879455829371L;
	
	private User usuarioLogado;
	
	@Inject
	private UserController userController;
	
	@Inject
	private UserDAO uDAO;
	
	@Inject
	private PersonDAO pDAO;
	
	@Inject
	private StoreDAO sDAO;
	
	@Inject
	private EmployeeDAO eDAO;
	
	@PostConstruct
	public void init() {
		usuarioLogado = new User();
	}
	
	public void loginPersonAction() {
		
		User u = uDAO.findByUsername(this.usuarioLogado.getLogin());
		
		if (u != null) {
			
			byte[] salt = Base64.getDecoder().decode(u.getSalt());
	        this.usuarioLogado.setPassword(Cryptography.getSHA256(this.usuarioLogado.getPassword(), salt)); 
			
			if (u.getPassword().equals(this.usuarioLogado.getPassword())) {			
				
				Person person = pDAO.findByUser(u.getId());
				u.setPerson(person);
				
				if (person == null) {
					this.setErrors(usuarioLogado.validateLogin());
					return;
				}
				
				FacesContext facesContext = FacesContext.getCurrentInstance();
				HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
				session.setAttribute("user", u);
				
				userController.setMeta(person);
				this.userController.setUsuarioLogado(u);			
				this.redirect("user/store/shoppings.xhtml");
			} else {
				this.setErrors(usuarioLogado.validateLogin());
				return;
			}
			
		} else {
			this.setErrors(usuarioLogado.validateLogin());
			return;
		}
		
	}
	
	public void loginEmployeeAction() {
		
		User u = uDAO.findByUsername(this.usuarioLogado.getLogin());
		
		if (u != null) {
			
			byte[] salt = Base64.getDecoder().decode(u.getSalt());
	        this.usuarioLogado.setPassword(Cryptography.getSHA256(this.usuarioLogado.getPassword(), salt)); 
			
			if (u.getPassword().equals(this.usuarioLogado.getPassword())) {				
				Employee employee = eDAO.findByUser(u.getId());
				
				if (employee == null) {
					Store store = sDAO.findByUser(u.getId());
					
					if (store == null) {
						this.setErrors(usuarioLogado.validateLogin());
						return;
					}
					
					u.setStore(store);
					userController.setMeta(store);
					userController.setStoreId(store.getId());
				} else {
					u.setEmployee(employee);
					userController.setMeta(employee);
					Store s = employee.getActualStore();
					if (s != null) {
						userController.setStoreId(s.getId());
					}					
				}				
				
				this.userController.setUsuarioLogado(u);
				this.redirect("admin/dashboard.xhtml");
			} else {
				this.setErrors(usuarioLogado.validateLogin());
				return;
			}
			
		} else {
			this.setErrors(usuarioLogado.validateLogin());
			return;
		}
		
	}
	
	public boolean permission(ArrayList<String> permissions) {
		
		if (this.userController.getMeta() != null) {
			for(String s : permissions) {			
				if (Enum_Type_User.valueOf(s).equals(this.userController.getMeta().getType())) {
					return true;
				}
			}
		}
		
		return false;
	}

	public User getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(User usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public UserController getUserController() {
		return userController;
	}

	public void setUserController(UserController userController) {
		this.userController = userController;
	}

	
		
}


