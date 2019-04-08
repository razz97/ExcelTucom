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
			<select class="col-md-7" onChange="window.location.href ='/ExcelTucom/config?sheet=' + this.options[this.selectedIndex].value" class="form-control">
				<%  SheetDTO[] sheets = (SheetDTO[]) request.getAttribute("sheetNames");
					int activeSheet = (int) request.getAttribute("configActiveSheet");
					for (SheetDTO tmpSheet : sheets) { %>
						<option value="<%=tmpSheet.getIndex()%>"  <%=tmpSheet.getIndex() == activeSheet? "selected" : "" %>>
							<%= tmpSheet.getTitle() %>
						</option>
				<% } %>
			</select>
			<div class="col-md-2"></div>
		</div>
		<div class="row">
			<div class="col-md-2"></div>
				<form class="col-md-8" method="POST">
				<% Sheet sheet = (Sheet) request.getAttribute("sheet");
							if (sheet != null) { %>
					<table class="table table-striped table-bordered">
						<tr><th>Field name</th><th>Shorthand</th><th>Value</th></tr>
							<% 
								Weight[] weights = Controller.getInstance().getWeights(sheet);
								boolean isNotaFinal;
								for (Weight weight: weights) {
									if (weight != null) {
										isNotaFinal = weight.getName().equals("NOTA FINAL");%>
								<tr>
									<td>
										<input name="name-<%=weight.getCellIndex()%>" 
											class="form-control"
											<%=isNotaFinal ? "disabled" : ""%> 
											value="<%=weight.getName()%>"/>
									</td>
									<td>
										<input name="short-<%=weight.getCellIndex()%>" 
											class="form-control" 
											<%=isNotaFinal ? "disabled" : ""%> 
											value="<%=weight.getShorthand()%>"/>
									</td>		
									<td>
										<input name="value-<%=weight.getCellIndex()%>" 
											type="number" 
											class="form-control <%=isNotaFinal ? "" : "weight" %>" 
											<%=isNotaFinal ? "disabled id='notaFinal'" : ""%> 
											value="<%=weight.getValue()%>"
											onChange="updateNotaFinal()" />
									</td>		
								</tr>				
							<% }} %>
							<tr><td colspan="3"><input type="submit" class="btn btn-primary form-control" value="SAVE" /></td></tr>
							<% } %>
					</table>
					<input type="hidden" name="sheet" value="<%= activeSheet %>" />
				</form>
			<div class="col-md-2"></div>
		</div> 
	</div>
	<script>
		function updateNotaFinal() {
			var count = 0;
			$(".weight").each((i,obj) => count += parseInt(obj.value));
			$("#notaFinal").val(count);
		}
	</script>
</body>
</html>