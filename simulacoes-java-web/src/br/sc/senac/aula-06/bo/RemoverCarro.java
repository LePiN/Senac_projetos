package br.sc.senac.aula06.bo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.sc.senac.aula05.dao.CarroDAO;

public class RemoverCarro implements Acao {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("idCarro"));

		//Remover carro dado um id
		CarroDAO dao = new CarroDAO();
		boolean removeu = dao.excluir(id);
		
		String mensagem;
		if(removeu) {
			mensagem = "Carro de id (" + id + ") foi removido";  
		}else {
			mensagem = "Erro ao remover carro";
		}
		
		request.setAttribute("msg", mensagem);
		
		return "/aula06/listaCarros.jsp";
	}

}
