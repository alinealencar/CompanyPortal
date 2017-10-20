/*
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
* Description: This class insert employee information to database.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;

import database.DatabaseAccess;
import helper.DatabaseManagement;
import helper.ValidateInput;

@WebServlet("/EmployeeEntry")
public class EmployeeEntry extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EmployeeEntry() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//get user input information
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String employeeNum = request.getParameter("employeeNum");
		String email = request.getParameter("email");
		String hireYear = request.getParameter("hireYear");
		String jobPosition = request.getParameter("jobPosition");
		
		//validation
				response.sendRedirect("employee-entry.jsp");
				
		//first name validation (if it is not empty and/or only alphabet)
		if(!ValidateInput.isMissing(firstName) && ValidateInput.isAlphabet(firstName)){
			request.getSession().removeAttribute("errorFName");
			request.getSession().setAttribute("firstName", firstName);//store valid value to session
		}else{
			if(ValidateInput.isMissing(firstName)){			
				request.getSession().setAttribute("errorFName", "You must Input First Name");
				request.getSession().setAttribute("firstName", "");
			}else if(!ValidateInput.isAlphabet(firstName)){		
				request.getSession().setAttribute("errorFName", "First Name must contain only alphabet");
				request.getSession().setAttribute("firstName", "");
			}
		}
		
		//employee number stays in textbox when user need to modify other value
		request.getSession().setAttribute("employeeNum", employeeNum);//store value to session
		
			
		//last name validation (if it is not empty and/or only alphabet)		
		if(!ValidateInput.isMissing(lastName) && ValidateInput.isAlphabet(lastName)){
			request.getSession().removeAttribute("errorLName");
			request.getSession().setAttribute("lastName", lastName);//store valid value to session
		}else{
			if(ValidateInput.isMissing(lastName)){			
				request.getSession().setAttribute("errorLName", "You must Input Last Name");
				request.getSession().setAttribute("lastName", "");
			}else if(!ValidateInput.isAlphabet(lastName)){		
				request.getSession().setAttribute("errorLName", "Last Name must contain only alphabet");
				request.getSession().setAttribute("lastName", "");
			}
		}	
			
		//email validation (if it is not empty and/or valid email combination)
		if(!ValidateInput.isMissing(email) && ValidateInput.isValidEmail(email)){
			request.getSession().removeAttribute("errorEmail");
			request.getSession().setAttribute("email", email);//store valid value to session
		}else{
			if(ValidateInput.isMissing(email)){			
				request.getSession().setAttribute("errorEmail", "You must Input Email");
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
		}else{
			if(jobPosition == ""){			
			request.getSession().setAttribute("errorPosition", "You must be a valid job position");
			}
		}

		//**************************************** Not done yet		
		/*
		 * drop down list doesn't stay selected item when there is invalid value
		 * haven't check proper value is stored
		 * pass data to database
		 * show successful entry
		 */

	}//end of doPost()

}
