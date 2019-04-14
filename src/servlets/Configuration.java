package servlets;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import exception.InvalidActionException;
import exception.InvalidActionException.Tipo;

/**
 * Servlet implementation class Configuration
 */
@WebServlet("/config")
public class Configuration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String info;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Configuration() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("delete") != null) {
			doDelete(request, response); return;
		} if (request.getParameter("update") != null) {
			doPut(request, response); return;
		}
		// GET VALUES
		try {
			Controller controller = Controller.getInstance();
			int sheetNumber =  request.getParameter("sheet") == null ? 0 : Integer.parseInt(request.getParameter("sheet"));
			request.setAttribute("info", info);
			info = null;
			request.setAttribute("sheet", controller.getSheet(sheetNumber));
			request.setAttribute("sheetNames", controller.getDTOSheets());
			request.setAttribute("sheetActive", -1);
			request.setAttribute("configActiveSheet", sheetNumber);
			getServletContext().getRequestDispatcher("/jsp/configuration.jsp").forward(request, response);
		} catch (InvalidActionException ex) {
			errorResponse(request, response, ex.getMessage());
		} catch (NumberFormatException ex) {
			errorResponse(request, response, Tipo.SHEET_PARAM_NOT_INTEGER.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ADD COLUMN
		try {
			Controller controller = Controller.getInstance();
			int sheetNum = Integer.parseInt(request.getParameter("sheet"));
			double value = Double.parseDouble(request.getParameter("value"));
			String name = request.getParameter("name");
			String shorthand = request.getParameter("short");
			controller.addColumn(sheetNum, name, shorthand, value);
			controller.commit();
			info = "Column " + name + " added successfully";
			response.sendRedirect("/ExcelTucom/config?sheet=" + sheetNum);
		} catch (InvalidActionException ex) { 
			errorResponse(request, response, ex.getMessage()); 
		} catch (NumberFormatException | NullPointerException ex) {
			errorResponse(request, response, Tipo.WRONG_POST_PARAM.getMessage());
		}
	}
	
	private void handlePostParam(HttpServletRequest request, String paramName, int sheetNum) throws InvalidActionException {
		if (paramName.equals("sheet")) return;
		String[] splitParamName = paramName.split("-");
		int rowNum;
		switch (splitParamName[0]) {
			case "name": rowNum = 0; break;
			case "short": rowNum = 2; break;
			case "value": rowNum = 1; break;
			default: throw new InvalidActionException(Tipo.WRONG_POST_PARAM);
		}
		Controller.getInstance().updateCell(sheetNum, rowNum, Integer.parseInt(splitParamName[1]),  request.getParameter(paramName));
	}
	
	protected void errorResponse(HttpServletRequest request, HttpServletResponse response, String error) throws ServletException, IOException {
		request.setAttribute("error", error);
		getServletContext().getRequestDispatcher("/jsp/error.jsp").forward(request, response);
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// DELETE COLUMN
		try {
			Controller controller = Controller.getInstance();
			int sheetNum = Integer.parseInt(request.getParameter("sheet"));
			int column = Integer.parseInt(request.getParameter("column"));
			controller.deleteColumn(sheetNum, column);
			controller.commit();
			info = "Row deleted successfully.";
			request.setAttribute("deleted", true);
			response.sendRedirect("/ExcelTucom/config?sheet=" + sheetNum);
		} catch (InvalidActionException ex) { 
			errorResponse(request, response, ex.getMessage()); 
		} catch (NumberFormatException | NullPointerException ex) {
			errorResponse(request, response, Tipo.WRONG_DELETE_PARAM.getMessage());
		}
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// UPDATE VALUES
		try {
			Controller controller = Controller.getInstance();
			int sheetNum = Integer.parseInt(request.getParameter("sheet"));
			Enumeration<String> paramNames = request.getParameterNames();
			while (paramNames.hasMoreElements()) 
				handlePostParam(request, paramNames.nextElement(), sheetNum);			
			 controller.commit();
			 info = "Sheet updated successfully";
			 response.sendRedirect("/ExcelTucom/config?sheet=" + sheetNum);
		} catch (InvalidActionException ex) { 
			errorResponse(request, response, ex.getMessage()); 
		} catch (NumberFormatException | NullPointerException ex) {
			errorResponse(request, response, Tipo.WRONG_POST_PARAM.getMessage());
		}
	}
}
