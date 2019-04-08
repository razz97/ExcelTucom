package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import exception.InvalidActionException;
import exception.InvalidActionException.Tipo;

/**
 * Servlet implementation class ShowExcel
 */
@WebServlet("/excel")
public class ShowExcel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Controller controller = Controller.getInstance();
			int sheetNumber = request.getParameter("sheet") == null ? 0 : Integer.parseInt(request.getParameter("sheet"));
			request.setAttribute("sheet", controller.getSheet(sheetNumber));
			request.setAttribute("sheetActive", sheetNumber);
			request.setAttribute("sheetNames", controller.getDTOSheets());
			getServletContext().getRequestDispatcher("/jsp/excel.jsp").forward(request, response);
		} catch(InvalidActionException ex) {
			errorResponse(request, response, ex.getMessage());
		} catch(NumberFormatException ex) {
			errorResponse(request, response, Tipo.SHEET_PARAM_NOT_INTEGER.getMessage());
		}
	}
	
	protected void errorResponse(HttpServletRequest request, HttpServletResponse response, String error) throws ServletException, IOException {
		request.setAttribute("error", error);
		getServletContext().getRequestDispatcher("/jsp/error.jsp").forward(request, response);
	}

}
