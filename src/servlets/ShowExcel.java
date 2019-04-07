package servlets;

import java.io.IOException;

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
    private Controller controller;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowExcel() {
    	controller = Controller.getInstance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String paramSheet = request.getParameter("sheet");
		int sheetNumber = 0;
		Sheet sheet;
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
			sheet = null;
			exception = e;
		}
		if (exception == null)
			request.setAttribute("sheet", sheet);
		else
			request.setAttribute("error", exception.getMessage());
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
