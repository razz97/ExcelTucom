<%@page import="model.SheetDTO"%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">	
	<a class="navbar-brand" href="/ExcelTucom/Configuration">Configuration</a>
	<%
	SheetDTO[] sheets = (SheetDTO[]) request.getAttribute("sheetNames");
	for (SheetDTO sheet: sheets) {
		out.println("<a class=\"navbar-brand\" href=\"/Exceltucom/ShowExcel?sheet=" + sheet.getIndex() + "\">" + sheet.getTitle() + "</a>");
	}
	%>
</nav>