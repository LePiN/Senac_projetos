package com.r8store.model.dao;

import javax.enterprise.context.ApplicationScoped;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.r8store.util.HibernateUtil;

import com.r8store.model.entity.Person;

@ApplicationScoped
public class PersonDAO extends GenericDAO<Person> {
	
	public Person findByUser(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from Person where fk_user = :fk_user ");
		query.setParameter("fk_user", id);
		
		return (Person) query.uniqueResult();
	}

	public Person findByEmail(String email) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from Person where email = :email ");
		query.setParameter("email", email);
		
		return (Person) query.uniqueResult();
	}
	
}
