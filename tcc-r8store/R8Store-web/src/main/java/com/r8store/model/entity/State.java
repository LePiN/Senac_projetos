package com.r8store.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.primefaces.model.UploadedFile;

@Entity
@Table(name="state")
public class State extends MasterEntity {
	
	private static final long serialVersionUID = -4146920798422122723L;

	@OneToMany(mappedBy = "state")
    private List<City> cities = new ArrayList<City>();
	
	@ManyToOne
    @JoinColumn(name = "fk_country")
	private Country country;
	
	private String name;
	
	private String initials;

	public State(Long id, UploadedFile file, List<City> cities, Country country, String name, String initials) {
		super(id, file);
		this.cities = cities;
		this.country = country;
		this.name = name;
		this.initials = initials;
	}

	public State() {
		super();
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
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
		result = prime * result + ((cities == null) ? 0 : cities.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((initials == null) ? 0 : initials.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		State other = (State) obj;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
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
		return true;
	}

	@Override
	public String toString() {
		return "State [country=" + country + ", name=" + name + ", initials=" + initials + "]";
	}
	
}
