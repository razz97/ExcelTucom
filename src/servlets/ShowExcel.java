package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;

/**
 * Servlet implementation class ShowExcel
 */
@WebServlet("/ShowExcel")
public class ShowExcel extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Controller controller;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowExcel() {
    	controller = new Controller();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String paramSheet = request.getParameter("sheet");
		int sheetNumber;
		if (paramSheet == null) sheetNumber = 0;
		else { 
			try {
				sheetNumber = Integer.parseInt(paramSheet);			
			} catch (NumberFormatException e) {
				sheetNumber = 0;
			}
		}
		request.setAttribute("sheetNames", controller.getDTOSheets());
		request.setAttribute("sheet", controller.getSheet(sheetNumber));
		getServletContext().getRequestDispatcher("/jsp/ShowExcel.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
