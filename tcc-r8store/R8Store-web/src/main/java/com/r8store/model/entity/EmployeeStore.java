package com.r8store.model.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="employee_store")
public class EmployeeStore extends MasterEntity {
	
	@ManyToOne
    @JoinColumn(name = "fk_store")
	private Store store;
	
	@ManyToOne
    @JoinColumn(name = "fk_employee")
	private Employee employee;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date exclusionDate;

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Date getExclusionDate() {
		return exclusionDate;
	}

	public void setExclusionDate(Date exclusionDate) {
		this.exclusionDate = exclusionDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + ((exclusionDate == null) ? 0 : exclusionDate.hashCode());
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
		EmployeeStore other = (EmployeeStore) obj;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		if (exclusionDate == null) {
			if (other.exclusionDate != null)
				return false;
		} else if (!exclusionDate.equals(other.exclusionDate))
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
		return "EmployeeStore [store=" + store + ", employee=" + employee + ", exclusionDate=" + exclusionDate + "]";
	}
	
}
