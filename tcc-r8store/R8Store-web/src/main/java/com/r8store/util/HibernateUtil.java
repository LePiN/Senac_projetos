package com.r8store.util;


import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.r8store.model.entity.*;

public class HibernateUtil {
	private static SessionFactory fabricaDeSessoes = createSessionFactory();

	public static SessionFactory getSessionFactory() {
		return fabricaDeSessoes;
	}

	private static SessionFactory createSessionFactory() {
		
		try {
			Configuration configuracao = new Configuration().configure("hibernate.cfg.xml");
			configuracao			
			.addAnnotatedClass(Address.class)
			.addAnnotatedClass(Answer.class)
			.addAnnotatedClass(City.class)
			.addAnnotatedClass(Country.class)
			.addAnnotatedClass(Award.class)
			.addAnnotatedClass(Employee.class)
			.addAnnotatedClass(EmployeeStore.class)
			.addAnnotatedClass(Form.class)
			.addAnnotatedClass(Game.class)
			.addAnnotatedClass(PersonAward.class)
			.addAnnotatedClass(Person.class)
			.addAnnotatedClass(Product.class)
			.addAnnotatedClass(Point.class)
			.addAnnotatedClass(Question.class)
			.addAnnotatedClass(Rating.class)
			.addAnnotatedClass(State.class)
			.addAnnotatedClass(Store.class)
			.addAnnotatedClass(Offer.class)
			.addAnnotatedClass(User_Post.class)
			.addAnnotatedClass(User.class);
			ServiceRegistry registro = new StandardServiceRegistryBuilder().applySettings(configuracao.getProperties()).build();
			SessionFactory fabrica = configuracao.buildSessionFactory(registro);
			return fabrica;
		} catch (Throwable ex) {
			System.err.println("A fábrica de sessões não pode ser criada." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
}
