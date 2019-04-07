package servlets;

import java.io.IOException;
import java.util.ArrayList;
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
 * Servlet implementation class ShowExcel
 */
@WebServlet("/excel")
public class ShowExcel extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private List<String> errors = new ArrayList<>();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Controller controller;
		try {
			controller = Controller.getInstance();
		} catch (InvalidActionException e) {
			errors.add(e.getMessage());
			getServletContext().getRequestDispatcher("/jsp/excel.jsp").forward(request, response);
			return;
		}
		String paramSheet = request.getParameter("sheet");
		int sheetNumber = 0;
		Sheet sheet;
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
			sheet = null;
			errors.add(e.getMessage());
		}
		if (errors.isEmpty())
			request.setAttribute("sheet", sheet);
		else
			request.setAttribute("errors", errors);
		request.setAttribute("sheetActive", sheetNumber);
		request.setAttribute("sheetNames", controller.getDTOSheets());
		getServletContext().getRequestDispatcher("/jsp/excel.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
