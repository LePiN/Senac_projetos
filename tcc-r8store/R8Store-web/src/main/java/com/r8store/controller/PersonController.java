package com.r8store.controller;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.omnifaces.cdi.ViewScoped;

import com.r8store.enums.Enum_Type_User;
import com.r8store.util.Cryptography;

import com.r8store.model.dao.PersonDAO;
import com.r8store.model.dao.UserDAO;
import com.r8store.model.entity.Person;
import com.r8store.model.entity.User;

@Named
@ViewScoped
public class PersonController extends Controller implements Serializable {

	private static final long serialVersionUID = -2796751255944731103L;

	@Inject
	private UserController userController;
	
	private Person person;
	private User user;
	private String confPassword;
	
	@Inject
	private UserDAO uDAO;
	
	@Inject
	private PersonDAO pDAO;
	
	
	@PostConstruct
	public void init() {
		person = new Person();
		user = new User();		
	}
	
	public void registerAction() {
		try {
			
			byte[] salt = Cryptography.getSalt();
			user.setSalt(Base64.getEncoder().encodeToString(salt));
			user.setPassword(Cryptography.getSHA256(user.getPassword(), salt));
			user.setId(0l);
			
			user = uDAO.merge(user);
			person.setUser(user);
			person.setType(Enum_Type_User.PERSON);
			
			person = pDAO.merge(person);
			
			userController.setMeta(person);
			
			user.setPerson(person);
			
			FacesContext facesContext = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
			session.setAttribute("user", user);
			userController.setUsuarioLogado(user);
			userController.getMeta().setType(Enum_Type_User.PERSON);
			
			this.redirect("user/store/shoppings.xhtml");
			
		} catch (NoSuchAlgorithmException e) {			
			e.printStackTrace();
		}
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getConfPassword() {
		return confPassword;
	}

	public void setConfPassword(String confPassword) {
		this.confPassword = confPassword;
	}

	public UserController getUserController() {
		return userController;
	}

	public void setUserController(UserController userController) {
		this.userController = userController;
	}
	
}

