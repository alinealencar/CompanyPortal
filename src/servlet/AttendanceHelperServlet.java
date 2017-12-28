package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.ValidateInput;


@WebServlet("/AttendanceHelperServlet")
public class AttendanceHelperServlet extends HttpServlet {
	
	public static String dept;
	private static final long serialVersionUID = 1L;
       
  
    public AttendanceHelperServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		boolean isNotValid = false;
		
		dept = request.getParameter("department");
		//request.getSession().setAttribute("department", dept);
		response.sendRedirect("attendance.jsp");
		
		//check if department name if missing
		if (ValidateInput.isMissing(dept)) {
			request.getSession().setAttribute("errorDepartment", "Please select a department.");
			request.getSession().setAttribute("department", "");
			isNotValid = true;
		}
		else {
			request.getSession().removeAttribute("errorDepartment");
			request.getSession().setAttribute("department", dept);
		}
	}

}
