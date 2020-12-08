package com.r8store.controller;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.json.JSONException;
import org.json.JSONObject;
import org.omnifaces.cdi.ViewScoped;

import com.r8store.enums.Enum_Gender;
import com.r8store.enums.Enum_Pages;
import com.r8store.enums.Enum_Type_User;
import com.r8store.model.dao.AddressDAO;
import com.r8store.model.dao.CityDAO;
import com.r8store.model.dao.EmployeeDAO;
import com.r8store.model.dao.EmployeeStoreDAO;
import com.r8store.model.dao.StateDAO;
import com.r8store.model.dao.UserDAO;
import com.r8store.model.entity.Address;
import com.r8store.model.entity.City;
import com.r8store.model.entity.Employee;
import com.r8store.model.entity.EmployeeStore;
import com.r8store.model.entity.State;
import com.r8store.model.entity.Store;
import com.r8store.model.entity.User;
import com.r8store.util.Cryptography;
import com.r8store.util.HttpAddress;

@Named
@ViewScoped
public class EmployeeController extends Controller implements Serializable {

	private static final long serialVersionUID = 7908352227461797198L;
	
	private Employee employee;
	private List<Employee> employees;
	private User user;
	private List<Enum_Type_User> types;
	private String confSenha;
	private Employee selectedEmployee;
	private City city;
	private State state;
	private List<City> cities;
	private List<State> states;
	private Address address;
	
	@Inject
	private UserController userController;
	
	@Inject
	private UserDAO uDAO;
	
	@Inject
	private EmployeeDAO eDAO;
	
	@Inject
	private StateDAO stDAO;
	
	@Inject
	private CityDAO cDAO;
	
	@Inject
	private AddressDAO aDAO;
	
	@Inject
	private EmployeeStoreDAO esDAO;
	
	@PostConstruct
	public void init() {
		employee = new Employee();
		selectedEmployee = new Employee(); 
		employees = new ArrayList<>();
		user = new User();
		employee.setUser(user);
		address = new Address();
		employee.setAddress(address);
		states = new ArrayList<>();
		cities = new ArrayList<>();
	}

	public Employee getEmployee() {
		return employee;
	}
	
