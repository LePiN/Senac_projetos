package com.r8store.model.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.primefaces.model.UploadedFile;

@Entity
@Table(name="user_post")
public class User_Post extends MasterEntity {
	
	private static final long serialVersionUID = -3042010351367397451L;

	@ManyToOne
    @JoinColumn(name = "fk_store")
	private Store store;
	
	@ManyToOne
    @JoinColumn(name = "fk_person")
	private Person person;
	
	private String comment;

	public User_Post(Long id, UploadedFile file, Store store, Person person, String comment) {
		super(id, file);
		this.store = store;
		this.person = person;
		this.comment = comment;
	}

	public User_Post() {
		super();
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + ((person == null) ? 0 : person.hashCode());
		result = prime * result + ((store == null) ? 0 : store.hashCode());
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
		User_Post other = (User_Post) obj;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
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
		return true;
	}

	@Override
	public String toString() {
		return "User_Post [store=" + store + ", person=" + person + ", comment=" + comment + "]";
	}
	
}
