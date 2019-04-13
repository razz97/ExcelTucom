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
				<%
					}
				%>
			</select>
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
						Weight[] weights = Controller.getInstance().getWeights(sheet);
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
							value="<%=weight.getValue()%>" onChange="updateNotaFinal()" /></td>
						<td><input type="hidden" name="sheet"
							value="<%=activeSheet%>" /> <input type="hidden" name="column"
							value="<%=weight.getCellIndex()%>" />
							<button type="button"

								onclick="window.location.href ='/ExcelTucom/config?sheet=<%=activeSheet%>&delete=true&column=<%=weight.getCellIndex()%>'">
								<span aria-hidden="true">&times;</span>
							</button>
							<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" data-whatever="<%= weight.getName() %>">Add column</button></td>
					</tr>
					<%
								}
							}
					%>
					<tr>
						<td colspan="3"><input type="submit"
							class="btn btn-primary form-control" value="SAVE" /></td>
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
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">New message</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form>
						<div class="form-group">
							<label for="recipient-name" class="col-form-label">Recipient:</label>
							<input type="text" class="form-control" id="recipient-name">
						</div>
						<div class="form-group">
							<label for="message-text" class="col-form-label">Message:</label>
							<textarea class="form-control" id="message-text"></textarea>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary">Send message</button>
				</div>
			</div>
		</div>
	</div>
	<script>
		function updateNotaFinal() {
			var count = 0;
			$(".weight").each((i,obj) => count += parseFloat(obj.value));
			$("#notaFinal").val(count);
		}
		$('#exampleModal').on('show.bs.modal', function (event) {
			  var button = $(event.relatedTarget) // Button that triggered the modal
			  var recipient = button.data('whatever') // Extract info from data-* attributes
			  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
			  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
			  var modal = $(this)
			  modal.find('.modal-title').text('Opened: ' + recipient)
			  modal.find('.modal-body input').val(recipient)
			})
	</script>
</body>
</html>