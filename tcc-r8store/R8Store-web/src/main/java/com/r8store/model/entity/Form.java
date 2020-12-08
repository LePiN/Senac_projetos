package com.r8store.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.primefaces.model.UploadedFile;

import com.r8store.enums.Enum_Form;

@Entity
@Table(name="form")
public class Form extends MasterEntity {
	
	private static final long serialVersionUID = -1094177465329065592L;

	@ManyToOne
    @JoinColumn(name = "fk_store")
	private Store store;
	
	@OneToMany(mappedBy = "form")
    private List<Rating> ratings = new ArrayList<Rating>();
	
	@OneToMany(mappedBy = "form")
    private List<Question> questions = new ArrayList<Question>();
	
	private Enum_Form type;

	public Form(Long id, UploadedFile file, Store store, List<Rating> ratings, List<Question> questions,
			Enum_Form type) {
		super(id, file);
		this.store = store;
		this.ratings = ratings;
		this.questions = questions;
		this.type = type;
	}

	public Form() {
		super();
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public Enum_Form getType() {
		return type;
	}

	public void setType(Enum_Form type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((questions == null) ? 0 : questions.hashCode());
		result = prime * result + ((ratings == null) ? 0 : ratings.hashCode());
		result = prime * result + ((store == null) ? 0 : store.hashCode());
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
		Form other = (Form) obj;
		if (questions == null) {
			if (other.questions != null)
				return false;
		} else if (!questions.equals(other.questions))
			return false;
		if (ratings == null) {
			if (other.ratings != null)
				return false;
		} else if (!ratings.equals(other.ratings))
			return false;
		if (store == null) {
			if (other.store != null)
				return false;
		} else if (!store.equals(other.store))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Form [store=" + store + ", ratings=" + ratings + ", questions=" + questions + ", type=" + type + "]";
	}
	
}
