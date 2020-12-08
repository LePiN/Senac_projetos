package com.r8store.util;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import com.r8store.enums.Enum_Type_User;
import com.r8store.model.dao.EmployeeDAO;
import com.r8store.model.dao.StateDAO;
import com.r8store.model.dao.UserDAO;
import com.r8store.model.entity.Employee;
import com.r8store.model.entity.State;
import com.r8store.model.entity.User;

public class PopularDB {

	public static void main(String[] args) {

		try {		
			System.out.println("Inicializando o processo de inserção no banco...");			
			
			//CREATE ADMIN
			Employee e = new Employee();
			EmployeeDAO eDAO = new EmployeeDAO();
			User user = createUser("admin", "j78tK2017");
			
			e.setUser(user);
			e.setName("Admin");
			e.setEmail("admin@teste.com.br");
			e.setIdentifier("11111111111");
			e.setType(Enum_Type_User.ADMIN);
			
			e = eDAO.merge(e);	
			
			State state = new State();
			StateDAO sDAO = new StateDAO();
			
			state.setInitials("AC");
			state.setName("Acre");
			sDAO.merge(state);
			
			state = new State();
			state.setInitials("AL");
			state.setName("Alagoas");
			sDAO.merge(state);
			
			state = new State();
			state.setInitials("AP");
			state.setName("Amapá");
			sDAO.merge(state);
			
			state = new State();
			state.setInitials("AM");
			state.setName("Amazonas");
			sDAO.merge(state);
			
			state = new State();
			state.setInitials("BA");
			state.setName("Bahia");
			sDAO.merge(state);
			
			state = new State();
			state.setInitials("CE");
			state.setName("Ceará");
			sDAO.merge(state);
			
			state = new State();
			state.setInitials("DF");
			state.setName("Distrito Federal");
			sDAO.merge(state);
			
			state = new State();
			state.setInitials("ES");
			state.setName("Espírito Santo");
			sDAO.merge(state);
			
			state = new State();
			state.setInitials("GO");
			state.setName("Goiás");
			sDAO.merge(state);
			
			state = new State();
			state.setInitials("MA");
			state.setName("Maranhão");
			sDAO.merge(state);
			
			state = new State();
			state.setInitials("MT");
			state.setName("Matro Grosso");
			sDAO.merge(state);
			
			state = new State();
			state.setInitials("MS");
			state.setName("Matro Grosso do Sul");
			sDAO.merge(state);
			
			state = new State();
			state.setInitials("MG");
			state.setName("Minas Gerais");
			sDAO.merge(state);
			
			state = new State();
			state.setInitials("PA");
			state.setName("Pará");
			sDAO.merge(state);
			
			state = new State();
			state.setInitials("PB");
			state.setName("Paraíba");
			sDAO.merge(state);
			
			state = new State();
			state.setInitials("PR");
			state.setName("Paraná");
			sDAO.merge(state);
			
			state = new State();
			state.setInitials("PE");
			state.setName("Pernambucano");
			sDAO.merge(state);
			
			state = new State();
			state.setInitials("PI");
			state.setName("Piauí");
			sDAO.merge(state);
			
			state = new State();
			state.setInitials("RJ");
			state.setName("Rio de Janeiro");
			sDAO.merge(state);
			
			state = new State();
			state.setInitials("RN");
			state.setName("Rio Grande do Norte");
			sDAO.merge(state);
			
			state = new State();
			state.setInitials("RS");
			state.setName("Rio Grande do Sul");
			sDAO.merge(state);
			
			state = new State();
			state.setInitials("RO");
			state.setName("Rondônia");
			sDAO.merge(state);
			
			state = new State();
			state.setInitials("RR");
			state.setName("Roraima");
			sDAO.merge(state);
			
			state = new State();
			state.setInitials("SC");
			state.setName("Santa Catarina");
			sDAO.merge(state);
			
			state = new State();
			state.setInitials("SP");
			state.setName("São Paulo");
			sDAO.merge(state);
			
			state = new State();
			state.setInitials("SE");
			state.setName("Sergipe");
			sDAO.merge(state);
			
			state = new State();
			state.setInitials("TO");
			state.setName("Tocantins");
			sDAO.merge(state);
			
//			//CREATE SHOPPING
//			Store s = new Store();
//			StoreDAO sDAO = new StoreDAO();
//			user = createUser("shopping", "123");
//			
//			s.setUser(user);
//			s.setCnpj("68223873000105");
//			s.setName("Shopping principal");
//			s.setInscricaoEstadual("402031120750");
//			s.setEmail("shopping@teste.com.br");
//			s.setType(Enum_Type_User.SHOPPING);
//			
//			Store shopping = sDAO.merge(s);
//			
//			//CREATE STORE
//			s = new Store();
//			user = createUser("store1", "123");
//			
//			s.setUser(user);
//			s.setCnpj("68223873000105");
//			s.setName("Loja 1");
//			s.setInscricaoEstadual("402031120750");
//			s.setEmail("store1@teste.com.br");
//			s.setShopping(shopping);
//			s.setType(Enum_Type_User.STORE);
//			
//			s = sDAO.merge(s);
			
			System.out.println("Finalizando o processo de inserção no banco.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Houve um problema ao realizar a inserção");
		}
	}
	
	private static User createUser(String login, String password) {
		UserDAO uDAO = new UserDAO();
		User user = new User();
				
		try {
			user.setLogin(login);
			user.setPassword(password);
			
			byte[] salt;
			salt = Cryptography.getSalt();
			user.setSalt(Base64.getEncoder().encodeToString(salt));
			user.setPassword(Cryptography.getSHA256(user.getPassword(), salt));
			user.setId(0l);
			
			user = uDAO.merge(user);
			return user;
		} catch (NoSuchAlgorithmException e) { 
			e.printStackTrace();
		}
		
		return null;
	}

}
