package com.r8store.model.entity;

import java.util.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.primefaces.model.UploadedFile;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="offer")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Offer extends MasterEntity {
	
	private static final long serialVersionUID = 343091189459084518L;

	@OneToOne
    @JoinColumn(name = "fk_product")
	private Product product;
	
	private String title;
	
	private Double discount;
	
	private String description;
	
	private Date date;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date exclusionDate;

	public Offer(Long id, UploadedFile file, Product product, String title, Double discount, String description, Date date, Date exclusionDate) {
		super(id, file);
		this.product = product;
		this.discount = discount;
		this.description = description;
		this.date = date;
		this.exclusionDate = date;
		this.title = title;
	}

	public Offer() {
		super();
	}
	
	public Map<String, String> validate() {
		Map<String, String> errors = new HashMap<String,String>();
		
		if (this.getDescription() == null || this.getDescription().isEmpty()) {
			errors.put("description", "Não é possível inserir uma descrição vazia");
		}
		
		if (this.getTitle() == null || this.getTitle().isEmpty()) {
			errors.put("title", "Não é possível inserir um titúlo vazia");
		}
		
		if (this.getDiscount() == null || this.getDiscount() <= 0) {
			errors.put("discount", "É necessário inserir um desconto válido");
		}
		
		if(this.getProduct() == null || this.getProduct().getId() == 0) {
			errors.put("product", "É necessário inserir um produto");
		}
		
		Date d = new Date(Calendar.getInstance().getTimeInMillis());
		
		if (this.getDate() == null || this.getDate().before(d)) {
			errors.put("date", "É necessário inserir uma data válida");
		}
		
		return errors;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getExclusionDate() {
		return exclusionDate;
	}

	public void setExclusionDate(Date exclusionDate) {
		this.exclusionDate = exclusionDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((discount == null) ? 0 : discount.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
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
		Offer other = (Offer) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (discount == null) {
			if (other.discount != null)
				return false;
		} else if (!discount.equals(other.discount))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} 
		return true;
	}

	@Override
	public String toString() {
		return "Offer [product=" + product + ", discount=" + discount + ", description=" + description + ", date="
				+ date + "]";
	}
	
}
