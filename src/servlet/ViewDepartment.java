package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
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

    /**
	 * This method is called whenever this servlet receives a GET request.
	 * It calls the doPost method.
	 * 
	 * @param	request	HttpServletRequest object
     * @param	response HttpServletResponse object
     * @return	void
     * @exception	ServletException
     * @exception	 IOException on input error
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	/**
	 * This method is called whenever this servlet receives a POST request.
	 * It is showing all departments.
	 * 
	 * @param	request	HttpServletRequest object
     * @param	response HttpServletResponse object
     * @return	void
     * @exception	ServletException
     * @exception	 IOException on input error
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		String dept = request.getParameter("department");
		request.setAttribute("department", dept);
		RequestDispatcher dispatcher = request.getRequestDispatcher("view-department.jsp");
        dispatcher.forward(request, response);
	}

}
