package com.r8store.api;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.r8store.model.entity.Offer;
import com.r8store.util.HibernateUtil;

@Path("/offer")
public class OfferResource {

	@GET
	@Path("/offers/{storeId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Offer> getOffersByShopping(@PathParam("storeId") Long id, @QueryParam("name") String name, @Context HttpServletRequest request) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		return this.findOffersByShop(id);
    }
	private List<Offer> findOffersByShop(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
        String sql = "from Offer as o where 1=1 and o.product.store.shopping.id = :id and o.exclusionDate is null and o.date > now()";
        
		Query query = session.createQuery(sql);
		query.setParameter("id", id);
		
		List<Offer> offers = query.list();
		return offers;
	}
	
	@GET
	@Path("/offers/store/{storeId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Offer> getOffersByStore(@PathParam("storeId") Long id, @QueryParam("name") String name, @Context HttpServletRequest request) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		return this.findOffersByStore(id);
    }
	
	private List<Offer> findOffersByStore(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
        String sql = "from Offer as o where 1=1 and o.product.store.id = :id and o.exclusionDate is null and o.date > now()";
        
		Query query = session.createQuery(sql);
		query.setParameter("id", id);
		
		List<Offer> offers = query.list();
		return offers;
	}

}
