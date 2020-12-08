package com.r8store.enums;

public enum Enum_Pages {
	
	PAGEDASHBOARD(
		"dashboard.xhtml", 
		new String[] {"SHOPPING", "STORE", "ADMIN", "MANAGER"}
			
	),
	STATISTICPAGES(
		"dashboard.xhtml", 
		new String[] {"STORE", "MANAGER"}
	),
	PERSONPAGES(
		"dashboard.xhtml", 
		new String[] {"PERSON"}
	),
	SHOPPINGPAGES(
		"dashboard.xhtml", 
		new String[] {"SHOPPING"}
	),
	EMPLOYEEPAGES(
		"dashboard.xhtml", 
		new String[] {"STORE", "MANAGER", "EMPLOYEE", "SHOPPING"}
	),
	ADMINPAGES(
		"dashboard.xhtml", 
		new String[] {"ADMIN"}
	);
	
	private String pageName;
	private String[] permissions;
	
	Enum_Pages(String pageName, String[] permissions) {
		this.pageName = pageName;
		this.permissions = permissions;
	}

	public String getPageName() {
		return pageName;
	}

	public String[] getPermissions() {
		return permissions;
	}

	
}
