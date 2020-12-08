package com.r8store.model.dao;

import java.util.Date;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.r8store.enums.Enum_Game;
import com.r8store.model.entity.Game;
import com.r8store.model.entity.Person;
import com.r8store.util.HibernateUtil;

@ApplicationScoped
public class GameDAO extends GenericDAO<Game> {

	public List<Game> findByDate(Long id, Enum_Game game, Date date) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from Game as g inner join fetch g.point as p where p.person.id = :personId and g.type = :type and day(g.date) = day(:date) and month(g.date) = month(:date) and year(g.date) = year(:date) group by g.id");		
		query.setParameter("personId", id);
		query.setParameter("type", game);
		query.setParameter("date", date);
		
		return query.list();
	}
	
	public Game findByCheckIn(Date date, Long idPerson, Long idStore) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from Game as g where g.type = :type and g.point.person.id = :idPerson and g.point.store.id = :idStore and day(g.date) = day(:date) and month(g.date) = month(:date) and year(g.date) = year(:date)");
		query.setParameter("idPerson", idPerson);
		query.setParameter("idStore", idStore);
		query.setParameter("date", date);
		query.setParameter("type", Enum_Game.CHECKIN);
		
		return (Game) query.uniqueResult();
	}
	
	public void updateByPerson(Long id, Double points) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("update Point as p set p.points = p.points + :points where p.person.id = :id");		
			query.setParameter("points", points);
			query.setParameter("id", id);
			query.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}				
	}
	
}
