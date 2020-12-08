package br.sc.senac.aula10.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.sc.senac.aula10.bo.PermissaoBO;
import br.sc.senac.aula10.entity.Pessoa;

/**
 * Senac/SC - Desenvolvimento para Web
 * 
 * Aula 10: Login e Session
 * 
 * Classe que modela um filtro para interceptar todas as requisições para o path
 * "/aula10/restrito", através do método doFilter
 * 
 * O registro do filtro e do path é realizado no web.xml
 * 
 * @author vilmar
 *
 */
public class FiltroLogin implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Pessoa pessoa = null;
		HttpSession sessao = ((HttpServletRequest) request).getSession();

		// Verifica se já existe pessoa na Session
		if (sessao != null) {
			pessoa = (Pessoa) sessao.getAttribute("pessoaLogada");
		}

		PermissaoBO permissaoBO = new PermissaoBO();
		// Verifica a permissao (código de permissão arbitrado --> consultar
		// posteriormente em um DAO ou BO)
		if (permissaoBO.temPermissao(pessoa)) {
			//Tem permissão: propaga a requisição para o destino original
			chain.doFilter(request, response);
		} else {
			//Sem permissão
			String contextPath = ((HttpServletRequest) request).getContextPath();
			((HttpServletResponse) response).sendRedirect(contextPath + permissaoBO.obterDestino(pessoa));
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

	public void destroy() {
	}
}