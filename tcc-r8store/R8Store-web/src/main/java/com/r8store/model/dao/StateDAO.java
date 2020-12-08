package com.r8store.model.dao;

import javax.enterprise.context.ApplicationScoped;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.r8store.model.entity.State;
import com.r8store.util.HibernateUtil;

@ApplicationScoped
public class StateDAO extends GenericDAO<State> {	
	
	public State findStateByInitial(String initial) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from State where initials = :initial");
		query.setParameter("initial", initial);
		
		return (State) query.uniqueResult();
	}
}
