package control;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.PhotoDAO;

import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/UploadPhoto")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50) // 50MB
public class UploadPhoto extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UploadPhoto() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		int id = 0;
		Object idObj = request.getAttribute("productID");
		if (idObj != null) {
		    id = (int) idObj;
		} else {
			response.sendError(500);
			return;
		}
		for (Part part : request.getParts()) {
			
			String fileName = part.getSubmittedFileName();
			if (fileName != null && !fileName.equals("")) {
				try {
					PhotoDAO.updatePhoto(id, part.getInputStream());
				} catch (Exception sqlException) {
					System.out.println(sqlException);
				}
			}
		}
		RequestDispatcher dispatcher = this.getServletContext().
				getRequestDispatcher("/resources/admin/catalogue.jsp?success=true");
		dispatcher.forward(request, response);
	}

}
