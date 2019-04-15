<%@page import="java.util.List"%>
<%@page import="model.SheetDTO"%>

<% SheetDTO[] sheets = (SheetDTO[]) request.getAttribute("sheetNames");
int sheetActive = (int) request.getAttribute("sheetActive");
String info = (String) request.getAttribute("info");
if (sheets != null) { %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<a class="navbar-brand" href="/ExcelTucom/excel?sheet=0">ExcelTucom</a>
	<div class="collapse navbar-collapse" id="navbarNav">
		<ul class="navbar-nav">
			<li class="nav-item <%=sheetActive == -2 ? "active" : ""%>"><a class="nav-link" href="/ExcelTucom/load">Load Excel</a></li>
			<li class="nav-item <%=sheetActive == -1 ? "active" : ""%>"><a class="nav-link" href="/ExcelTucom/config">Configuration</a></li>
			<% for (SheetDTO sheet : sheets) { %>
					<li class="nav-item <%=sheetActive == sheet.getIndex() ? "active" : "" %>">
						<a class="nav-link" href="/ExcelTucom/excel?sheet=<%=sheet.getIndex()%>"><%=sheet.getTitle()%></a>
					</li>
			<% } %>
		</ul>
	</div>
</nav>
<% } if (info != null) { %>
	<div class="alert alert-success" role="alert"><%=info%></div>
<% } %>
