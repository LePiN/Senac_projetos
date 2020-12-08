package com.r8store.model.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.r8store.util.HibernateUtil;

import com.r8store.model.entity.Employee;
import com.r8store.model.entity.Store;

@ApplicationScoped
public class EmployeeDAO extends GenericDAO<Employee> {
	
	public Employee findByUser(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from Employee where fk_user = :fk_user and exclusionDate is null ");
		query.setParameter("fk_user", id);
		
		return (Employee) query.uniqueResult();
	}

	public Employee findByEmail(String email) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from Employee where email = :email");
		query.setParameter("email", email);
		
		return (Employee) query.uniqueResult();
	}
	
	public Employee findByIdentifier(String identifier) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from Employee where identifier = :identifier");
		query.setParameter("identifier", identifier);
		
		return (Employee) query.uniqueResult();
	}
	
	public List<Employee> findByStore(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from Employee as e inner join fetch e.employeesStore as es where es.store.id = :id and es.exclusionDate is null group by e.id");
		query.setParameter("id", id);
		
		return query.list();
	}
	
	public void removeById(Employee employee) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		session.getTransaction().begin();
		
		//Query query = session.createQuery("delete Store where id = :id");
		Query query = session.createQuery("update Employee set exclusionDate=now() where id = :id");
		query.setParameter("id", employee.getId());
		int result = query.executeUpdate();
		
		query = session.createQuery("update EmployeeStore set exclusionDate=now() where fk_employee = :employee_id and fk_store = :store_id and exclusionDate is null");
		query.setParameter("employee_id", employee.getId());
		query.setParameter("store_id", employee.getActualStore().getId());
		result = query.executeUpdate();
		
		session.getTransaction().commit();
	}
	
}
