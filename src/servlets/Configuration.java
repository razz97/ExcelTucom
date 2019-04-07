package servlets;

import java.io.IOException;
import java.util.Enumeration;

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

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Configuration() {
		controller = Controller.getInstance();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String paramSheet = request.getParameter("sheet");
		int sheetNumber = 0;
		Sheet sheet = null;
		Exception exception = null;
		if (paramSheet != null) {
			try {
				sheetNumber = Integer.parseInt(paramSheet);			
			} catch (NumberFormatException e) {
				exception = new InvalidActionException(Tipo.SHEET_PARAM_NOT_INTEGER);
			}
		}
		try {
			sheet = controller.getSheet(sheetNumber);
		} catch (InvalidActionException e) {
			exception = e;
		}
		if (exception == null)
			request.setAttribute("sheet", sheet);
		else
			request.setAttribute("error", exception.getMessage());
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
		Enumeration<String> parameterNames = request.getParameterNames();
		int sheetNum = 0;
		try {
			sheetNum = Integer.parseInt(request.getParameter("sheet"));
		} catch(NumberFormatException e) {
			e.printStackTrace();
		}
		while (parameterNames.hasMoreElements()) {
			String paramName = parameterNames.nextElement();
			if (paramName.equals("sheet")) continue;
			String paramValue = request.getParameter(paramName);
			String[] splitParamName = paramName.split("-");
			String weightType = splitParamName[0];
			int weightIndex = 0;
			try {
				weightIndex = Integer.parseInt(splitParamName[1]);
			} catch(NumberFormatException e ) {
				e.printStackTrace();
			}
			int rowNum = 0;
			switch (weightType) {
				case "name": rowNum = 0; break;
				case "short": rowNum = 2; break;
				case "value": rowNum = 1;break;
			}
			controller.updateCell(sheetNum, rowNum, weightIndex, paramValue);
		}
		try {
			controller.commit();
		} catch (InvalidActionException e) {
			e.printStackTrace();
		}
		doGet(request, response);
	}

}
