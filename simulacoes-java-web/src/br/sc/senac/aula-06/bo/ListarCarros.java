package br.sc.senac.aula06.bo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.sc.senac.aula05.dao.CarroDAO;
import br.sc.senac.aula05.entity.Carro;

public class ListarCarros implements Acao {

	public String executar(HttpServletRequest request, HttpServletResponse response) {
		CarroDAO carroDAO = new CarroDAO();
		List<Carro> carros = carroDAO.listar();
		request.setAttribute("carros", carros);
		return "/aula06/listaCarros.jsp";
	}

}
