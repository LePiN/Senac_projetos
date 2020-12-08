package com.r8store.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.primefaces.model.UploadedFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="city")
public class City extends MasterEntity {
	
	private static final long serialVersionUID = -5721403690726974272L;

	@OneToMany(mappedBy = "city")
	@JsonIgnore
    private List<Address> addresses = new ArrayList<Address>();
	
	@ManyToOne
	@JsonIgnore
    @JoinColumn(name = "fk_state")
	private State state;
	
	private String name;

	public City(Long id, UploadedFile file, List<Address> addresses, State state, String name) {
		super(id, file);
		this.addresses = addresses;
		this.state = state;
		this.name = name;
	}

	public City() {
		super();
	}
	
	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((addresses == null) ? 0 : addresses.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
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
		City other = (City) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "City [state=" + state + ", name=" + name + "]";
	}
	
}
