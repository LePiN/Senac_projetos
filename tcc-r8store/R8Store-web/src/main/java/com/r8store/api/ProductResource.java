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

import com.r8store.model.dao.ProductDAO;
import com.r8store.model.entity.Product;
import com.r8store.util.HibernateUtil;

@Path("/product")
public class ProductResource {

	@GET
	@Path("/products/{productId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getProductsByStore(@PathParam("productId") Long id, @QueryParam("name") String name, @Context HttpServletRequest request) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		ProductDAO pDAO = new ProductDAO();
		Product p = pDAO.findById(id);
		
		if (id == null || id == 0l || p == null) {
			return null;
		}
		return this.findProductsByStore(p.getStore().getId(), p.getId());
    }
	
	private List<Product> findProductsByStore(Long id, Long productId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
        String sql = "from Product as p where 1=1 and p.store.id = :id and p.id <> :productId and p.exclusionDate is null";
        
		Query query = session.createQuery(sql);
		query.setParameter("id", id);
		query.setParameter("productId", productId);
		
		List<Product> products = query.list();
		return products;
	}

}
