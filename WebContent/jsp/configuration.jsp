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
			<h1 class="col-md-8" style="text-align: center">Configuration - <%= request.getAttribute("filename") %></h1>
			<div class="col-md-2"></div>
		</div>
		<div class="form-group row">
			<div class="col-md-2"></div>
			<label for="selectSheet" class="col-md-1">Select a sheet:</label> <select
				class="col-md-7"
				onChange="window.location.href ='/ExcelTucom/config?sheet=' + this.options[this.selectedIndex].value"
				class="form-control">
				<%
					SheetDTO[] sheets = (SheetDTO[]) request.getAttribute("sheetNames");
					int activeSheet = (int) request.getAttribute("configActiveSheet");
					for (SheetDTO tmpSheet : sheets) {
				%>
				<option value="<%=tmpSheet.getIndex()%>"
					<%=tmpSheet.getIndex() == activeSheet ? "selected" : ""%>>
					<%=tmpSheet.getTitle()%>
				</option>
				<% } %>
			</select>
			<div class="col-md-2"></div>
		</div>
		<div class="row">
			<div class="col-md-2"></div>
				<form class="col-md-8" method="POST">
					<input type="hidden" name="newSheet" value="true">
					<input type="text" name="name" placeholder="Sheet name" class="col-md-8" required>
					<input type="submit" class="col-md-3 btn btn-primary" style="margin: 20px;" value="Add new sheet">
				</form>
			<div class="col-md-2"></div>
		</div>
		<div class="row">
			<div class="col-md-2"></div>
			<form class="col-md-8" method="POST">
				<%
					Sheet sheet = (Sheet) request.getAttribute("sheet");
					if (sheet != null) {
				%>
				<table class="table table-striped table-bordered">
					<tr>
						<th>Field name</th>
						<th>Shorthand</th>
						<th>Value</th>
						<th></th>
					</tr>
					<%
						Weight[] weights = (Weight[]) request.getAttribute("weights");
							boolean isNotaFinal;
							for (Weight weight : weights) {
								if (weight != null && !weight.getName().equals("COMENTARIOS")) {
									isNotaFinal = weight.getName().equals("NOTA FINAL");
					%>
					<tr>
						<td><input name="name-<%=weight.getCellIndex()%>"
							class="form-control" <%=isNotaFinal ? "disabled" : ""%>
							value="<%=weight.getName()%>" /></td>
						<td><input name="short-<%=weight.getCellIndex()%>"
							class="form-control" <%=isNotaFinal ? "disabled" : ""%>
							value="<%=weight.getShorthand()%>" /></td>
						<td><input name="value-<%=weight.getCellIndex()%>"
							type="number"
							class="form-control <%=isNotaFinal ? "" : "weight"%>"
							<%=isNotaFinal ? "disabled id='notaFinal'" : ""%>
							value="<%=weight.getValue()%>" /></td>
						<td style="text-align: center"><input type="hidden"
							name="sheet" value="<%=activeSheet%>" />
							<button type="button" class="close" style="float: inherit"
								onclick="window.location.href ='/ExcelTucom/config?sheet=<%=activeSheet%>&delete=true&column=<%=weight.getCellIndex()%>'">
								<span aria-hidden="true">&times;</span>
							</button></td>
					</tr>
					<%
						}
							}
					%>
					<tr>
						<td colspan="4">
							<button type="button" class="btn btn-primary form-control" data-target="#addColumnModal" data-toggle="modal">ADD COLUMN</button>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<input type="hidden" name="update" value="true">
							<input type="submit" class="btn btn-success form-control" value="SAVE" />
						</td>
					</tr>

					
					<%
						}
					%>
				</table>
				<input type="hidden" name="sheet" value="<%=activeSheet%>" />
			</form>
			<div class="col-md-2"></div>
		</div>
	</div>
	<div class="modal fade" id="addColumnModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Add column</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<form method="POST" id="columnForm">
				<div class="modal-body">
						<input type="hidden" name="sheet" value="<%=activeSheet %>">
						<div class="form-group">
							<label for="name" class="col-form-label">Field name:</label>
							<input type="text" class="form-control" id="name" name="name" required>
						</div>
						<div class="form-group">
							<label for="short" class="col-form-label">Shorthand:</label>
							<input type="text" class="form-control" id="short" name="short" required>
						</div>
						<div class="form-group">
							<label for="value" class="col-form-label">Weight:</label>
							<input type="text" class="form-control" id="value" name="value" required>
						</div>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
					<button type="submit" class="btn btn-primary" id="btnSubmitColumn">Submit</button>
				</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>