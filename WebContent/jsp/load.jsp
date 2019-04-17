<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<jsp:include page="head.html" />
<body>
	<jsp:include page="navigation.jsp" />
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-2"></div>
			<h1 class="col-md-8" style="text-align: center">Select an existing excel file</h1>
			<div class="col-md-2"></div>
		</div>
		<div class="form-group row">
			<div class="col-md-2"></div>
			<select class="col-md-8 form-control">
			<% List<String> files = (List<String>) request.getAttribute("files"); 
			   for (String file : files) {   
			%>
				<option><%= file %></option>
			<% } %>
			</select>
			<div class="col-md-2"></div>
		</div>
		<div class="row">
			<div class="col-md-2"></div>
			<h1 class="col-md-8" style="text-align: center">Upload an excel file</h1>
			<div class="col-md-2"></div>
		</div>
		<form class="row" id="form" method="POST" enctype="multipart/form-data">
			<div class="col-md-2"></div>
			<input type="file" name="excel" id="inputFile">
			<input type="submit">
			<div class="col-md-2"></div>
		</form>
	</div>
	<script>
		$("#form").submit((submitEvent) => {
	        switch ($("#inputFile").val().replace(/^.*\./, '')) {
	            case 'xls':
	            case 'xlsx':
			        break;
	            default:
	            	submitEvent.preventDefault();
	                alert("File is invalid, it must have an extension .xls or .xlsx");
	        }
	        
 	 	});
	</script>
</body>
</html>