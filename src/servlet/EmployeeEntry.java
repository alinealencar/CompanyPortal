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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;

import dataModel.Employee;
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
		boolean validEmail = false;
		boolean validYear = false;
		boolean validJobPos = false;
		HttpSession session = request.getSession();
		
		response.setContentType("text/html");
		
		//get user input information
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String employeeNum = request.getParameter("employeeNum");
		String email = request.getParameter("email");
		String hireYear = request.getParameter("hireYear");
		String jobPosition = request.getParameter("jobPosition");
		
		// Create Employee object
		Employee anEmployee = new Employee(firstName, lastName, employeeNum, email, hireYear, jobPosition);
		
				
		//first name validation (if it is not empty and/or only alphabet)
		if(!ValidateInput.isMissing(anEmployee.getFirstName()) && ValidateInput.isAlphabet(anEmployee.getFirstName())){
			session.removeAttribute("errorFName");
			session.setAttribute("firstName", anEmployee.getFirstName());//store valid value to session
			validFName = true;
		}else{
			if(ValidateInput.isMissing(anEmployee.getFirstName())){			
				session.setAttribute("errorFName", "You must input a First Name");
				session.setAttribute("firstName", "");
			}else if(!ValidateInput.isAlphabet(anEmployee.getFirstName())){		
				session.setAttribute("errorFName", "First Name must contain letters only");
				session.setAttribute("firstName", "");
			}
		}
		
					
		//last name validation (if it is not empty and/or only alphabet)		
		if(!ValidateInput.isMissing(anEmployee.getLastName()) && ValidateInput.isAlphabet(anEmployee.getLastName())){
			session.removeAttribute("errorLName");
			session.setAttribute("lastName", anEmployee.getLastName());//store valid value to session
			validLName = true;
		}else{
			if(ValidateInput.isMissing(anEmployee.getLastName())){			
				session.setAttribute("errorLName", "You must input a Last Name");
				session.setAttribute("lastName", "");
			}else if(!ValidateInput.isAlphabet(anEmployee.getLastName())){		
				session.setAttribute("errorLName", "Last Name must contain letters only");
				session.setAttribute("lastName", "");
			}
		}
		
		
		//employee number stays in textbox when user need to modify other value
		session.setAttribute("employeeNum", anEmployee.getEmpNo());//store value to session
		
			
		//email validation (if it is not empty and/or valid email combination)
		if(!ValidateInput.isMissing(anEmployee.getEmail()) && ValidateInput.isValidEmail(anEmployee.getEmail())){
			session.removeAttribute("errorEmail");
			session.setAttribute("email", anEmployee.getEmail());//store valid value to session
			validEmail = true;
		}else{
			if(ValidateInput.isMissing(anEmployee.getEmail())){			
				session.setAttribute("errorEmail", "You must input an Email");
				session.setAttribute("email", "");
			}else if(!ValidateInput.isValidEmail(anEmployee.getEmail())){		
				session.setAttribute("errorEmail", "Invalid email");
				session.setAttribute("email", "");
			}
		}
		
		//Hire Year validation (if it is not select index 0)
		if(anEmployee.getHireYear() != null){
			session.removeAttribute("errorYear");
			session.setAttribute("hireYear", anEmployee.getHireYear());//store valid value to session
			validYear = true;
		}else{
			if(anEmployee.getHireYear() == null){			
				session.setAttribute("errorYear", "You must select a year");
			}
		}
		
		//Job Position validation (if it is not select index 0)
		if(anEmployee.getJobPosition() != null){
			session.removeAttribute("errorPosition");
			session.setAttribute("jobPosition", anEmployee.getJobPosition());//store valid value to session
			validJobPos = true;
		}else{
			if(anEmployee.getJobPosition() == null){			
				session.setAttribute("errorPosition", "You must select a valid job position");
			}
		}

		 
		//Store valid data to database
				
		if(validFName && validLName && validEmail && validYear && validJobPos){
			try {
				if(DatabaseManagement.insertEmployee(anEmployee.getFirstName(), 
						anEmployee.getLastName(), 
						anEmployee.getEmpNo(), 
						anEmployee.getEmail(), 
						anEmployee.getHireYear(), 
						anEmployee.getJobPosition())){
					request.setAttribute("employeeInsertSuccess","Employee " + anEmployee.getFirstName() + " " + anEmployee.getLastName() + " has been successfully added to the system ") ;
					
					//clear form
					session.setAttribute("firstName", "");
					session.setAttribute("lastName", "");
					session.setAttribute("employeeNum","");
					session.setAttribute("email", "");
					session.setAttribute("hireYear", "");
					session.setAttribute("jobPosition", "");
				}
				else {
					request.setAttribute("employeeInsertFail", "Employee " + anEmployee.getFirstName()  + " " + anEmployee.getLastName() + " has NOT been added to the system ");
				}
			}
			catch(Exception e){
				System.out.println(e);
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("employee-entry.jsp");
			dispatcher.forward(request, response);
			return;
		}
		else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("employee-entry.jsp");
			dispatcher.forward(request, response);
		}

	}//end of doPost()

}
