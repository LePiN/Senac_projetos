package com.r8store.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;

import com.r8store.enums.Enum_Pages;
import com.r8store.enums.Enum_Type_User;

import com.r8store.model.entity.SessionEntity;

public class Controller {
	
	private Map<String, String> errors = new HashMap<String,String>();
	
	public void forward(String path) {
		try {
			FacesContext.getCurrentInstance().getExternalContext().dispatch(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void redirect(String path) {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void executeCommand(String command) {
		RequestContext.getCurrentInstance().execute(command);;
	}
	
	public boolean verifyPageAccess(SessionEntity meta, Enum_Pages page) {
		
		boolean access = false;
		
		if (meta != null && meta.getType() != null) {		
			for (String s : page.getPermissions()) {
				if (Enum_Type_User.valueOf(s).equals(meta.getType())) {
					access = true;
					break;
				}
			}
			 
		} else {
			this.redirect(this.getAbsolutePath() + "/login.xhtml");	
			return false;
		}		
		
		if (!access) {			
			this.redirect(this.getAbsolutePath() + "/index.xhtml");			
		}
		
		return access;
	}
	
	public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
	
	public void addErrorMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
	
	public String getAbsolutePath() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		return request.getContextPath();
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}
	
}

