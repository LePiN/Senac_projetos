package com.r8store.model.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.r8store.model.entity.Point;
import com.r8store.util.HibernateUtil;

@ApplicationScoped
public class PointDAO extends GenericDAO<Point> {
		
	public Point findByUserAndShopping(Long idUser, Long idShopping) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from Point where fk_person = :fk_person and fk_store = :fk_store");
		query.setParameter("fk_person", idUser);
		query.setParameter("fk_store", idShopping);
		
		return (Point) query.uniqueResult();
	}
	
	public Double findAccumulated(Long idPerson) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("select sum(p.accumulated), sum(p.id) from Point p inner join p.person as pe where pe.id = :idPerson");
		query.setParameter("idPerson", idPerson);
		
		Double accumulated = 0d;
		List<Object[]> rows = query.list();
		if (rows != null && rows.size() > 0) {
			for (Object[] row: rows) {
				if(row[0] != null) {
					accumulated = ((Double) row[0]).doubleValue();
				}
			}
		}		
		
		return accumulated;
	}
	
}
