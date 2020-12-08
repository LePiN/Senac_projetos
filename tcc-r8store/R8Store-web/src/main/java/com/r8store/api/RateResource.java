package com.r8store.api;


import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.r8store.model.dao.RatingDAO;
import com.r8store.model.entity.Rating;

@Path("/rate")
public class RateResource {
	
	@GET
    public Response findRate(@QueryParam("token") String token) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		RatingDAO rDAO = new RatingDAO();
		Rating r = rDAO.findByToken(token);
		
		if (r != null && r.getPerson() == null) {
			return Response.status(200).entity("Token encontrado").build();			
		}
		
		return Response.status(404).entity("Não foi possível encontrar um token").build();
    }
	
	@GET
	@Path("/code/{hash}")
	@Produces(MediaType.APPLICATION_JSON)
    public Response findRateByHash(@PathParam("hash") String token) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		RatingDAO rDAO = new RatingDAO();
		Rating r = rDAO.findByToken(token);
		
		if (r != null) {
			return Response.status(200).entity(r).build();			
		}
		
		return Response.status(404).entity("Ocorreu algum problema na consulta da avaliação").build();
    }

}

