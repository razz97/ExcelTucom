<nav class="navbar navbar-expand-lg navbar-light bg-light">	
<%
	String[] titles = (String[]) request.getAttribute("sheetNames");
	for (String title: titles) {
		out.println("<a class=\"navbar-brand\" href=\"/Exceltucom/ShowExcel?sheet=" + title + "\">"+title+"</a>");
	}
%>
</nav>