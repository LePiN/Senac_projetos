<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>Aula 06 - Front Controller (listagem de carros)</title>
	</head>
	<body>
		<h1>Lista de carros</h1>
		<a href="/DW20172/Controller?classe=ListarCarros">Listar</a>
		<table>
		<tr><th>Modelo/Montadora</th><th>Ano</th><th>Valor</th><th colspan="2">Ações</th></tr>
		<c:forEach items="${carros}" var="carro">
			<tr>
				<td>${carro.modelo} / ${carro.montadora.nome}</td> 
				<td>${carro.ano}</td>
				<td>${carro.valor}</td>
				<td><a href="/DW20172/Controller?classe=RemoverCarro&idCarro=${carro.id}">Remover</a></td>
				<td><a href="/DW20172/Controller?classe=ObterCarro&idCarro=${carro.id}">Editar</a></td>
			</tr>
		</c:forEach>
		</table>
		<p>${msg}</p>
	</body>
</html>