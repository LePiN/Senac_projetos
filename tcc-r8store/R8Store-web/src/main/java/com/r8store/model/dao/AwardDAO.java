package com.r8store.model.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.r8store.model.entity.Award;
import com.r8store.util.HibernateUtil;

@ApplicationScoped
public class AwardDAO extends GenericDAO<Award> {
	
	public List<Award> findByStore(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from Award where fk_store = :fk_store and exclusionDate is null ");
		query.setParameter("fk_store", id);
		
		return query.list();
	}
	
	public void removeById(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		session.getTransaction().begin();
		
		//Query query = session.createQuery("delete Store where id = :id");
		Query query = session.createQuery("update Award set exclusionDate=now() where id = :id");
		query.setParameter("id", id);
		int result = query.executeUpdate();
		
		session.getTransaction().commit();
	}
}
