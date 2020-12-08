package com.r8store.enums;

public enum Enum_Gender {
	MASCULINO("Masculino"),
	FEMININO("Feminino");
	
	private String name;
	
	Enum_Gender(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
