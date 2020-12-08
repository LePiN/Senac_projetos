package com.r8store.model.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.primefaces.model.UploadedFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="rating")
public class Rating extends MasterEntity {
	
	private static final long serialVersionUID = -1744235665708187822L;

	@ManyToOne
	@JsonIgnore
    @JoinColumn(name = "fk_store")
	private Store store;
	
	@ManyToOne
	@JsonIgnore
    @JoinColumn(name = "fk_form")
	private Form form;
	
	@ManyToOne
	@JsonIgnore
    @JoinColumn(name = "fk_employee")
	private Employee employee;
	
	@ManyToOne
	@JsonIgnore
    @JoinColumn(name = "fk_person")
	private Person person;
	
	private String token;
	
	private String message;
	
	private Double total;
	
	private Double points;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	public Rating(Long id, UploadedFile file, Store store, Form form, Employee employee, Person person, String token,
			String message, Double total, Double points, Date createdAt) {
		super(id, file);
		this.store = store;
		this.form = form;
		this.employee = employee;
		this.person = person;
		this.message = message;
		this.token = token;
		this.total = total;
		this.points = points;
		this.createdAt = createdAt;
	}

	public Rating() {
		super();
		this.createdAt = new Date();
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Form getForm() {
		return form;
	}

	public void setForm(Form form) {
		this.form = form;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Double getPoints() {
		return points;
	}

	public void setPoints(Double points) {
		this.points = points;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + ((form == null) ? 0 : form.hashCode());
		result = prime * result + ((person == null) ? 0 : person.hashCode());
		result = prime * result + ((store == null) ? 0 : store.hashCode());
		result = prime * result + ((token == null) ? 0 : token.hashCode());
		result = prime * result + ((total == null) ? 0 : total.hashCode());
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
		Rating other = (Rating) obj;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		if (form == null) {
			if (other.form != null)
				return false;
		} else if (!form.equals(other.form))
			return false;
		if (person == null) {
			if (other.person != null)
				return false;
		} else if (!person.equals(other.person))
			return false;
		if (store == null) {
			if (other.store != null)
				return false;
		} else if (!store.equals(other.store))
			return false;
		if (token == null) {
			if (other.token != null)
				return false;
		} else if (!token.equals(other.token))
			return false;
		if (total == null) {
			if (other.total != null)
				return false;
		} else if (!total.equals(other.total))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Rating [store=" + store + ", form=" + form + ", employee=" + employee + ", person=" + person
				+ ", token=" + token + ", total=" + total + "]";
	}
	
}
