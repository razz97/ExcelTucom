<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Resumen</title>
</head>
<body>
	<%
		for (int i = 0; i < 10; i++) {
			System.out.println("Contando por consola: " + i);
			out.println("Contando hasta 10, vamos por el: " + i + "<br/>");
		}
	%>
</body>
</html>