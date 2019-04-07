<%@page import="model.SheetDTO"%>
<%
	SheetDTO[] sheets = (SheetDTO[]) request.getAttribute("sheetNames");
	int sheetActive = (int) request.getAttribute("sheetActive");
	String error = (String) request.getAttribute("error");
	if (sheets != null) {
%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<a class="navbar-brand" href="/ExcelTucom/excel?sheet=0">ExcelTucom</a>
	<div class="collapse navbar-collapse" id="navbarNav">
		<ul class="navbar-nav">
			<li class="nav-item <%= sheetActive == -1 ? "active" : "" %>"><a class="nav-link" href="/ExcelTucom/config">Configuration</a></li>
			<%
				for (SheetDTO sheet : sheets) {
					out.println("<li class=\"nav-item " + (sheetActive == sheet.getIndex() ? "active" : "") +
							"\"><a class=\"nav-link\" href=\"/ExcelTucom/excel?sheet=" + sheet.getIndex() + 
							"\">" + sheet.getTitle() + "</a></li>");
				}
			%>
		</ul>
	</div>
</nav>
<% 
} if (error != null) { 
		out.println("<div class=\"alert alert-danger\" role=\"alert\">");
		out.println(error + "<br>");
	  	out.println("<a href=\"/ExcelTucom\">Click here to go to the main page</a></div>");
} %>