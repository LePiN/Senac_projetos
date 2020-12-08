package com.r8store.model.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.r8store.util.HibernateUtil;

import com.r8store.model.entity.Store;

@ApplicationScoped
public class StoreDAO extends GenericDAO<Store> {
		
	public List<Store> findStoresByShoppingId(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from Store where exclusionDate is null and shopping_id = :id ");
		query.setParameter("id", id);
		
		return query.list();
	}
	
	public List<Store> findShoppings() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from Store where exclusionDate is null and shopping_id is null ");
		
		return query.list();
	}
	
	public void removeById(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		session.getTransaction().begin();
		
		//Query query = session.createQuery("delete Store where id = :id");
		Query query = session.createQuery("update Store set exclusionDate=now() where id = :id");
		query.setParameter("id", id);
		int result = query.executeUpdate();
		
		session.getTransaction().commit();
	}
	
	public Store findByUser(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from Store where fk_user = :fk_user ");
		query.setParameter("fk_user", id);
		
		return (Store) query.uniqueResult();
	}

	public Store findByEmail(String email) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from Store where email = :email");
		query.setParameter("email", email);
		
		return (Store) query.uniqueResult();
	}
	
	public Store findByCnpj(String cnpj) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from Store where cnpj = :cnpj");
		query.setParameter("cnpj", cnpj);
		
		return (Store) query.uniqueResult();
	}
	
	public Store findByInscricao(String inscricao) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from Store where inscricaoEstadual = :inscricao");
		query.setParameter("inscricao", inscricao);
		
		return (Store) query.uniqueResult();
	}
}
