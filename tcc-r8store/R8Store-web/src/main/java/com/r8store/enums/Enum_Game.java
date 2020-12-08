package com.r8store.enums;

public enum Enum_Game {
	RATE("RATE"),
	AWARD("AWARD"),
	CHECKIN("CHECKIN");
	
	private String name;
	
	Enum_Game(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
