/*********************************************************************
* Project: COMP3095_TechGirls
* Assignment: Assignment 1
* Author(s): Aline Neves Alencar,
* 			 Kie Ogiya,
* 			 Maria Alyssa Villacete,
* 			 Princess Ilasin
* Student Number: 101036808,
* 				  100984638
* 				  100923181
* 				  100879176
* Date: October 19, 2017.
* Description: This class inserts employee information to database.
************************************************************************/
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;

import dataModel.User;
import database.DatabaseAccess;
import helper.DatabaseManagement;
import helper.ValidateInput;

@WebServlet("/EmployeeEntry")
public class EmployeeEntry extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EmployeeEntry() {
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
	 * It is responsible for creating an employee and inserting it into the database.
	 * 
	 * @param	request	HttpServletRequest object
     * @param	response HttpServletResponse object
     * @return	void
     * @exception	ServletException
     * @exception	 IOException on input error
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//check if valid
		boolean validFName = false;
		boolean validLName = false;
		//boolean validEmpNo = false;
		boolean validEmail = false;
		boolean validYear = false;
		boolean validJobPos = false;
		
		//get user input information
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String employeeNum = request.getParameter("employeeNum");
		String email = request.getParameter("email");
		String hireYear = request.getParameter("hireYear");
		String jobPosition = request.getParameter("jobPosition");
		
		//validation
		//response.sendRedirect("employee-entry.jsp");
				
		//first name validation (if it is not empty and/or only alphabet)
		if(!ValidateInput.isMissing(firstName) && ValidateInput.isAlphabet(firstName)){
			request.getSession().removeAttribute("errorFName");
			request.getSession().setAttribute("firstName", firstName);//store valid value to session
			validFName = true;
		}else{
			if(ValidateInput.isMissing(firstName)){			
				request.getSession().setAttribute("errorFName", "You must input a First Name");
				request.getSession().setAttribute("firstName", "");
			}else if(!ValidateInput.isAlphabet(firstName)){		
				request.getSession().setAttribute("errorFName", "First Name must contain letters only");
				request.getSession().setAttribute("firstName", "");
			}
		}
		
					
		//last name validation (if it is not empty and/or only alphabet)		
		if(!ValidateInput.isMissing(lastName) && ValidateInput.isAlphabet(lastName)){
			request.getSession().removeAttribute("errorLName");
			request.getSession().setAttribute("lastName", lastName);//store valid value to session
			validLName = true;
		}else{
			if(ValidateInput.isMissing(lastName)){			
				request.getSession().setAttribute("errorLName", "You must input a Last Name");
				request.getSession().setAttribute("lastName", "");
			}else if(!ValidateInput.isAlphabet(lastName)){		
				request.getSession().setAttribute("errorLName", "Last Name must contain letters only");
				request.getSession().setAttribute("lastName", "");
			}
		}
		
		
		//employee number stays in textbox when user need to modify other value
		request.getSession().setAttribute("employeeNum", employeeNum);//store value to session
		
			
		//email validation (if it is not empty and/or valid email combination)
		if(!ValidateInput.isMissing(email) && ValidateInput.isValidEmail(email)){
			request.getSession().removeAttribute("errorEmail");
			request.getSession().setAttribute("email", email);//store valid value to session
			validEmail = true;
		}else{
			if(ValidateInput.isMissing(email)){			
				request.getSession().setAttribute("errorEmail", "You must input an Email");
				request.getSession().setAttribute("email", "");
			}else if(!ValidateInput.isValidEmail(email)){		
				request.getSession().setAttribute("errorEmail", "Invalid email");
				request.getSession().setAttribute("email", "");
			}
		}
		
		//Hire Year validation (if it is not select index 0)
		if(hireYear != ""){
			request.getSession().removeAttribute("errorYear");
			//request.getSession().setAttribute("2000", "selected=\"selected\"");//keep selected **********************************
			request.getSession().setAttribute("hireYear", hireYear);//store valid value to session
			validYear = true;
		}else{
			if(hireYear == ""){			
				request.getSession().setAttribute("errorYear", "You must select a year");
			}
		}
		
		//Job Position validation (if it is not select index 0)
		if(jobPosition != ""){
			request.getSession().removeAttribute("errorPosition");
			//request.getSession().setAttribute("2000", "selected=\"selected\"");//keep selected **********************************
			request.getSession().setAttribute("jobPosition", jobPosition);//store valid value to session
			validJobPos = true;
		}else{
			if(jobPosition == ""){			
			request.getSession().setAttribute("errorPosition", "You must select a valid job position");
			}
		}

		 
		//Store valid data to database
		Connection conn = null;
		HttpSession session = request.getSession(true);
				
		if(validFName && validLName && validEmail && validYear && validJobPos){
			try {
				//DatabaseAccess.createDatabase();
				conn = (Connection) DatabaseAccess.connectDataBase();
				
				if(DatabaseManagement.insertEmployee(firstName, lastName, employeeNum, email, hireYear, jobPosition, conn)){
					session.setAttribute("employeeSuccess","Employee " + firstName + lastName + " has been successfully added to the system ") ;
					
					//clear all form for successfully added employee
					request.getSession().setAttribute("firstName", "");
					request.getSession().setAttribute("lastName", "");
					request.getSession().setAttribute("employeeNum","");
					request.getSession().setAttribute("email", "");
				}
				else {
					session.setAttribute("employeeError", "Employee " + firstName + lastName + " has NOT been added to the system ");
				}
			}
			catch(Exception e){
				System.out.println(e);
			}
			finally {
				try{
					// Close the connection
					conn.close();
					response.sendRedirect("employee-entry.jsp");
					return;
				}
				catch(SQLException ex){
					ex.printStackTrace();
				}
			}
		}
		else {
			response.sendRedirect("employee-entry.jsp");
		}
		
		
		//**************************************** Not done yet		
		/*
		 * drop down list doesn't stay selected item when there is invalid value
		 * haven't check proper value is stored  --- DONE
		 * pass data to database --- DONE
		 * show successful entry
		 */

	}//end of doPost()

}