	public void registerAction() {
		try {
			Map<String, String> validation;
			if(this.employee != null && this.employee.getId() != null && this.employee.getId() > 0) {
				validation = employee.validate(true);
				validation.putAll(employee.getUser().validateRegister(true));				
			} else {
				employee.setId(0l);
				user.setId(0l);
				validation = employee.validate(false);
				validation.putAll(employee.getUser().validateRegister(false));				
			}
						
			if (validation.isEmpty()) {
				byte[] salt = Cryptography.getSalt();
				employee.getUser().setSalt(Base64.getEncoder().encodeToString(salt));
				employee.getUser().setPassword(Cryptography.getSHA256(employee.getUser().getPassword(), salt));
				
				user = uDAO.merge(employee.getUser());
								
				employee.setExclusionDate(null);
				
				Store s = new Store();
				if (userController.getUsuarioLogado().getEmployee() == null) {
					s = userController.getUsuarioLogado().getStore();
				} else {
					s = userController.getUsuarioLogado().getEmployee().getActualStore();
				}				
				
				employee.setUser(user);
				
				this.employee.getAddress().setCity(this.city);
				address = aDAO.merge(this.employee.getAddress());
				employee.setAddress(address);
				
				employee = eDAO.merge(employee);				
				
				EmployeeStore es = new EmployeeStore();
				es.setEmployee(employee);
				es.setStore(s);
				
				esDAO.merge(es);
				
				this.addMessage("Sucesso", "Cadastro realizado com sucesso.");
				employee = new Employee();
				employee.setUser(new User());
				address = new Address();
				employee.setAddress(address);
				city = new City();
				state = new State();
				this.setErrors(null);
				
				return;
			} else {
				this.setErrors(validation); 
				return;
			}
						
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	public void createAddEmployeePage(ComponentSystemEvent event) {
		if (!this.verifyPageAccess(userController.getMeta(), Enum_Pages.EMPLOYEEPAGES)) {
			return;
		}
		types = Enum_Type_User.getTypesByPermission(userController.getMeta());
		this.states = stDAO.select();
	}
	
	public void createListEmployeePage(ComponentSystemEvent event) {
		if (!this.verifyPageAccess(userController.getMeta(), Enum_Pages.EMPLOYEEPAGES)) {
			return;
		}
		if (this.userController.getUsuarioLogado().getStore() != null) {
			employees = eDAO.findByStore(this.userController.getUsuarioLogado().getStore().getId());
		} else {
			employees = eDAO.findByStore(this.userController.getUsuarioLogado().getEmployee().getActualStore().getId());
		}
		types = Enum_Type_User.getTypesByPermission(userController.getMeta());
		this.states = stDAO.select();
	}
	
	public void delete() {		
		eDAO.removeById(this.selectedEmployee);
		
		employees.remove(this.selectedEmployee);
		this.addMessage("Sucesso", "Sua exclusão foi realizada com sucesso.");
		
		return;
	}	
	
	public void update() {			
		Map<String, String> validation = this.selectedEmployee.validate(true);
		
		if (validation.isEmpty()) {
			eDAO.merge(this.selectedEmployee);
			this.selectedEmployee.getAddress().setCity(this.city);
			aDAO.merge(this.selectedEmployee.getAddress());
			this.selectedEmployee = new Employee();
			this.addMessage("Sucesso", "Sua alteração foi realizada com sucesso.");
			this.executeCommand("PF('editDialog').hide()");			
			return;
		} else {
			this.setErrors(validation);
			return;
		}
		
	}	
	
	public void onStateChange() {
        if(state != null && !state.equals(""))
            cities = cDAO.findCitiesByStateId(this.state.getId());
        else
            cities = new ArrayList<>();
    }
	
	public void onIdentifierChange() {
		Employee e = eDAO.findByIdentifier(this.employee.getIdentifier());
		
		if(e != null && e.getExclusionDate() != null) {
			this.employee = e;
		}
	}
	
	public void onZipChange() {
		JSONObject j = HttpAddress.returnAddress(this.employee.getAddress().getZipCode().replaceAll("[^0-9]", ""));
		try {
			if (j != null) {
				this.employee.getAddress().setStreet(j.getString("logradouro") != null ? j.getString("logradouro") : "");
				this.employee.getAddress().setDistrict(j.getString("bairro") != null ? j.getString("bairro") : "");
								
				this.setState(stDAO.findStateByInitial(j.getString("uf")));
				this.setCities(cDAO.findCitiesByStateId(this.getState().getId()));
				this.setCity(cDAO.findCityByStateAndName(this.state.getId(), j.getString("localidade").toUpperCase()));
				for(City c : this.cities) {
					System.out.println("aqui" + c);
					System.out.println("aqui" + this.getCity());
				}
			} 			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public void updateUser() {	
		try {
			Map<String, String> validation = this.selectedEmployee.getUser().validateRegister(true);
			
			if (validation.isEmpty()) {
				byte[] salt = Cryptography.getSalt();
				this.selectedEmployee.getUser().setSalt(Base64.getEncoder().encodeToString(salt));
				this.selectedEmployee.getUser().setPassword(Cryptography.getSHA256(this.selectedEmployee.getUser().getPassword(), salt));
				uDAO.merge(this.selectedEmployee.getUser());
				this.selectedEmployee = new Employee();
				this.addMessage("Sucesso", "Sua alteração foi realizada com sucesso.");
				this.executeCommand("PF('editUserDialog').hide()");			
				return;
			} else {
				this.setErrors(validation);
				return;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}	

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserController getUserController() {
		return userController;
	}

	public void setUserController(UserController userController) {
		this.userController = userController;
	}
	
	public List<Enum_Type_User> getTypes() {
		return types;
	}

	public void setTypes(List<Enum_Type_User> types) {
		this.types = types;
	}

	public Enum_Gender[] genders() {
		return Enum_Gender.values();
	}

	public String getConfSenha() {
		return confSenha;
	}

	public void setConfSenha(String confSenha) {
		this.confSenha = confSenha;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Employee getSelectedEmployee() {
		return selectedEmployee;
	}

	public void setSelectedEmployee(Employee selectedEmployee) {
		this.city = selectedEmployee.getAddress().getCity();
		if(this.city != null && this.city.getId() > 0) {
			this.state = selectedEmployee.getAddress().getCity().getState();
			this.cities = cDAO.findCitiesByStateId(this.state.getId());
		}
		this.selectedEmployee = selectedEmployee;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public List<State> getStates() {
		return states;
	}

	public void setStates(List<State> states) {
		this.states = states;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
