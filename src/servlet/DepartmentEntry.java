/*************************************************************
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
* Description: This servlet handles the dept-entry.jsp page
<<<<<<< HEAD
*********************************************************************************/

package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DatabaseAccess;
import helper.CookieUtilities;
import helper.DatabaseManagement;
import helper.ValidateInput;


@WebServlet("/DepartmentEntry")
public class DepartmentEntry extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DepartmentEntry() {
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
	 * It is responsible for creating a department and inserting it into the database.
	 * 
	 * @param	request	HttpServletRequest object
     * @param	response HttpServletResponse object
     * @return	void
     * @exception	ServletException
     * @exception	 IOException on input error
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		response.setContentType("text/html");
		Boolean isMissingValue = false;
		
		String deptName = request.getParameter("deptName");
		String loc = request.getParameter("location");
		
		response.sendRedirect("dept-entry.jsp");
		
		//check if department name is missing
		if (ValidateInput.isMissing(deptName)) {
			request.getSession().setAttribute("errorDeptName", "Please enter a department name");
			request.getSession().setAttribute("deptName", "");
			//deptName = "Missing department name!";
			isMissingValue = true;
		}
		else {
			request.getSession().removeAttribute("errorDeptName");
			request.getSession().setAttribute("deptName", deptName);
		}
		
		//check if department location is missing
		if (ValidateInput.isMissing(loc)) {
			//loc = "Missing department location!";
			request.getSession().setAttribute("errorLoc", "Please enter a location");
			request.getSession().setAttribute("location", "");
			isMissingValue = true;
		}
		else {
			request.getSession().removeAttribute("errorLoc");
			request.getSession().setAttribute("location", loc);
		}
		
		//Cookie c1 = new Cookie("deptName", deptName);
		//c1.setMaxAge( 60 * 60 * 24 * 7);
		//response.addCookie(c1);
		//Cookie c2 = new Cookie("location", loc);
		//c2.setMaxAge( 60 * 60 * 24 * 7);
		//response.addCookie(c2);
	
		if (!isMissingValue){
			try {
				//DatabaseAccess.createDatabase();
				conn = DatabaseAccess.connectDataBase();
			
				//check if insertion to the database succeeded
				if(DatabaseManagement.insertDepartment(deptName, loc, conn)) 
					request.getSession().setAttribute("deptInsertSuccess", "The " + deptName + " department was successfully created!");
			}
			catch(Exception e){
				//error message if insert failed
				request.getSession().setAttribute("deptInsertError", e + "\nPlease try again.");
			}
			finally {
				try{
					// Close the connection
					conn.close();
					
				}
				catch(SQLException ex){
				ex.printStackTrace();
				}
			}
		}
	}
}


