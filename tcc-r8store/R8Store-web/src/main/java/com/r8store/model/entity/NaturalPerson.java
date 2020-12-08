package com.r8store.model.entity;



import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

import com.r8store.enums.Enum_Gender;

@MappedSuperclass
public abstract class NaturalPerson extends SessionEntity {

	private static final long serialVersionUID = 9144342926702460801L;

	private String identifier;
	
	private Date birthday;
	
	@Enumerated(EnumType.STRING)
	private Enum_Gender gender;

	public NaturalPerson(String identifier, Date birthday, Enum_Gender gender) {
		super();
		this.identifier = identifier;
		this.birthday = birthday;
		this.gender = gender;
	}
	
	public NaturalPerson() {
		super();
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Enum_Gender getGender() {
		return gender;
	}

	public void setGender(Enum_Gender gender) {
		this.gender = gender;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((identifier == null) ? 0 : identifier.hashCode());
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
		NaturalPerson other = (NaturalPerson) obj;
		if (birthday == null) {
			if (other.birthday != null)
				return false;
		} else if (!birthday.equals(other.birthday))
			return false;
		if (gender != other.gender)
			return false;
		if (identifier == null) {
			if (other.identifier != null)
				return false;
		} else if (!identifier.equals(other.identifier))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "NaturalPerson [identifier=" + identifier + ", birthday=" + birthday + ", gender=" + gender + "]";
	}
	
}
