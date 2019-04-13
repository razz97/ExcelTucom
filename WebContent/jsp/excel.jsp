<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="org.apache.poi.ss.usermodel.Cell"%>
<%@page import="org.apache.poi.ss.usermodel.CellType"%>
<%@page import="org.apache.poi.ss.usermodel.Row"%>
<%@page import="java.util.Iterator"%>
<%@page import="org.apache.poi.ss.usermodel.Sheet"%>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<jsp:include page="head.html" />
<body>
	<jsp:include page="navigation.jsp" />
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-2"></div>
			<h1 class="col-md-8" style="text-align: center">ShowExcel</h1>
			<div class="col-md-2"></div>
		</div>
		<div class="row">
			<div class="col-md-2"></div>
			<table class="table table-striped table-bordered col-md-8">
				<%
					Sheet sheet = (Sheet) request.getAttribute("sheet");
					if (sheet != null) {
						Iterator<Row> rowIterator = sheet.iterator();
						rowIterator.next();
						rowIterator.next();
						while (rowIterator.hasNext()) {
							Iterator<Cell> cellIterator = rowIterator.next().cellIterator();
							out.println("<tr>");
							while (cellIterator.hasNext()) {
								Cell cell = cellIterator.next();
								CellType type = cell.getCellType();
								out.println("<td>");
									switch (type) {
									case NUMERIC:
									case FORMULA:
										out.println(String.format("%.2f", cell.getNumericCellValue()));
										break;
									case STRING:
										out.println(cell.getRichStringCellValue());
										break;
									default:
								}
								out.println("</td>");
							}
							out.println("</tr>");
						}
					}
				%>
			</table>
			<div class="col-md-2"></div>
		</div>
	</div>
</body>
</html>