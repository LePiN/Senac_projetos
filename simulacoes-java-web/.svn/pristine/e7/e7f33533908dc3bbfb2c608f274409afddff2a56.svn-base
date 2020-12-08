<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- Importações das bibliotecas JSTL -->
<%@ taglib  uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Aula 06 - Exemplo 3 - JSTL</title>
	</head>
	<body>
		<fmt:setLocale value="pt-BR"/>
		<c:set var="total" value="1000.54"/>
		<p>
		     Dinheiros brasileiros: <fmt:formatNumber value="${total}"  type="currency"/>
		</p>
		
		<fmt:setLocale value="pt-PT"/>
		<p>
		     Dinheiros lusitanos: <fmt:formatNumber value="${total}"  type="currency"/>
		</p>
	</body>
</html>