package br.sc.senac.aula06.bo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.sc.senac.aula05.dao.CarroDAO;
import br.sc.senac.aula05.entity.Carro;
import br.sc.senac.aula05.entity.Montadora;

public class AlterarCarro implements Acao {

	public String executar(HttpServletRequest request, HttpServletResponse response) {

		Carro carro = new Carro();
		carro.setId(Integer.parseInt(request.getParameter("idcarro")));
		carro.setModelo(request.getParameter("modelo"));
		carro.setAno(Integer.parseInt(request.getParameter("ano")));
		carro.setValor(Double.parseDouble(request.getParameter("valor")));

		Montadora montadora = new Montadora();
		montadora.setId(Integer.parseInt(request.getParameter("idmontadora")));
		montadora.setNome(request.getParameter("nomemontadora"));
		montadora.setPais(request.getParameter("paismontadora"));

		carro.setMontadora(montadora);

		CarroDAO carroDAO = new CarroDAO();

		if (carroDAO.atualizar(carro) == true) {
			request.setAttribute("msg", "Carro atualizado com sucesso, ID: " + carro.getId());
		} else {
			request.setAttribute("msg", "Erro ao atualizar carro, ID: " + carro.getId());
		}

		ListarCarros acaoListarCarros = new ListarCarros();
		return acaoListarCarros.executar(request, response);
	}
}
