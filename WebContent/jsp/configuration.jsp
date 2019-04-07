<%@page import="model.Weight"%>
<%@page import="controller.Controller"%>
<%@page import="org.apache.poi.ss.usermodel.Sheet"%>
<%@page import="model.SheetDTO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<jsp:include page="head.html" />
<body>
	<jsp:include page="navigation.jsp" />
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-2"></div>
			<h1 class="col-md-8" style="text-align: center">Configuration</h1>
			<div class="col-md-2"></div>
		</div>
		<div class="form-group row">
			<div class="col-md-2"></div>
			<label for="selectSheet" class="col-md-1">Select a sheet:</label> 
			<select class="col-md-7" id="selectSheet" onChange="onSelectSheet()" class="form-control">
				<%
					SheetDTO[] sheets = (SheetDTO[]) request.getAttribute("sheetNames");
					int activeSheet = (int) request.getAttribute("configActiveSheet");
					for (SheetDTO tmpSheet : sheets) {
						out.println("<option value='" + tmpSheet.getIndex() + "' "+ (tmpSheet.getIndex() == activeSheet? "selected" : "") +">" + tmpSheet.getTitle() + "</option>");
					}
				%>
			</select>
			<div class="col-md-2"></div>
		</div>
		<div class="row">
		<div class="col-md-2"></div>
		<form class="col-md-8">
			<%
			Sheet sheet = (Sheet) request.getAttribute("sheet");
			if (sheet != null) {
				Weight[] weights = Controller.getInstance().getWeights(sheet);
				for (Weight weight: weights) {
					if (weight != null) {
			%>
					<div class="form-group row">
						<div class="col-md-6">
							<label for="name">Field name</label>
							<input id="name" class="form-control" value="<%= weight.getName()  %>" />
						</div>
						<div class="col-md-6">
							<label for="weight">Weight</label>
							<input id="weight" class="form-control" value="<%= weight.getValue()  %>" />
						</div>		
					</div>				
			<% }}} %>
		</form>
		<div class="col-md-2"></div>
		</div> 
	</div>
	<script>
		function onSelectSheet() {
			var select = document.getElementById("selectSheet");
			var selection = select.options[select.selectedIndex].value;
			window.location
					.replace("http://localhost:8080/ExcelTucom/config?sheet="
							+ selection);
		}
	</script>
</body>
</html>