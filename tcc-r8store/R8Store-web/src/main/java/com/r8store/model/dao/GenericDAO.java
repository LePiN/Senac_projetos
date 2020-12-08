package com.r8store.model.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.r8store.util.HibernateUtil;

public class GenericDAO<Entity> {	
	private Class<Entity> classe;
	
	@SuppressWarnings("unchecked")
	public GenericDAO(){
		Class class1 = getClass();
	    Type genericSuperclass = null;
	    for(;;) {
	        genericSuperclass = class1.getGenericSuperclass();
	        if(genericSuperclass instanceof ParameterizedType)
	            break;
	        class1 = class1.getSuperclass();
	    }
	    ParameterizedType genericSuperclass_ = (ParameterizedType) genericSuperclass;
	    this.classe = ((Class) ((Class) genericSuperclass_.getActualTypeArguments()[0]));
	}
	  
	public void save(Entity e){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			 transacao = sessao.beginTransaction();
			 sessao.save(e);
			 transacao.commit();				 
		}catch(RuntimeException erro){			
			if (transacao!=null)
			transacao.rollback();
			throw erro;
		}
		finally{
			sessao.close();
		}		
	}
	
	public void delete(Entity e){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			 transacao = sessao.beginTransaction();
			 sessao.delete(e);
			 transacao.commit();	 
		}catch(RuntimeException erro){			
			if (transacao!=null)
			transacao.rollback();
			throw erro;
		}
		finally{
			sessao.close();
		}		
	}
	
	public void update(Entity e){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try{
			 transacao = sessao.beginTransaction();
			 sessao.update(e);
			 transacao.commit();	
			
		}catch(RuntimeException erro){			
			if (transacao!=null)
			transacao.rollback();
			throw erro;
		}
		finally{
			sessao.close();
		}		
	}
	
	public List<Entity> select(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();		
		try{	
			CriteriaBuilder builder = sessao.getCriteriaBuilder();
			CriteriaQuery<Entity> query = builder.createQuery(classe);
			Root<Entity> from = query.from(classe);
			CriteriaQuery<Entity> select = query.select(from);	
			
			TypedQuery<Entity> typedQuery = sessao.createQuery(select);
			List<Entity> resultado = typedQuery.getResultList();
			return resultado;
		}catch(RuntimeException erro){		
		
			throw erro;
		}
		finally{
			sessao.close();
		}		
	}
	
	public Entity findById(Long id){
		Session sessao = HibernateUtil.getSessionFactory().openSession();		
		try{	
			CriteriaBuilder builder = sessao.getCriteriaBuilder();
			CriteriaQuery<Entity> query = builder.createQuery(classe);
			Root<Entity> from = query.from(classe);
			CriteriaQuery<Entity> select = query.select(from).where(builder.equal(from.get("id"), id));	
			
			TypedQuery<Entity> typedQuery = sessao.createQuery(select);
			Entity resultado = typedQuery.getSingleResult();
			return resultado;
		}catch(RuntimeException erro){		
			throw erro;
		}
		finally{
			sessao.close();
		}		
		
	}
	
	public Entity merge(Entity e) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			@SuppressWarnings("unchecked")
			Entity ent = (Entity) sessao.merge(e);
			transacao.commit();
			return(ent);
		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
	}

}