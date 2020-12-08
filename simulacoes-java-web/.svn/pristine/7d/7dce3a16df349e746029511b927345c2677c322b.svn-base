package br.sc.senac.aula06.bo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.sc.senac.aula05.dao.CarroDAO;
import br.sc.senac.aula05.entity.Carro;

public class ObterCarro implements Acao {

	public String executar(HttpServletRequest request, HttpServletResponse response) {
		CarroDAO carroDAO = new CarroDAO();
		int idCarro = Integer.parseInt(request.getParameter("idCarro"));
		Carro carro = carroDAO.obterPorId(idCarro);

		// Coloca o objeto consultado como atributo da requisição
		request.setAttribute("carro", carro);

		// Encaminha para a página de detalhe/edição
		return "/aula06/edicaoCarro.jsp";
	}

}
