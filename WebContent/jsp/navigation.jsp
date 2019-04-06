<%@page import="model.SheetDTO"%>
<%
	SheetDTO[] sheets = (SheetDTO[]) request.getAttribute("sheetNames");
	int sheetActive = (int) request.getAttribute("sheetActive");
%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<a class="navbar-brand" href="/ExcelTucom/ShowExcel?sheet=0">ExcelTucom</a>
	<div class="collapse navbar-collapse" id="navbarNav">
		<ul class="navbar-nav">
			<li class="nav-item <%= sheetActive == -1 ? "active" : "" %>"><a class="nav-link" href="/ExcelTucom/Configuration">Configuration</a></li>
			<%
				for (SheetDTO sheet : sheets) {
					out.println("<li class=\"nav-item " + (sheetActive == sheet.getIndex() ? "active" : "") +
							"\"><a class=\"nav-link\" href=\"/ExcelTucom/ShowExcel?sheet=" + sheet.getIndex() + 
							"\">" + sheet.getTitle() + "</a></li>");
				}
			%>
		</ul>
	</div>
</nav>