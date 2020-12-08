<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>Aula 06 - Front Controller (edição de um carro)</title>
	</head>
	<body>
		<form action="/DW20172/Controller?classe=AlterarCarro" method="POST">
			<fieldset>
				<legend>Dados do Carro:</legend>
				<input type="text" name="idcarro" value="${carro.id}" hidden>
				<label for="modelo">Modelo </label><input type="text" name="modelo" value="${carro.modelo}" required><br> 
				<label for="ano">Ano </label><input type="text" name="ano" value="${carro.ano}" required>
				<label for="valor">Valor </label><input type="number" name="valor" value="${carro.valor}" required>
			</fieldset>
			<fieldset>
				<legend>Montadora:</legend>
				<input type="text" name="idmontadora" value="${carro.montadora.id}" hidden>
				<label for="nomemontadora">Nome </label><input type="text" name="nomemontadora" value="${carro.montadora.nome}"><br>
				<label for="paismontadora">País </label><input type="text" name="paismontadora" value="${carro.montadora.pais}"><br> 
				<input type="SUBMIT" value="Atualizar carro"><br>
			</fieldset>
		</form>
	</body>
</html>