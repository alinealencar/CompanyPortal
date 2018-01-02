package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ViewAttendance")
public class ViewAttendance extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ViewAttendance() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		String dept = request.getParameter("department");
		request.getSession().setAttribute("department", dept);
		
		response.sendRedirect("view-attendance.jsp");
		return;
	}
	

}