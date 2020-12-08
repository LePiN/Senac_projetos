package com.r8store.model.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.r8store.model.entity.City;
import com.r8store.model.entity.State;
import com.r8store.util.HibernateUtil;

@ApplicationScoped
public class CityDAO extends GenericDAO<City> {
	
	public List<City> findCitiesByStateId(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from City where fk_state = :id ");
		query.setParameter("id", id);
		
		return query.list();
	}
	
	public City findCityByStateAndName(Long id, String name) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from City where fk_state = :id and name = :name");
		query.setParameter("id", id);
		query.setParameter("name", name);
		
		return (City) query.uniqueResult();
	}
	
}
