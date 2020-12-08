package com.r8store.model.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.primefaces.model.UploadedFile;

import com.r8store.model.dao.UserDAO;

@Entity
@Table(name="user")
public class User extends MasterEntity {
	
	@OneToOne(mappedBy = "user")
	private Person person;
	
	@OneToOne(mappedBy = "user")
	private Employee employee;
	
	@OneToOne(mappedBy = "user")
	private Store store;
	
	private String login;
	
	private String password;
	
	@Transient
	private String confPassword;
	
	private String salt;

	public User(Long id, UploadedFile file, Person person, Employee employee, Store store, String login,
			String password, String confPassword, String salt) {
		super(id, file);
		this.person = person;
		this.employee = employee;
		this.store = store;
		this.login = login;
		this.password = password;
		this.confPassword = confPassword;
		this.salt = salt;
	}

	public User() {
		super();
	}
	
	public Map<String, String> validateLogin() {
		Map<String, String> errors = new HashMap<String,String>();
		errors.put("login", "Usuário ou senha incorretos");
		
		return errors;
	}
	
	public Map<String, String> validateRegister(boolean edit) {
		Map<String, String> errors = new HashMap<String,String>();
		UserDAO uDAO = new UserDAO();
		User editUser = null;
		try {
			editUser = uDAO.findById(this.getId());
		} catch(Exception e) {
			System.out.println("Nenhuma entidade foi encontrada");
		}
		
		if (this.getLogin() == null || this.getLogin().isEmpty()) {
			errors.put("login", "Não é possível inserir um login vazio");
		} else {
			User u = uDAO.findByUsername(this.login);
			if (u != null) {
				
				if(edit && editUser != null && !editUser.getLogin().equals(u.getLogin())) {
					errors.put("userExists", "Este usuário já existe");
				}
				
				if(!edit) {
					errors.put("userExists", "Este usuário já existe");
				}
				
			}
		}
		
		if (this.getPassword() == null || this.getPassword().isEmpty()) {
			errors.put("password", "Não é possível inserir uma senha vazia");
		}
		
		if (!this.getPassword().equals(this.confPassword)) {
			errors.put("confirmation", "As senhas precisam ser iguais");
		}
		
		return errors;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfPassword() {
		return confPassword;
	}

	public void setConfPassword(String confPassword) {
		this.confPassword = confPassword;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((confPassword == null) ? 0 : confPassword.hashCode());
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((person == null) ? 0 : person.hashCode());
		result = prime * result + ((salt == null) ? 0 : salt.hashCode());
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
		User other = (User) obj;
		if (confPassword == null) {
			if (other.confPassword != null)
				return false;
		} else if (!confPassword.equals(other.confPassword))
			return false;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (person == null) {
			if (other.person != null)
				return false;
		} else if (!person.equals(other.person))
			return false;
		if (salt == null) {
			if (other.salt != null)
				return false;
		} else if (!salt.equals(other.salt))
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
		return "User [person=" + person + ", employee=" + employee + ", store=" + store + ", login=" + login
				+ ", password=" + password + ", confPassword=" + confPassword + ", salt=" + salt + "]";
	}
	
}
