package com.r8store.enums;

import java.util.ArrayList;
import java.util.List;

import com.r8store.model.entity.SessionEntity;

public enum Enum_Type_User {
	ADMIN(
		1, 
		"Admin",
		new String[] {} 
	),
	STORE(
		2, 
		"Loja",
		new String[] {}
	),
	SHOPPING(
		3, 
		"Shopping",
		new String[] {}
	),
	EMPLOYEE(
		4, 
		"Funcionario",
		new String[] {"STORE", "MANAGER", "SHOPPING"} // STORE e MANAGER podem cadastrar o tipo especifico EMPLOYEE
	),
	MANAGER(
		5, 
		"Gerente",
		new String[] {"STORE"} 
	),
	PERSON(
		6, 
		"Usuario",
		new String[] {}
	);
	
	private int id;
	private String name;
	private String[] registerPermission; //Determina quais tipos de usu�rios logados podem cadastrar o tipo em especifico
	
	Enum_Type_User(int id, String name, String[] registerPermission) {
		this.id = id;
		this.name = name;
		this.registerPermission = registerPermission;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String[] getRegisterPermission() {
		return registerPermission;
	}
	
	public static List<Enum_Type_User> getTypesByPermission(SessionEntity meta) {
		List<Enum_Type_User> types = new ArrayList<>();
		
		for(Enum_Type_User type : Enum_Type_User.values()) {
			for(String nameType : type.getRegisterPermission()) {
				if (Enum_Type_User.valueOf(nameType).equals(meta.getType())) {
					types.add(type);
				}
			}
		}
		
		return types;
	}
	
}
