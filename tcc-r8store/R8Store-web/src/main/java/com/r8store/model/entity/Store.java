package com.r8store.model.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.validator.routines.EmailValidator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.r8store.enums.Enum_Type_User;
import com.r8store.model.dao.StoreDAO;


@Entity
@Table(name="store")
public class Store extends JuridicalPerson {
	
	private static final long serialVersionUID = -4776809099084220426L;

	@OneToOne
	@JsonIgnore
	@JoinColumn(name = "fk_user")
	private User user;
	
	@OneToMany(mappedBy = "store")
	@JsonIgnore
    private List<User_Post> userPosts = new ArrayList<User_Post>();
    
    @OneToMany(mappedBy = "store")
    @JsonIgnore
    private List<Product> products = new ArrayList<Product>();
    
    @OneToMany(mappedBy = "store")
    @JsonIgnore
    private List<Rating> ratings = new ArrayList<Rating>();
    
    @OneToMany(mappedBy = "store")
    @JsonIgnore
    private List<Form> forms = new ArrayList<Form>();
    
    @OneToMany(mappedBy = "store")
    @JsonIgnore
    private List<Award> awards = new ArrayList<Award>();
    
    @OneToMany(mappedBy = "store")
    @JsonIgnore
    private List<Point> points = new ArrayList<Point>();
    
    @ManyToOne(cascade={CascadeType.ALL})
    @JsonIgnore
	@JoinColumn(name="shopping_id")
	private Store shopping;
    
    @OneToMany(mappedBy = "store")
    @JsonIgnore
    private List<EmployeeStore> employeesStore = new ArrayList<EmployeeStore>();

	@OneToMany(mappedBy="shopping")
	@JsonIgnore
	private List<Store> stores = new ArrayList<Store>();
	
	@OneToOne
    @JoinColumn(name = "fk_address")
	private Address address;
	
	private String description;
	
	private Float rating;
	
	private String localAddress;
	
	private String coordinateX;
	
	private String coordinateY;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date exclusionDate;

	public Store(String cnpj, String inscricaoEstadual, User user, List<User_Post> userPosts, List<Product> products,
			List<Rating> ratings, List<Form> forms, List<Award> awards, Store shopping, String description,
			List<Store> stores, String coordinateX, String coordinateY, Float rating, Date exclusionDate, String localAddress) {
		super(cnpj, inscricaoEstadual);
		this.user = user;
		this.userPosts = userPosts;
		this.products = products;
		this.ratings = ratings;
		this.description = description;
		this.forms = forms;
		this.awards = awards;
		this.localAddress = localAddress;
		this.shopping = shopping;
		this.stores = stores;
		this.coordinateX = coordinateX;
		this.coordinateY = coordinateY;
		this.rating = rating;
		this.exclusionDate = exclusionDate;
	}

	public Store() {
		super();
	}
	
	public Long getShoppingId() {
		if (this.shopping != null) {
			return this.shopping.getId();
		} else {
			return this.getId();
		}
	}
	
