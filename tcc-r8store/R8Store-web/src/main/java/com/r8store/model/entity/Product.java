package com.r8store.model.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.primefaces.model.UploadedFile;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@Table(name="product")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Product extends MasterEntity {
	
	private static final long serialVersionUID = -5900789624862428184L;

	@ManyToOne
	@JsonIgnore
    @JoinColumn(name = "fk_store")
	private Store store;
	
	@OneToOne(mappedBy = "product")
	private Offer offer;
	
	@OneToOne(mappedBy = "product")
	@JsonIgnore
	private Award award;
	
	private String name;
	
	private String description;
	
	private Double value;
	
	private String link;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date exclusionDate;

	public Product(Long id, UploadedFile file, Store store, Offer offer, String description, String link, String name, Double value, Award award, Date exclusionDate) {
		super(id, file);
		this.store = store;
		this.offer = offer;
		this.description = description;
		this.link = link;
		this.value = value;
		this.name = name;
		this.award = award;
		this.exclusionDate = exclusionDate;
	}

	public Product() {
		super();
	}
	
	public Map<String, String> validate(boolean edit) {
		Map<String, String> errors = new HashMap<String,String>();
		
		if (this.getName() == null || this.getName().isEmpty()) {
			errors.put("name", "Não é possível inserir um nome vazio");
		}	
		
		if (this.getDescription() == null || this.getDescription().isEmpty()) {
			errors.put("description", "Não é possível inserir uma descrição vazia");
		}
		
		if (this.getValue() == null || this.getValue() == 0) {
			errors.put("value", "É necessário inserir um valor para o produto");
		}	
		
		return errors;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Award getAward() {
		return award;
	}

	public void setAward(Award award) {
		this.award = award;
	}

	public Date getExclusionDate() {
		return exclusionDate;
	}

	public void setExclusionDate(Date exclusionDate) {
		this.exclusionDate = exclusionDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((award == null) ? 0 : award.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((link == null) ? 0 : link.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((offer == null) ? 0 : offer.hashCode());
		result = prime * result + ((store == null) ? 0 : store.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		Product other = (Product) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (link == null) {
			if (other.link != null)
				return false;
		} else if (!link.equals(other.link))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (offer == null) {
			if (other.offer != null)
				return false;
		} else if (!offer.equals(other.offer))
			return false;
		if (store == null) {
			if (other.store != null)
				return false;
		} else if (!store.equals(other.store))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", description="
				+ description + ", value=" + value + ", link=" + link + "]";
	}
	
}
