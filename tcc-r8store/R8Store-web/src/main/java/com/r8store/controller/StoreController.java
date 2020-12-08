package com.r8store.controller;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.json.JSONException;
import org.json.JSONObject;
import org.omnifaces.cdi.ViewScoped;
import org.primefaces.model.UploadedFile;

import com.r8store.enums.Enum_Pages;
import com.r8store.enums.Enum_Type_User;
import com.r8store.model.dao.AddressDAO;
import com.r8store.model.dao.CityDAO;
import com.r8store.model.dao.PointDAO;
import com.r8store.model.dao.StateDAO;
import com.r8store.model.dao.StoreDAO;
import com.r8store.model.dao.UserDAO;
import com.r8store.model.entity.Address;
import com.r8store.model.entity.City;
import com.r8store.model.entity.Employee;
import com.r8store.model.entity.Point;
import com.r8store.model.entity.State;
import com.r8store.model.entity.Store;
import com.r8store.model.entity.User;
import com.r8store.util.Cryptography;
import com.r8store.util.HttpAddress;

@Named
@ViewScoped
public class StoreController extends Controller implements Serializable {

	private static final long serialVersionUID = -7509579110886732244L;
	
	private Store store;
	private User user;
	private List<Store> stores;
	private Store selectedStore;
	private UploadedFile file;
	private City city;
	private State state;
	private List<City> cities;
	private List<State> states;
	private Address address;
	private Point point;
	
	private String coordinates;
	
	@Inject
	private UserController userController;
	
	@Inject
	private StoreDAO sDAO;
	
	@Inject
	private StateDAO stDAO;
	
	@Inject
	private UserDAO uDAO;
	
	@Inject
	private CityDAO cDAO;
	
	@Inject
	private PointDAO pointDAO;
	
	@Inject
	private AddressDAO aDAO;
	
	@PostConstruct
	public void init() {		
		store = new Store();
		store.setAddress(new Address());
		user = new User();
		stores = new ArrayList<>();
		address = new Address();
		states = new ArrayList<>();
		cities = new ArrayList<>();
		selectedStore = new Store();
		selectedStore.setUser(new User());
	}
	
	public void registerStore() {
		try {
			
			Map<String, String> validation = store.validate(false);	
			validation.putAll(user.validateRegister(false));
						
			if (validation.isEmpty()) {
				byte[] salt = Cryptography.getSalt();
				user.setSalt(Base64.getEncoder().encodeToString(salt));
				user.setPassword(Cryptography.getSHA256(user.getPassword(), salt));
				user.setId(0l);
				
				user = uDAO.merge(user);
				
				store.setId(0l);
				store.setUser(user);
				store.setShopping(userController.getUsuarioLogado().getStore());
				store.setType(Enum_Type_User.STORE);
				store.setAddress(null);
				
				store = sDAO.merge(store);
				
				if(this.file.getFileName() != null && !this.file.getFileName().isEmpty()) {
					store.setFile(this.file);
					store.upload("imagemPrincipal");
				}	
				
				this.addMessage("Sucesso", "Cadastro realizado com sucesso.");
				user = new User();
				store = new Store();
				this.setErrors(null);
				
				return;
			} else {
				this.setErrors(validation); 
				return;
			}
						
		} catch (NoSuchAlgorithmException e) {			
			e.printStackTrace();
		}
		
		return;
	}
	
	public void registerShopping() {
		try {
			store.setType(Enum_Type_User.SHOPPING);
			
			Map<String, String> validation = store.validate(false);	
			validation.putAll(user.validateRegister(false));
						
			if (validation.isEmpty()) {
				byte[] salt = Cryptography.getSalt();
				user.setSalt(Base64.getEncoder().encodeToString(salt));
				user.setPassword(Cryptography.getSHA256(user.getPassword(), salt));
				user.setId(0l);
				
				user = uDAO.merge(user);
				
				store.setId(0l);
				store.setUser(user);
				
				this.address.setCity(city);
				this.address.setId(0l);
				this.address = aDAO.merge(this.address);
				store.setAddress(this.address);
				
				store = sDAO.merge(store);
				
				if(this.file.getFileName() != null && !this.file.getFileName().isEmpty()) {
					store.setFile(this.file);
					store.upload("imagemPrincipal");
				}				
				
				this.addMessage("Sucesso", "Cadastro realizado com sucesso.");
				user = new User();
				store = new Store();
				
				this.address = new Address();
				this.city = new City();
				this.state = new State();
				this.setErrors(null);
				
				return;
			} else {
				this.setErrors(validation); 
				return;
			}
						
		} catch (NoSuchAlgorithmException e) {			
			e.printStackTrace();
		}
		
		return;
	}
	
