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
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		String dept = request.getParameter("department");
		
		if(dept != null){
			request.setAttribute("dept", dept);
			request.setAttribute("group", request.getParameter("group"));
			
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("view-group.jsp");
        dispatcher.forward(request, response);
	}

}
