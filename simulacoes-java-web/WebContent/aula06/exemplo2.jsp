<%@page import="br.sc.senac.aula05.filter.FiltroCarro"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="br.sc.senac.aula05.dao.CarroDAO, br.sc.senac.aula05.entity.Carro" %> 
<%@ page import="java.util.ArrayList" %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Exemplo 2 - Listagem de Carros</title>
	</head>
	<body>
		<table>
			<tr>
				<th>Montadora</th><th>Modelo</th><th>Ações</th>
			</tr>
			<a href="/DW20172/controllerCarros?acao=consultarCarros">Listar carros</a>
			<c:forEach items="${listaCarros}" var="carro">
				<tr>
					<td><c:out value="${carro.montadora.nome}"></c:out></td>
		 			<td><c:out value="${carro.modelo}"></c:out></td>
		 			<td>
		 				<button onclick="/DW20172/controllerCarros?acao=editarCarro&id=${carro.id}">Editar</button>
		 				<button>Remover</button>
		 			</td>
				</tr>
			</c:forEach>		
		</table>
	</body>
</html>