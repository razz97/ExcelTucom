package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Sheet;

import controller.Controller;
import exception.InvalidActionException;
import exception.InvalidActionException.Tipo;

/**
 * Servlet implementation class Configuration
 */
@WebServlet("/config")
public class Configuration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Controller controller;
	private List<String> errors = new ArrayList<>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Configuration() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			controller = Controller.getInstance();
		} catch (InvalidActionException e) {
//			errors.add(e.getMessage());
//			getServletContext().getRequestDispatcher("/jsp/excel.jsp").forward(request, response);
//			return;
		}
		String paramSheet = request.getParameter("sheet");
		int sheetNumber = 0;
		Sheet sheet = null;
		if (paramSheet != null) {
			try {
				sheetNumber = Integer.parseInt(paramSheet);			
			} catch (NumberFormatException e) {
				errors.add(new InvalidActionException(Tipo.SHEET_PARAM_NOT_INTEGER).getMessage());
			}
		}
		try {
			sheet = controller.getSheet(sheetNumber);
		} catch (InvalidActionException e) {
			errors.add(e.getMessage());
		}
		if (errors.isEmpty())
			request.setAttribute("sheet", sheet);
		else
			request.setAttribute("errors", errors);
		request.setAttribute("sheetNames", controller.getDTOSheets());
		request.setAttribute("sheetActive", -1);
		request.setAttribute("configActiveSheet", sheetNumber);
		getServletContext().getRequestDispatcher("/jsp/configuration.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			controller = Controller.getInstance();
		} catch (InvalidActionException e) {
			// TODO: handle exception
		}
		Enumeration<String> parameterNames = request.getParameterNames();
		int sheetNum = 0;
		try {
			sheetNum = Integer.parseInt(request.getParameter("sheet"));
		} catch(NumberFormatException e) {
			errors.add(new InvalidActionException(Tipo.SHEET_PARAM_NOT_INTEGER).getMessage());
		}
		while (parameterNames.hasMoreElements()) {
			String paramName = parameterNames.nextElement();
			if (paramName.equals("sheet")) 
				continue;
			String paramValue = request.getParameter(paramName);
			String[] splitParamName = paramName.split("-");
			String weightType = splitParamName[0];
			int weightIndex = 0;
			try {
				weightIndex = Integer.parseInt(splitParamName[1]);
			} catch(NumberFormatException e ) {
				errors.add(new InvalidActionException(Tipo.CELL_INDEX_NOT_INTEGER).getMessage());
			}
			int rowNum = 0;
			switch (weightType) {
				case "name": rowNum = 0; break;
				case "short": rowNum = 2; break;
				case "value": rowNum = 1;break;
			}
			controller.updateCell(sheetNum, rowNum, weightIndex, paramValue);
		}
		try { controller.commit(); }
		catch (InvalidActionException e) { errors.add(e.getMessage()); }
		doGet(request, response);
	}

}