	public void createAddStorePage(ComponentSystemEvent event){
		if (!this.verifyPageAccess(userController.getMeta(), Enum_Pages.SHOPPINGPAGES)) {
			return;
		}		
	}
	
	public void createAddShoppingPage(ComponentSystemEvent event){
		if (!this.verifyPageAccess(userController.getMeta(), Enum_Pages.ADMINPAGES)) {
			return;
		}
		this.states = stDAO.select();
	}
	
	public void createListStorePage(ComponentSystemEvent event){
		if (!this.verifyPageAccess(userController.getMeta(), Enum_Pages.SHOPPINGPAGES)) {
			return;
		}
		
		this.stores = sDAO.findStoresByShoppingId(userController.getUsuarioLogado().getStore().getId());
	}
	
	public void createListShoppingPage(ComponentSystemEvent event){
		if (!this.verifyPageAccess(userController.getMeta(), Enum_Pages.ADMINPAGES)) {
			return;
		}
		
		this.stores = sDAO.findShoppings();
		
		if(this.getErrors() != null && this.getErrors().size() > 0) {
			this.executeCommand("PF('editDialog').show()");	
		}
	}
	
	public void showStore(ComponentSystemEvent event) {
		if (!this.verifyPageAccess(userController.getMeta(), Enum_Pages.PERSONPAGES)) {
			return;
		}
		
		Map<String, String> params = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap();
		
		this.store = sDAO.findById(Long.parseLong(params.get("id")));
	}
	
	public void showDashboard(ComponentSystemEvent event) {
		if (!this.verifyPageAccess(userController.getMeta(), Enum_Pages.PERSONPAGES)) {
			return;
		}
		
		Map<String, String> params = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap();
		
		this.store = sDAO.findById(Long.parseLong(params.get("shop")));
		this.point = pointDAO.findByUserAndShopping(this.userController.getUsuarioLogado().getPerson().getId(), this.store.getId());
		if (point == null) {
			point = new Point();
			point.setId(0l);
			point.setPoints(0d);
			point.setStore(this.store);
			point.setPerson(this.userController.getUsuarioLogado().getPerson());
			point = pointDAO.merge(point);
		}
	}
	
	public void delete() {		
		sDAO.removeById(this.selectedStore.getId());
		
		stores.remove(this.selectedStore);
		this.addMessage("Sucesso", "Sua exclusão foi realizada com sucesso.");
		
		return;
	}	
	
	public void update() {			
		Map<String, String> validation = this.selectedStore.validate(true);
		
		if (validation.isEmpty()) {
			sDAO.merge(this.selectedStore);			
			
			if(this.file != null && this.file.getFileName() != null && !this.file.getFileName().isEmpty()) {
				selectedStore.setFile(this.file);
				selectedStore.upload("imagemPrincipal");
			}
			
			this.selectedStore = new Store();
			this.addMessage("Sucesso", "Sua alteração foi realizada com sucesso.");
			this.setErrors(null);
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
	
	public void onZipChange() {
		JSONObject j = HttpAddress.returnAddress(this.address.getZipCode().replaceAll("[^0-9]", ""));
		try {
			if (j != null) {
				this.address.setStreet(j.getString("logradouro") != null ? j.getString("logradouro") : "");
				this.address.setDistrict(j.getString("bairro") != null ? j.getString("bairro") : "");
								
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
			Map<String, String> validation = this.selectedStore.getUser().validateRegister(true);
			
			if (validation.isEmpty()) {
				byte[] salt = Cryptography.getSalt();
				this.selectedStore.getUser().setSalt(Base64.getEncoder().encodeToString(salt));
				this.selectedStore.getUser().setPassword(Cryptography.getSHA256(this.selectedStore.getUser().getPassword(), salt));
				uDAO.merge(this.selectedStore.getUser());
				this.selectedStore = new Store();
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

	public UserController getUserController() {
		return userController;
	}

	public void setUserController(UserController userController) {
		this.userController = userController;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Store> getStores() {
		return stores;
	}

	public void setStores(List<Store> stores) {
		this.stores = stores;
	}

	public Store getSelectedStore() {
		return selectedStore;
	}

	public void setSelectedStore(Store selectedStore) {
		this.setErrors(null);
		this.selectedStore = selectedStore;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public String getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
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

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}
	
}
