<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<jsp:include page="head.html" />
	<body>
		<div class="alert alert-danger" role="alert">
			<%= (String) request.getAttribute("error") %><br>
			<a href="/ExcelTucom">Main page</a>
		</div>
	</body>
</html>