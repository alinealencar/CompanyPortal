package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.ValidateInput;


@WebServlet("/EnterAttendanceHelper")
public class EnterAttendanceHelper extends HttpServlet {
	
	protected static String dept;
	private static final long serialVersionUID = 1L;
       
    public EnterAttendanceHelper() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		dept = request.getParameter("department");
		request.getSession().setAttribute("department", dept);
		response.sendRedirect("enter-attendance.jsp");
		return;
	}

}
