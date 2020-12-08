package com.r8store.api;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.r8store.enums.Enum_Game;
import com.r8store.model.dao.GameDAO;
import com.r8store.model.dao.PointDAO;
import com.r8store.model.entity.Point;
import com.r8store.model.entity.Store;
import com.r8store.model.entity.User;
import com.r8store.util.GameManager;
import com.r8store.util.HibernateUtil;

@Path("/store")
public class StoreResource {
	
	@GET
	@Path("/shoppings")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Store> getStores(@QueryParam("name") String name, @QueryParam("lat") String lat, @QueryParam("long") String lng, @QueryParam("rating") boolean rating) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException { 
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sql = "from Store where 1=1 and shopping_id is null and exclusionDate is null "; 
        
        if (name != null && !name.isEmpty()) {
        	sql += "and name LIKE :name "; 
        }      
        
        if (rating) {
        	sql += "order by rating desc "; 
        }
        
		Query query = session.createQuery(sql);
		
		if (name != null && !name.isEmpty()) {
			query.setParameter("name", "%" + name + "%");
        }
		
		List<Store> stores = query.list(); 
		
		if (lat != null && !lat.isEmpty() && lng != null && !lng.isEmpty()) {
			OrderedList list = new OrderedList();
			list.setLat(Double.valueOf(lat));
			list.setLng(Double.valueOf(lng));
			list.populateList(stores);
			stores = list.returnList();
        }
        return stores;
    }
	
	@GET
	@Path("/stores/{shoppingId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Store> getUser(@PathParam("shoppingId") Long shoppingId, @QueryParam("name") String name) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		Session session = HibernateUtil.getSessionFactory().openSession();
        String sql = "from Store where 1=1 and shopping_id = :id and exclusionDate is null "; 
        System.out.println("id: " + shoppingId);
        if (name != null && !name.isEmpty()) {
        	sql += "and name LIKE :name "; 
        } 
        
    	sql += "order by rating desc "; 
        
		Query query = session.createQuery(sql);
		query.setParameter("id", shoppingId);
		
		if (name != null && !name.isEmpty()) {
			query.setParameter("name", "%" + name + "%");
        }
		
		List<Store> stores = query.list();
		return stores;
    }
	
	@GET
	@Path("/checkin")
    @Produces(MediaType.APPLICATION_JSON)
    public Store getOffersByShopping(@QueryParam("lat") String lat, @QueryParam("lng") String lng, @Context HttpServletRequest request) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		GameDAO gDAO = new GameDAO();
		
		try {
			HttpSession userSession = request.getSession();
			User user = (User) userSession.getAttribute("user");
			Session session = HibernateUtil.getSessionFactory().openSession();
	        String sql = "from Store where 1=1 and shopping_id is null and exclusionDate is null"; 
	        
			Query query = session.createQuery(sql);
			
			List<Store> stores = query.list(); 
			
			Store s = null;
			if (lat != null && !lat.isEmpty() && lng != null && !lng.isEmpty()) {
				OrderedList list = new OrderedList();
				list.setLat(Double.valueOf(lat));
				list.setLng(Double.valueOf(lng));
				list.populateList(stores);
				stores = list.returnList();						
	        
				if (stores.size() > 0) {
					for(Store store : stores) {
						if (list.calcularDistancia(store.getCoordinateX(), store.getCoordinateY()) <= 1) {
							if (gDAO.findByCheckIn(new Date(), user.getPerson().getId(), store.getId()) == null)
								return store; 
						}
					}
					
				}
			}
			
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}									
        
    }
	
	@GET
	@Path("/checkin/confirm/{storeId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response confirmCheckIn(@PathParam("storeId") String storeId, @Context HttpServletRequest request) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		try {
			HttpSession userSession = request.getSession();
			User user = (User) userSession.getAttribute("user");
			
			PointDAO pDAO = new PointDAO();
			Point point = pDAO.findByUserAndShopping(user.getPerson().getId(), Long.valueOf(storeId));
			
			GameManager.createGame(point, Enum_Game.CHECKIN, 0d);	
			
		} catch (Exception e) {
			return Response.status(404).entity(new RESPONSE_STORE("200")).build();
		}
						
		return Response.status(200).entity(new RESPONSE_STORE("100")).build();
    }
	
	class RESPONSE_STORE {
		public String title;
		public String code;
		public String message;
		
		public RESPONSE_STORE(String code) {
			switch (code) {
				case "100":
					this.code = code;
					title = "Sucesso";
					message = "Checkin realizado com sucesso";
					break;
				case "200":
					title = "Erro";
					this.code = code;
					message = "Não foi possível realizar o checkin";
					break;
			} 
		}
	}
	
}
