package com.r8store.model.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.r8store.model.entity.Product;
import com.r8store.util.HibernateUtil;

@ApplicationScoped
public class ProductDAO extends GenericDAO<Product> {
	
	public List<Product> findProductsByStore(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from Product where fk_store = :fk_store and exclusionDate is null ");
		query.setParameter("fk_store", id);
		
		return query.list();
	}
	
	public List<Product> findProductsToAward(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from Product as p where p.store.id = :fk_store and p.exclusionDate is null and p.id not in (select a.product.id from Award as a where a.exclusionDate is null and a.product.id = p.id)");
		query.setParameter("fk_store", id);
		
		return query.list();
	}
	
	public List<Product> findProductsToOffer(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from Product as p where p.store.id = :fk_store and p.exclusionDate is null and p.id not in (select o.product.id from Offer as o where o.exclusionDate is null and o.product.store.id = :storeId group by o.product.id)");
		query.setParameter("fk_store", id);
		query.setParameter("storeId", id);
		
		return query.list();
	}
	
	public void removeById(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		session.getTransaction().begin();
				
		Query query = session.createQuery("update Product set exclusionDate=now() where id = :id");
		query.setParameter("id", id);
		int result = query.executeUpdate();
		
		query = session.createQuery("update Offer as o set exclusionDate=now() where o.product.id = :id");
		query.setParameter("id", id);
		result = query.executeUpdate();
		
		session.getTransaction().commit();
	}
	
}
