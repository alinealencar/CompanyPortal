package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ViewDepartment")
public class ViewDepartment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ViewDepartment() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		String dept = request.getParameter("department");
		request.getSession().setAttribute("department", dept);
		response.sendRedirect("view-department.jsp");
		return;
	}

}
