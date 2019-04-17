package servlets;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileExistsException;

import controller.Controller;
import exception.InvalidActionException;
import exception.InvalidActionException.Tipo;

/**
 * Servlet implementation class Load
 */
@WebServlet("/load")
public class Load extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String info;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setAttribute("info", info);
			info = null;
			request.setAttribute("files", Controller.getInstance().getFileNames());
			request.setAttribute("sheetActive", -2);
			request.setAttribute("sheetNames", Controller.getInstance().getDTOSheets());
			getServletContext().getRequestDispatcher("/jsp/load.jsp").forward(request, response);
		} catch(InvalidActionException ex) {
			errorResponse(request, response, ex.getMessage());
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Controller controller = Controller.getInstance();
			ServletFileUpload sf = new ServletFileUpload(new DiskFileItemFactory());
			FileItem fileItem = sf.parseRequest(request).get(0);
			fileItem.write(new File(controller.getPath() + fileItem.getName()));
			controller.changeFile(fileItem.getName());
			info = "File " + fileItem.getName() + " uploaded successfully.";
			response.sendRedirect("/ExcelTucom/load");
		} catch(InvalidActionException ex) {
			errorResponse(request, response, ex.getMessage());
		} catch(FileExistsException e) {
			errorResponse(request, response, Tipo.FILE_EXISTS.getMessage());
		} catch (Exception e) {
			errorResponse(request, response, Tipo.ERROR_UPLOADING_FILE.getMessage());
		}
	}
	
	protected void errorResponse(HttpServletRequest request, HttpServletResponse response, String error) throws ServletException, IOException {
		request.setAttribute("error", error);
		getServletContext().getRequestDispatcher("/jsp/error.jsp").forward(request, response);
	}


}
