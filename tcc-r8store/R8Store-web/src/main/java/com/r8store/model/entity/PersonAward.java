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
@Table(name="person_award")
public class PersonAward extends MasterEntity {
	
	private static final long serialVersionUID = 8485609535380999645L;

	@ManyToOne
	@JsonIgnore
    @JoinColumn(name = "fk_person")
	private Person person;
	
	@ManyToOne
    @JoinColumn(name = "fk_coupon")
	private Award award;
	
	private String code;
	
	private boolean finalized;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date adquiredIn;

	public PersonAward(Long id, UploadedFile file, Person person, Award award, String code, boolean finalized, Date adquiredIn) {
		super(id, file);
		this.person = person;
		this.award = award;
		this.adquiredIn = adquiredIn;
		this.code = code;
		this.finalized = finalized;
	}

	public PersonAward() {
		super();
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Award getAward() {
		return this.award;
	}

	public void setAward(Award award) {
		this.award = award;
	}

	public Date getAdquiredIn() {
		return adquiredIn;
	}

	public void setAdquiredIn(Date adquiredIn) {
		this.adquiredIn = adquiredIn;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isFinalized() {
		return finalized;
	}

	public void setFinalized(boolean finalized) {
		this.finalized = finalized;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((award == null) ? 0 : award.hashCode());
		result = prime * result + ((person == null) ? 0 : person.hashCode());
		result = prime * result + ((adquiredIn == null) ? 0 : adquiredIn.hashCode());
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
		PersonAward other = (PersonAward) obj;
		if (award == null) {
			if (other.award != null)
				return false;
		} else if (!award.equals(other.award))
			return false;
		if (person == null) {
			if (other.person != null)
				return false;
		} else if (!person.equals(other.person))
			return false;
		if (adquiredIn == null) {
			if (other.adquiredIn != null)
				return false;
		} else if (!adquiredIn.equals(other.adquiredIn))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Person_Coupon [person=" + person + ", award=" + award + ", until=" + adquiredIn + "]";
	}
	
}
