<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
   
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Aula 6 - Exemplo 3</title>
	</head>
	<body>
	
	<!-- 
		Crie uma página JSP que utilize EL, Core Tags e as JSTL Functions 
		Utilize a função fn_contains em conjunto com a tag <c_if> para verificar 
		se a String “acbdabcad” contém a substring “abc”, 
			em caso afirmativo escrever “Contém” como resposta -->
	<c:if test="${fn:contains('acbdabcad','abc')}">
		<p>Contém!</p>
	</c:if>
	
	<!-- 
		Utilize a função fn:split com a String “1;2;3;4” para obter um array, 
		depois percorrer o array com <c_forEach> para realizar a soma dos valores 
		do array e mostrá-la como resultado
	-->
	<c:set var="numeros" value="${fn:split('1;2;3;4', ';')}"/>
	
	<c:forEach items="${numeros}" var="numero">
		<c:set var="total" value="${total + numero}"/>
	</c:forEach>
	
	Total: <c:out value="${total}"/>	 
	</body>
</html>