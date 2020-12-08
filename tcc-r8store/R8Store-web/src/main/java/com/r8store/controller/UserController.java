package com.r8store.controller;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import com.r8store.enums.Enum_Type_User;

import com.r8store.model.entity.SessionEntity;
import com.r8store.model.entity.User;


@Named
@SessionScoped
public class UserController extends Controller implements Serializable {

	private static final long serialVersionUID = 4177389879455829371L;
	
	private User usuarioLogado;
	private SessionEntity meta;
	private Long storeId;
	
	@PostConstruct
	public void init() {
		usuarioLogado = new User();
	}
	
	public boolean permission(ArrayList<String> permissions) {
		
		if (this.meta != null) {
			for(String s : permissions) {			
				if (Enum_Type_User.valueOf(s).equals(this.meta.getType())) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public void logout() {
		FacesContext fc = FacesContext.getCurrentInstance();   
		HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);   
		session.invalidate();
		this.redirect(fc.getExternalContext().getRequestContextPath() + "/index.xhtml");
	}

	public User getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(User usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public SessionEntity getMeta() {
		return meta;
	}

	public void setMeta(SessionEntity meta) {
		this.meta = meta;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
		
}

