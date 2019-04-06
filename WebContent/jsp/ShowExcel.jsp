<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="org.apache.poi.ss.usermodel.CellType"%>
<%@page import="org.apache.poi.ss.usermodel.Cell"%>
<%@page import="org.apache.poi.ss.usermodel.Row"%>
<%@page import="java.util.Iterator"%>
<%@page import="org.apache.poi.ss.usermodel.Sheet"%>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>

<%--Esto es una directiva para utilizar la clase Date --%>
<%@ page import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
	<head>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" >
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" ></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" ></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" ></script>
		<meta http-equiv="Content-Type"	content="text/html; charset=ISO-8859-1">
		<title>ExcelTucom</title>
	</head>
	<body>
		<jsp:include page="navigation.jsp" />
		<div class="container">
		<h1>ShowExcel</h1>
		<table class="table table-striped table-bordered">
			<%
				Sheet sheet = (Sheet) request.getAttribute("sheet");
				Iterator<Row> rowIterator = sheet.iterator();
				while (rowIterator.hasNext()) {
					Iterator<Cell> cellIterator = rowIterator.next().cellIterator();
					out.println("<tr>");
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						CellType type = cell.getCellType();
						switch (type) {
							case NUMERIC : 
								out.println("<td>" + cell.getNumericCellValue() + "</td>");
								break;
							case STRING : 
								out.println("<td>" + cell.getRichStringCellValue() + "</td>");
								break;
							default:
								out.println("<td></td>");
						}
					}
					out.println("</tr>");
				}
			%>
		</table>
		</div>

	</body>
</html>