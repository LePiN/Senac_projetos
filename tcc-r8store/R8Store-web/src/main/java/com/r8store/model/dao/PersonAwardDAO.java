package com.r8store.model.dao;

import javax.enterprise.context.ApplicationScoped;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.r8store.model.entity.PersonAward;
import com.r8store.util.HibernateUtil;

@ApplicationScoped
public class PersonAwardDAO extends GenericDAO<PersonAward> {

	public PersonAward findByPerson(Long awardId, Long personId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from PersonAward as p where p.award.id = :awardId and p.person.id = :personId ");
		query.setParameter("awardId", awardId);
		query.setParameter("personId", personId);
		
		return (PersonAward) query.uniqueResult();
	}

	public PersonAward findByCode(String code) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from PersonAward as p where p.code = :code and p.finalized = 0 and p.award.exclusionDate is null ");
		query.setParameter("code", code);
		
		return (PersonAward) query.uniqueResult();
	}	
	
}