	public Map<String, String> validate(boolean edit) {
		Map<String, String> errors = new HashMap<String,String>();
		StoreDAO sDAO = new StoreDAO();	
		
		Store editStore = new Store();
		if(edit) {
			editStore = sDAO.findById(this.getId());
		}
		
		Store s = sDAO.findByCnpj(this.getCnpj());
		if (s != null) {
			if(edit && !editStore.getCnpj().equals(s.getCnpj())) {
				errors.put("cnpjExists", "Este cnpj já existe");
			}
			
			if(!edit) {
				errors.put("cnpjExists", "Este cnpj já existe");
			}			
		}
		
		s = sDAO.findByEmail(this.getEmail());
		if (s != null) {
			if(edit && !editStore.getEmail().equals(s.getEmail())) {
				errors.put("emailExists", "Este email já existe");
			}
			
			if(!edit) {
				errors.put("emailExists", "Este email já existe");
			}			
		}
		
		s = sDAO.findByInscricao(this.getInscricaoEstadual());
		if (s != null) {
			if(edit && !editStore.getInscricaoEstadual().equals(s.getInscricaoEstadual())) {
				errors.put("inscricaoExists", "Esta inscrição estadual já existe");
			}
			
			if(!edit) {
				errors.put("inscricaoExists", "Esta inscrição estadual já existe");
			}
			
		}			
		
		if (this.getName() == null || this.getName().isEmpty()) {
			errors.put("name", "Não é possível inserir um nome vazio");
		}
		
		if (this.getInscricaoEstadual() == null || this.getInscricaoEstadual().isEmpty()) {
			errors.put("inscricao", "É necessário inserir a inscrição estadual");
		}
		
		if (this.getCnpj() == null || this.getCnpj().isEmpty()) {
			errors.put("cnpj", "É necessário inserir um cnpj");
		}
		
		if (!EmailValidator.getInstance().isValid(this.getEmail())) {
			errors.put("email", "Email inválido ou inexistente");
		}
		
		if ((this.getCoordinateX() == null || this.getCoordinateX().isEmpty()) && Enum_Type_User.SHOPPING.equals(this.getType()) && !edit) {
			errors.put("geolocation", "É necessário inserir um ponto no mapa");
		}
		
		return errors;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User_Post> getUserPosts() {
		return userPosts;
	}

	public void setUserPosts(List<User_Post> userPosts) {
		this.userPosts = userPosts;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	public List<Form> getForms() {
		return forms;
	}

	public void setForms(List<Form> forms) {
		this.forms = forms;
	}

	public List<Award> getAwards() {
		return awards;
	}

	public void setAwards(List<Award> awards) {
		this.awards = awards;
	}

	public Store getShopping() {
		return shopping;
	}

	public void setShopping(Store shopping) {
		this.shopping = shopping;
	}

	public List<Store> getStores() {
		return stores;
	}

	public void setStores(List<Store> stores) {
		this.stores = stores;
	}

	public String getCoordinateX() {
		return coordinateX;
	}

	public void setCoordinateX(String coordinateX) {
		this.coordinateX = coordinateX;
	}

	public String getCoordinateY() {
		return coordinateY;
	}

	public void setCoordinateY(String coordinateY) {
		this.coordinateY = coordinateY;
	}

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}	
	
	public Date getExclusionDate() {
		return exclusionDate;
	}

	public void setExclusionDate(Date exclusionDate) {
		this.exclusionDate = exclusionDate;
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Point> getPoints() {
		return points;
	}

	public void setPoints(List<Point> points) {
		this.points = points;
	}

	public List<EmployeeStore> getEmployeesStore() {
		return employeesStore;
	}

	public void setEmployeesStore(List<EmployeeStore> employeesStore) {
		this.employeesStore = employeesStore;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocalAddress() {
		return localAddress;
	}

	public void setLocalAddress(String localAddress) {
		this.localAddress = localAddress;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((awards == null) ? 0 : awards.hashCode());
		result = prime * result + ((coordinateX == null) ? 0 : coordinateX.hashCode());
		result = prime * result + ((coordinateY == null) ? 0 : coordinateY.hashCode());
		result = prime * result + ((employeesStore == null) ? 0 : employeesStore.hashCode());
		result = prime * result + ((exclusionDate == null) ? 0 : exclusionDate.hashCode());
		result = prime * result + ((forms == null) ? 0 : forms.hashCode());
		result = prime * result + ((points == null) ? 0 : points.hashCode());
		result = prime * result + ((products == null) ? 0 : products.hashCode());
		result = prime * result + ((rating == null) ? 0 : rating.hashCode());
		result = prime * result + ((ratings == null) ? 0 : ratings.hashCode());
		result = prime * result + ((shopping == null) ? 0 : shopping.hashCode());
		result = prime * result + ((stores == null) ? 0 : stores.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + ((userPosts == null) ? 0 : userPosts.hashCode());
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
		Store other = (Store) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (awards == null) {
			if (other.awards != null)
				return false;
		} else if (!awards.equals(other.awards))
			return false;
		if (coordinateX == null) {
			if (other.coordinateX != null)
				return false;
		} else if (!coordinateX.equals(other.coordinateX))
			return false;
		if (coordinateY == null) {
			if (other.coordinateY != null)
				return false;
		} else if (!coordinateY.equals(other.coordinateY))
			return false;
		if (employeesStore == null) {
			if (other.employeesStore != null)
				return false;
		} else if (!employeesStore.equals(other.employeesStore))
			return false;
		if (exclusionDate == null) {
			if (other.exclusionDate != null)
				return false;
		} else if (!exclusionDate.equals(other.exclusionDate))
			return false;
		if (forms == null) {
			if (other.forms != null)
				return false;
		} else if (!forms.equals(other.forms))
			return false;
		if (points == null) {
			if (other.points != null)
				return false;
		} else if (!points.equals(other.points))
			return false;
		if (products == null) {
			if (other.products != null)
				return false;
		} else if (!products.equals(other.products))
			return false;
		if (rating == null) {
			if (other.rating != null)
				return false;
		} else if (!rating.equals(other.rating))
			return false;
		if (ratings == null) {
			if (other.ratings != null)
				return false;
		} else if (!ratings.equals(other.ratings))
			return false;
		if (shopping == null) {
			if (other.shopping != null)
				return false;
		} else if (!shopping.equals(other.shopping))
			return false;
		if (stores == null) {
			if (other.stores != null)
				return false;
		} else if (!stores.equals(other.stores))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (userPosts == null) {
			if (other.userPosts != null)
				return false;
		} else if (!userPosts.equals(other.userPosts))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Store [user=" + user + ", userPosts=" + userPosts + ", products=" + products + ", ratings=" + ratings
				+ ", forms=" + forms + ", awards=" + awards + ", points=" + points + ", shopping=" + shopping
				+ ", employeesStore=" + employeesStore + ", stores=" + stores + ", address=" + address + ", rating="
				+ rating + ", coordinateX=" + coordinateX + ", coordinateY=" + coordinateY + ", exclusionDate="
				+ exclusionDate + "]";
	}


}
