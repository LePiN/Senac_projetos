package com.r8store.model.dao;

import javax.enterprise.context.ApplicationScoped;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.r8store.util.HibernateUtil;

import com.r8store.model.entity.User;

@ApplicationScoped
public class UserDAO extends GenericDAO<User> {
	
	public User findByUsername(String username) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from User where login = :login ");
		query.setParameter("login", username);
		
		return (User) query.uniqueResult();
	}
	
}
