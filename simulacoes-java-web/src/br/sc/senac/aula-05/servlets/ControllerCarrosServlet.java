package br.sc.senac.aula05.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.sc.senac.aula05.dao.CarroDAO;
import br.sc.senac.aula05.dao.MontadoraDAO;
import br.sc.senac.aula05.entity.Carro;
import br.sc.senac.aula05.entity.Montadora;
import br.sc.senac.aula05.filter.FiltroCarro;

/**
 * Servlet implementation class ControllerCarrosServlet
 */
@WebServlet("/controllerCarros")
public class ControllerCarrosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControllerCarrosServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");
		String mensagemRetorno = "";
		String paginaDestino = "";

		MontadoraDAO montadoraDao = new MontadoraDAO();

		if (acao.equals("inserirMontadora")) {
			String nomeMontadora = request.getParameter("nome");
			String paisMontadora = request.getParameter("pais");

			Montadora montadora = new Montadora(nomeMontadora, paisMontadora);

			int idMontadora = montadoraDao.inserir(montadora);
			mensagemRetorno = "Id da montadora inserida: " + idMontadora;
		}

		if (acao.equals("alterarMontadora")) {
			String nomeMontadora = request.getParameter("nome");
			String paisMontadora = request.getParameter("pais");
			int id = Integer.parseInt(request.getParameter("id"));

			Montadora montadora = new Montadora(nomeMontadora, paisMontadora);
			montadora.setId(id);

			boolean sucesso = montadoraDao.atualizar(montadora);
			mensagemRetorno = sucesso ? "Montadora atualizada" : "Erro ao atualizar montadora";
		}

		if (acao.equals("excluirMontadora")) {
			// Converte o parâmetro "id" String para inteiro
			int id = Integer.parseInt(request.getParameter("id"));
			montadoraDao.excluir(id);
			mensagemRetorno = "Montadora com Id: " + id + " foi excluída";
		}

		if (acao.equals("consultarMontadoras")) {
			
			int id = 0;
			if(!request.getParameter("id").isEmpty()) {
				id = Integer.parseInt(request.getParameter("id"));
			}
			String pais = request.getParameter("pais");

			ArrayList<Montadora> montadoras = new ArrayList<>();

			if (id > 0) {
				Montadora m = montadoraDao.obterPorId(id);
				montadoras.add(m);
			} else if (pais != null && !pais.isEmpty()) {
				montadoras = montadoraDao.listarPorPais(pais);
			} else {
				montadoraDao.listar();
			}

			for (Montadora m : montadoras) {
				mensagemRetorno += "Montadora (" + m.getId() + "): " + m.getNome() + ". País: " + m.getPais() + "\n";
			}

		}
		
		//ControllerCarrosServlet
		if (acao.equals("consultarCarros")) {
			//Criar o filtro de carros a partir dos parâmetros da requisição
			FiltroCarro filtro = criarFiltroCarros(request);
			
			CarroDAO carroDAO = new CarroDAO();
			ArrayList<Carro> carros = carroDAO.listarPorFiltro(filtro);
			
			//Atributo para armazenar o resultado da consulta
			request.setAttribute("listaCarros", carros);
			
			//Encaminha para a página seguinte
			paginaDestino = "/aula06/exemplo2.jsp";
		}
		
		//Encaminhar para uma página JSP
		request.getRequestDispatcher(paginaDestino).forward(request, response);

	}

	private FiltroCarro criarFiltroCarros(HttpServletRequest request) {
		FiltroCarro filtro = new FiltroCarro();
		
		String modelo = request.getParameter("modelo");
		String paisMontadora = request.getParameter("paisMontadora");
		String montadora = request.getParameter("montadora");
		Double valorInicial = 0.0;
		Double valorFinal = 0.0;
		int anoInicial = 0;
		int anoFinal = 0;
		
		if(request.getParameter("valorInicial") != null && !request.getParameter("valorInicial").isEmpty()
				&& !request.getParameter("valorFinal").isEmpty()) {
			valorInicial = Double.parseDouble(request.getParameter("valorInicial"));
			valorFinal = Double.parseDouble(request.getParameter("valorFinal"));
		}
		
		if(request.getParameter("anoInicial") != null && !request.getParameter("anoInicial").isEmpty()
				&& !request.getParameter("anoFinal").isEmpty()) {
			anoInicial = Integer.parseInt(request.getParameter("anoInicial"));
			anoFinal = Integer.parseInt(request.getParameter("anoFinal"));
		}
		
		filtro.setModelo(modelo);
		filtro.setNomeMontadora(montadora);
		filtro.setPaisMontadora(paisMontadora);
		filtro.setValorInicial(valorInicial);
		filtro.setValorFinal(valorFinal);
		filtro.setAnoInicial(anoInicial);
		filtro.setAnoFinal(anoFinal);
		
		return filtro;
	}
}
