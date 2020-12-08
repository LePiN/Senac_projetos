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
import com.r8store.model.dao.AwardDAO;
import com.r8store.model.dao.PersonAwardDAO;
import com.r8store.model.dao.PersonDAO;
import com.r8store.model.dao.PointDAO;
import com.r8store.model.entity.Award;
import com.r8store.model.entity.Person;
import com.r8store.model.entity.PersonAward;
import com.r8store.model.entity.Point;
import com.r8store.model.entity.User;
import com.r8store.util.GameManager;
import com.r8store.util.HibernateUtil;

@Path("/award")
public class AwardResource {
	
	@GET
	@Path("/awards")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Award> getAwards(@QueryParam("id") Long id, @QueryParam("name") String name, @Context HttpServletRequest request) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		
		return this.findAwards(u.getPerson().getId());
    }
	
	@GET
	@Path("/related/{awardId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Award> getRelatedAwards(@PathParam("awardId") Long id, @QueryParam("name") String name, @Context HttpServletRequest request) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		
		AwardDAO aDAO = new AwardDAO();
		Award a = aDAO.findById(id);
		
		return this.findRelatedAwards(u.getPerson().getId(), a.getStore().getId(), a.getId());
    }
	
	private List<Award> findRelatedAwards(Long id, Long storeId, Long awardId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
        String sql = "from Award as a where 1=1 and quantity > 0 and a.id <> :awardId and a.exclusionDate is null and a.store.id = :shopId and a.store.exclusionDate is null";
        
        if (id > 0) {
        	sql += " and a.id not in (select pa.award.id from PersonAward as pa where pa.person.id = :id)";
        }
        
		Query query = session.createQuery(sql);
		
		if (id > 0) {
			query.setParameter("id", id);
		}
		
		query.setParameter("shopId", storeId);
		query.setParameter("awardId", awardId);
		
		List<Award> awards = query.list();
		return awards;
	}

	@GET
	@Path("/awards/{shopId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Award> getAwardsByShopping(@PathParam("shopId") Long id, @QueryParam("name") String name, @Context HttpServletRequest request) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		Long personId = u != null ? u.getPerson().getId() : 0;
		return this.findAwardsByShop(personId, id);
    }
	
	@GET
	@Path("/history")
    @Produces(MediaType.APPLICATION_JSON)
    public List<PersonAward> getAwardsByShopping(@QueryParam("name") String name, @Context HttpServletRequest request) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		Long personId = u != null ? u.getPerson().getId() : 0;
		return this.findAwardsByUser(personId);
    }
	
	@GET
	@Path("/awards/store/{storeId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Award> getAwardsByStore(@PathParam("storeId") Long id, @QueryParam("name") String name, @Context HttpServletRequest request) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		Long personId = u != null ? u.getPerson().getId() : 0;
		return this.findAwardsByStore(personId, id);
    }

	@GET
	@Path("/adquire")
    @Produces(MediaType.APPLICATION_JSON)
    public Response adquireAwards(@QueryParam("id") Long id, @Context HttpServletRequest request) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		AwardDAO aDAO = new AwardDAO();
		PersonAwardDAO paDAO = new PersonAwardDAO();
		PointDAO pointDAO = new PointDAO();		
		
		if (u != null) {
			Person person = u.getPerson();			
			Award award = aDAO.findById(id);	
			
			Point p = pointDAO.findByUserAndShopping(u.getPerson().getId(), award.getStore().getShopping().getId());
			
			if(p.getPoints() - award.getPoints() >= 0) {
				PersonAward pa = paDAO.findByPerson(id, person.getId());
				if(pa == null) {
					pa = new PersonAward();
					award.setQuantity(award.getQuantity() - 1);
			        aDAO.merge(award);
				}				        
		        
				String code = Integer.toHexString((new Date().toString() + 
						u.getPerson().getId()).hashCode()).toUpperCase();
				
				pa.setCode(code);
				pa.setFinalized(false);
		        pa.setPerson(person);
		        pa.setAward(award);	
		        pa.setAdquiredIn(new Date());
		        paDAO.merge(pa);
		        p.setPoints(p.getPoints() - award.getPoints());
		        
		        pointDAO.merge(p);
		        
		        GameManager.createGame(p, Enum_Game.AWARD, -2);
		        
		        return Response.status(200).entity(new ERROR_AWARD("100", p.getPoints())).build();
			} else {
				return Response.status(404).entity(new ERROR_AWARD("200", 0d)).build();
			}
			
		}
		
		return Response.status(404).entity(new ERROR_AWARD("300", 0d)).build();
    }
	
	public List<Award> findAwards(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
        String sql = "from Award as a where 1=1 and quantity > 0 and a.id not in (select pa.award.id from PersonAward as pa where pa.person.id = :id) and a.exclusionDate is null and a.store.exclusionDate is null";
        
		Query query = session.createQuery(sql);
		query.setParameter("id", id);
		
		List<Award> awards = query.list();
		return awards;
	}
	
	private List<Award> findAwardsByShop(Long id, Long shopId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
        String sql = "from Award as a where 1=1 and quantity > 0 and a.exclusionDate is null and a.store.shopping.id = :shopId and a.store.exclusionDate is null";
        
        if (id > 0) {
        	sql += " and a.id not in (select pa.award.id from PersonAward as pa where pa.person.id = :id)";
        }
        
		Query query = session.createQuery(sql);
		
		if (id > 0) {
			query.setParameter("id", id);
		}
		
		query.setParameter("shopId", shopId);
		
		List<Award> awards = query.list();
		return awards;
	}
	
	private List<Award> findAwardsByStore(Long id, Long storeId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
        String sql = "from Award as a where 1=1 and quantity > 0 and a.exclusionDate is null and a.store.id = :shopId and a.store.exclusionDate is null";
        
        if (id > 0) {
        	sql += " and a.id not in (select pa.award.id from PersonAward as pa where pa.person.id = :id)";
        }
        
		Query query = session.createQuery(sql);
		
		if (id > 0) {
			query.setParameter("id", id);
		}
		
		query.setParameter("shopId", storeId);
		
		List<Award> awards = query.list();
		return awards;
	}
	
	private List<PersonAward> findAwardsByUser(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
        String sql = "from PersonAward as pa where pa.person.id = :id";
        
		Query query = session.createQuery(sql);
		
		
		query.setParameter("id", id);
		
		List<PersonAward> awards = query.list();
		return awards;
	}
	
	class ERROR_AWARD {
		public String title;
		public String code;
		public String message;
		public Double points;
		
		public ERROR_AWARD(String code, Double points) {
			switch (code) {
				case "100":
					this.code = code;
					title = "Sucesso";
					message = "Prêmio adquirido com sucesso";
					break;
				case "200":
					title = "Erro";
					this.code = code;
					message = "Você não possui pontos suficientes";
					break;
				case "300":
					title = "Erro";
					this.code = code;
					message = "Ocorreu algum problema no servidor";
					break;
			}
			this.points = points; 
		}
	}

}
