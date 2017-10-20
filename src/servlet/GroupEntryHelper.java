/*********************************************************************************
* Project: COMP3095_TechGirls
* Assignment: Assignment 1
* Author(s): Aline Neves Alencar,
* 				Kie Ogiya,
* 				Maria Alyssa Villacete,
* 				Princess Ilasin
* Student Number: 101036808,
* 					100984638
* 					100923181
* 					100879176
* Date: October 17, 2017.
<<<<<<< HEAD
* Description: This servlet creates a cookie for the selected department and 
* 			   redirects back to the group-entry.jsp page
*********************************************************************************/

=======
* Description: This servlet acts as a first layer for the group-entry.jsp form.
*********************************************************************************/


>>>>>>> ae8d2e7eee67c19e86d1c3874cf8f1e8359a67a6
package servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/GroupEntryHelper")
public class GroupEntryHelper extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public GroupEntryHelper() {
        super();
        
    }

    /**
     * This method is called whenever this servlet receives a GET request.
     * It automatically calls the doPost method.
     * 
     * @param	request	HttpServletRequest object
     * @param	response HttpServletResponse object
     * @return	void
     * @exception	ServletException
     * @exception	 IOException on input error
     * @see #doPost(HttpServletRequest, HttpServletResponse)
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	/**
	 * This method is called whenever this servlet receives a POST request.
	 * It is responsible for creating a cookie with a department name.
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
		String group = request.getParameter("groupName");
		Cookie deptCookie = new Cookie("department", dept);
		response.addCookie(deptCookie);
		Cookie groupCookie = new Cookie("groupName", group);
		response.addCookie(groupCookie);
		response.sendRedirect("group-entry.jsp");
	}

}