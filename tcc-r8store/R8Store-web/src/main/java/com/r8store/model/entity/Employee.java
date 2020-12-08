package com.r8store.model.entity;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.validator.routines.EmailValidator;

import com.r8store.enums.Enum_Gender;
import com.r8store.model.dao.EmployeeDAO;

@Entity
@Table(name="employee")
public class Employee extends NaturalPerson {
	
	private static final long serialVersionUID = -8399995213312415711L;
	
	@OneToOne
	@JoinColumn(name = "fk_user")
	private User user;
	
	@OneToMany(mappedBy = "employee")
    private List<Rating> ratings = new ArrayList<Rating>();
	
	@OneToMany(mappedBy = "employee")
    private List<EmployeeStore> employeesStore = new ArrayList<EmployeeStore>();
	
	@OneToOne
    @JoinColumn(name = "fk_address")
	private Address address;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date exclusionDate;

	public Employee(String identifier, Date birthday, Enum_Gender gender, User user, List<Rating> ratings,
			Address address, Date exclusionDate) {
		super(identifier, birthday, gender);
		this.user = user;
		this.ratings = ratings;
		this.address = address;
		this.exclusionDate = exclusionDate;
	}
	
	public Employee() {
		super();
	}
	
	public Map<String, String> validate(boolean edit) {
		Map<String, String> errors = new HashMap<String,String>();
		EmployeeDAO eDAO = new EmployeeDAO();
		
		Employee editEmployee = new Employee();
		if(edit && this.getId() != null) {
			editEmployee = eDAO.findById(this.getId());
		}
		
		if (this.getName() == null || this.getName().isEmpty()) {
			errors.put("name", "Não é possível inserir um nome vazio");
		}
		
		Employee e = eDAO.findByIdentifier(this.getIdentifier());
		if (e != null) {
			if(edit && !editEmployee.getIdentifier().equals(e.getIdentifier())) {
				errors.put("identifierExists", "Este cpf já existe");
			}
			
			if(!edit) {
				errors.put("identifierExists", "Este cpf já existe");
			}
			
		}
		
		e = eDAO.findByEmail(this.getEmail());
		if (e != null) {
			if(edit && !editEmployee.getEmail().equals(e.getEmail())) {
				errors.put("emailExists", "Este email já existe");
			}
			
			if(!edit) {
				errors.put("emailExists", "Este email já existe");
			}			
		}
				
		if (!EmailValidator.getInstance().isValid(this.getEmail())) {
			errors.put("email", "Email inválido ou inexistente");
		}
		
		if(this.getType() == null || this.getType().getName().isEmpty()) {
			errors.put("type", "É necessário informar o tipo do funcionário");
		}
		
		if(this.getIdentifier() == null || this.getIdentifier().isEmpty()) {
			errors.put("identifier", "É necessário informar o cpf");
		}
		
		return errors;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getExclusionDate() {
		return exclusionDate;
	}

	public void setExclusionDate(Date exclusionDate) {
		this.exclusionDate = exclusionDate;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<EmployeeStore> getEmployeesStore() {
		return employeesStore;
	}

	public void setEmployeesStore(List<EmployeeStore> employeesStore) {
		this.employeesStore = employeesStore;
	}
	
	public Store getActualStore() {
		for(EmployeeStore eS : this.getEmployeesStore()) {
			if(eS.getExclusionDate() == null) {
				return eS.getStore();
			}
		}
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((employeesStore == null) ? 0 : employeesStore.hashCode());
		result = prime * result + ((exclusionDate == null) ? 0 : exclusionDate.hashCode());
		result = prime * result + ((ratings == null) ? 0 : ratings.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Employee other = (Employee) obj;
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
		if (ratings == null) {
			if (other.ratings != null)
				return false;
		} else if (!ratings.equals(other.ratings))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee [user=" + user + ", ratings=" + ratings + ", employeesStore=" + employeesStore + ", address="
				+ address + ", exclusionDate=" + exclusionDate + "]";
	}
	
}
