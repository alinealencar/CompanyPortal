package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.ValidateInput;

@WebServlet("/ViewGroup")
public class ViewGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public ViewGroup() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		String dept = request.getParameter("department");
		
		if(!ValidateInput.isMissing(dept)){
			request.setAttribute("dept", dept);
			request.setAttribute("group", request.getParameter("group"));
			
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("view-group.jsp");
        dispatcher.forward(request, response);
	}

}
