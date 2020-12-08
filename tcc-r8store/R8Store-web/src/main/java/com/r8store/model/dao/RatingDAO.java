package com.r8store.model.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartSeries;

import com.r8store.util.HibernateUtil;
import com.r8store.enums.Enum_Gender;
import com.r8store.model.entity.Rating;

@ApplicationScoped
public class RatingDAO extends GenericDAO<Rating> {
	
	public Rating findByToken(String token) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from Rating where token = :token ");
		query.setParameter("token", token);
		
		return (Rating) query.uniqueResult();
	}
	
	public int findByGender(Enum_Gender gender, ChartSeries series, Long storeId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("select month(r.createdAt), year(createdAt), count(*) from Person p inner join p.rating as r where p.gender = :gender and r.store.id = :id group by month(r.createdAt), year(r.createdAt)");
		query.setParameter("gender", gender);
		query.setParameter("id", storeId);
		List<Object[]> rows = query.list();
		
		for (Object[] row: rows) {
			series.set(row[0] + "-" + row[1], ((Long) row[2]).intValue());
		}
		
		return rows.size();
	}
	
	public Object[] findMonths(int year, Long storeId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("select min(month(r.createdAt)), max(month(r.createdAt)) from Rating r where year(r.createdAt) = :year and r.store.id = :storeId");
		query.setParameter("year", year);
		query.setParameter("storeId", storeId);
		List<Object[]> rows = query.list();
		
		return rows.get(0);
	}
	
	public List<Object[]> findByMonth(int starNumber, Long storeId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("select month(r.createdAt), year(r.createdAt), count(*) from Rating r where r.total = :starNumber and r.store.id = :storeId group by month(r.createdAt)");
		query.setParameter("starNumber", (double) starNumber);
		query.setParameter("storeId", storeId);
		List<Object[]> rows = query.list();
		
		return rows;
	}

	public List<Rating> findByStore(Long storeId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from Rating as r inner join fetch r.store as s where s.id = :id group by r.id");
		query.setParameter("id", storeId);
		
		return query.list();
	}

	public List<Rating> findByEmployee(Long storeId, String employeeName) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from Rating as r inner join fetch r.employee as e where r.store.id = :storeId and e.name LIKE :name group by r.id");
		query.setParameter("storeId", storeId);
		query.setParameter("name", "%" + employeeName + "%");
		
		return query.list();
	}
	
	public Double findTotalByStore(Long idStore) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("select sum(r.total), count(r.id) from Rating r where r.store.id = :idStore and r.person.id is not null");
		query.setParameter("idStore", idStore);
		
		Double total = 0d;
		Long quantity = 0l;
		
		List<Object[]> rows = query.list();
		if (rows != null && rows.size() > 0) {
			for (Object[] row: rows) {
				if(row[0] != null) {
					total = ((Double) row[0]);
				}
				if(row[1] != null) {
					quantity = ((Long) row[1]);
				}
			}
		}		
		
		Double media = 0d;
		if (quantity != 0d) {
			media = total / quantity;
		}
		return media;
	}
	
	
}
