<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>

<%--Esto es una directiva para utilizar la clase Date --%>
<%@ page import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type"	content="text/html; charset=ISO-8859-1">
<title>Main Page</title>
</head>
<body>
	<%
		for (int i = 0; i < 10; i++) {
			System.out.println("Contando por consola: " + i);
			out.println("Contando hasta 10, vamos por el: " + i + "<br/>");
		}
	%>
	Bienvenido!
	<br>
	<%--Comentarios: Esto es un comentario en JSP --%>

	<%--Esto es un scriptlet JSP 
	<%
		System.out.println();
	%>
	<%
		out.print("Hola mundo desde JSP");
	%>
	<br>--%>
	<%--Esto es una expresión en JSP 
	<%="Esto es una expresión"%>
	<br>--%>
	<%--Se declara una directiva para utilizar la clase Date <%@ page import="java.util.Date"  %> 
	<%
		Date d = new Date();
		out.println("Fecha Actual: " + d);
	%>
	<br>--%>
	<%-- Declaraciones en JSP 
	<%!public static int contador = 7;%>
	<%
		out.println("Valor de la variable contador: " + contador);
	%>
	<br>--%>
	<%--Navegador y Versión del Sistema Operativo 
	<%
		out.println(request.getHeader("USER-AGENT") + "<br/>");
	%>
	--%>
	<%--Asi se imprime una Map 
	<%="Asi se imprime una Map"%>
	<table>
		<c:forEach items="${themeList}" var="entry">
			<tr>
				<td><c:out value="${entry.key}" /></td>
				<td><c:out value="${entry.value}" /></td>
			</tr>
		</c:forEach>
	</table>
	--%>
	<%--Asi se imprime una matriz con librerias 
	<%="Asi se imprime una matriz con librerias"%>
	<table>
		<c:forEach items="${matriz}" var="entry">
			<tr>
				<c:forEach items="${entry}" var="entryC">
					<td><c:out value="${entryC}" /></td>
				</c:forEach>
			</tr>
		</c:forEach>
	</table>
	--%>
	<%--Asi se imprime una array con java 
	<%="Asi se imprime una array con java"%>
	<%@page import="java.util.ArrayList"%>
	<%@page import="java.util.Iterator"%> 
	<%
		ArrayList<String> list = (ArrayList<String>) request.getAttribute("list");
	%>

	 <%  
		Iterator<String> iterator = list.iterator();
		while (iterator.hasNext()) {    
			System.out.println(iterator.next());
		}
	 %>

	<button type="button" onclick="">Click me</button>--%>
</body>
</html>