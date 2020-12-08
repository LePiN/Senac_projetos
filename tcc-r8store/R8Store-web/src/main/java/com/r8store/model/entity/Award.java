package com.r8store.model.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.primefaces.model.UploadedFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="award")
public class Award extends MasterEntity {
	
	private static final long serialVersionUID = 8881218869030894328L;

	@ManyToOne
    @JoinColumn(name = "fk_store")
	private Store store;	
	
	@OneToOne
    @JoinColumn(name = "fk_product")
	private Product product;
	
	private String name;
	
	private String description;
	
	private double points;
	
	private int quantity;
	
	@OneToMany(mappedBy = "award")
    @JsonIgnore
    private List<PersonAward> personAward = new ArrayList<PersonAward>();
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date exclusionDate;

	public Award(Long id, UploadedFile file, Store store, double points, int quantity, Date date, Product product, String name, String description, Date exclusionDate, List<PersonAward> personAward) {
		super(id, file);
		this.store = store;
		this.points = points;
		this.quantity = quantity;
		this.date = date;
		this.product = product;
		this.personAward = personAward;
		this.name = name;
		this.description = description;
		this.exclusionDate = exclusionDate;
	}

	public Award() {
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
		
		if (this.getPoints() <= 0) {
			errors.put("points", "É necessário inserir um custo em pontos");
		}	
		
		if (this.getQuantity() <= 0) {
			errors.put("quantity", "É necessário inserir uma quantidade de prêmios");
		}	
		
		if(this.getProduct() == null || this.getProduct().getId() == 0) {
			errors.put("product", "É necessário inserir um produto");
		}
		
		if(this.getDate() == null) {
			errors.put("date", "É necessário inserir uma data limite");
		}
		
		return errors;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public double getPoints() {
		return points;
	}

	public void setPoints(double points) {
		this.points = points;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getExclusionDate() {
		return exclusionDate;
	}

	public void setExclusionDate(Date exclusionDate) {
		this.exclusionDate = exclusionDate;
	}

	public List<PersonAward> getPersonAward() {
		return personAward;
	}

	public void setPersonAward(List<PersonAward> personAward) {
		this.personAward = personAward;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(points);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + quantity;
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
		Award other = (Award) obj;
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
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(points) != Double.doubleToLongBits(other.points))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (quantity != other.quantity)
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
		return "Award [store=" + store + ", product=" + product + ", name=" + name + ", description=" + description
				+ ", points=" + points + ", quantity=" + quantity + ", date=" + date + "]";
	}
	
}
