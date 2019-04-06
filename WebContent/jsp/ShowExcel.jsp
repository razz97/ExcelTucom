<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="org.apache.poi.hslf.model.Sheet"%>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>

<%--Esto es una directiva para utilizar la clase Date --%>
<%@ page import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
	<head>
		<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
		<meta http-equiv="Content-Type"	content="text/html; charset=ISO-8859-1">
		<title>ExcelTucom</title>
	</head>
	<body>
		<jsp:include page="navigation.jsp" />
		<h1>ShowExcel</h1>
		<table class="table table-striped table-bordered">
			<tr>
			<%
				Sheet sheet = (Sheet) request.getAttribute("sheet");
				sheet.get
				for (sheet)
			%>
			</tr>
		</table>
	</body>
</html>