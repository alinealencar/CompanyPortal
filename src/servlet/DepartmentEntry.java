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
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DatabaseAccess;
import helper.DatabaseManagement;
import helper.ValidateInput;
import dataModel.Department;


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
		Boolean isNotValid = false;
		
		String deptName = request.getParameter("deptName");
		String loc = request.getParameter("location");
		
		Department aDept = new Department(deptName, loc);
		
//		response.sendRedirect("dept-entry.jsp");
		
		//check if department name is missing
		if (ValidateInput.isMissing(aDept.getDeptName())) {
			request.getSession().setAttribute("errorDeptName", "Please enter a department name");
			request.getSession().setAttribute("deptName", "");
			isNotValid = true;
		}
		else {
			request.getSession().removeAttribute("errorDeptName");
			request.getSession().setAttribute("deptName", aDept.getDeptName());
		}
		
		//check if department location is missing
		if (ValidateInput.isMissing(aDept.getDeptLoc())) {
			request.getSession().setAttribute("errorLoc", "Please enter a location");
			request.getSession().setAttribute("location", "");
			isNotValid = true;
		}
		else {
			request.getSession().removeAttribute("errorLoc");
			request.getSession().setAttribute("location", aDept.getDeptLoc());
		}
	
		if (!isNotValid){
			try {
				//DatabaseAccess.createDatabase();
				conn = DatabaseAccess.connectDataBase();
			
				//check if insertion to the database succeeded
				if(DatabaseManagement.insertDepartment(aDept.getDeptName(), aDept.getDeptLoc(), conn)) {
					request.setAttribute("deptInsertSuccess", "The " + aDept.getDeptName() + " department was successfully created!");
				
					//clear form
					request.getSession().setAttribute("deptName", "");
					request.getSession().setAttribute("location", "");
					
				}
			}
			catch(Exception e){
				//error message if insert failed
				request.setAttribute("deptInsertError", e + "\nPlease try again.");
			}
			finally {
				try{
					// Close the connection
					conn.close();
					
				}
				catch(SQLException ex){
				ex.printStackTrace();
				}
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("dept-entry.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
}


