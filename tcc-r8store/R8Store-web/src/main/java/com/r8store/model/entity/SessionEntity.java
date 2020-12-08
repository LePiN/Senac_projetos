package com.r8store.model.entity;

import java.io.Serializable;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

import com.r8store.enums.Enum_Type_User;

@MappedSuperclass
public abstract class SessionEntity extends MasterEntity implements Serializable {
	
	private static final long serialVersionUID = 6384952216265398588L;
	
	@Enumerated(EnumType.STRING)
	private Enum_Type_User type;
	
	private String name;
	
	private String email;
	
	private String tel;
	
	private String cel;

	public SessionEntity() {
		super();
	}

	public Enum_Type_User getType() {
		return type;
	}

	public void setType(Enum_Type_User type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getCel() {
		return cel;
	}

	public void setCel(String cel) {
		this.cel = cel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cel == null) ? 0 : cel.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((tel == null) ? 0 : tel.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		SessionEntity other = (SessionEntity) obj;
		if (cel == null) {
			if (other.cel != null)
				return false;
		} else if (!cel.equals(other.cel))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (tel == null) {
			if (other.tel != null)
				return false;
		} else if (!tel.equals(other.tel))
			return false;
		if (type != other.type)
			return false;
		return true;
	}
	
}
