package com.r8store.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.primefaces.model.UploadedFile;

@Entity
@Table(name="country")
public class Country extends MasterEntity {
	
	private static final long serialVersionUID = -2774984461590836187L;

	@OneToMany(mappedBy = "country")
    private List<State> states = new ArrayList<State>();
	
	private String name;
	
	private String initials;

	public Country(Long id, UploadedFile file, List<State> states, String name, String initials) {
		super(id, file);
		this.states = states;
		this.name = name;
		this.initials = initials;
	}

	public Country() {
		super();
	}

	public List<State> getStates() {
		return states;
	}

	public void setStates(List<State> states) {
		this.states = states;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInitials() {
		return initials;
	}

	public void setInitials(String initials) {
		this.initials = initials;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((initials == null) ? 0 : initials.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((states == null) ? 0 : states.hashCode());
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
		Country other = (Country) obj;
		if (initials == null) {
			if (other.initials != null)
				return false;
		} else if (!initials.equals(other.initials))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (states == null) {
			if (other.states != null)
				return false;
		} else if (!states.equals(other.states))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Country [states=" + states + ", name=" + name + ", initials=" + initials + "]";
	}
	
}
