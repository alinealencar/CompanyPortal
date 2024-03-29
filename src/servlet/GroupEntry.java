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
* Description: This servlet handles the group-entry.jsp page
*********************************************************************************/

package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.DatabaseAccess;
import helper.DatabaseHelper;
import helper.DatabaseManagement;
import helper.ValidateInput;
import dataModel.*;

@WebServlet("/GroupEntry")
public class GroupEntry extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GroupEntry() {
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
	 * It is responsible for creating a group and inserting it into the database.
	 * 
	 * @param	request	HttpServletRequest object
     * @param	response HttpServletResponse object
     * @return	void
     * @exception	ServletException
     * @exception	 IOException on input error
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		Connection conn = null;
		response.setContentType("text/html");
		Boolean isNotValid = false;
		String fname;
		String lname;
		String names[];
		
		//access form values
		String deptName = request.getParameter("department");
		String groupName = request.getParameter("groupName");
		String emp1 = request.getParameter("emp1");
		String emp2 = request.getParameter("emp2");
		String emp3 = request.getParameter("emp3");
		String emp4 = request.getParameter("emp4");
		String emp5 = request.getParameter("emp5");
		String emp6 = request.getParameter("emp6");
		
		Group aGroup = new Group();
		aGroup.setDeptName(deptName);
		aGroup.setGroupName(groupName);
		aGroup.setMember1(emp1);
		aGroup.setMember2(emp2);
		aGroup.setMember3(emp3);
		aGroup.setMember4(emp4);
		aGroup.setMember5(emp5);
		aGroup.setMember6(emp6);
		
//		response.sendRedirect("group-entry.jsp");
		
		//check if department name if missing
		if (ValidateInput.isMissing(aGroup.getDeptName())) {
			request.getSession().setAttribute("errorDepartment", "Please select a department.");
			request.getSession().setAttribute("department", "");
			isNotValid = true;
		}
		else {
			request.getSession().removeAttribute("errorDepartment");
			request.getSession().setAttribute("department", aGroup.getDeptName());
		}
		
		//check if group name is missing
		if (ValidateInput.isMissing(aGroup.getGroupName())) {
			request.getSession().setAttribute("errorGroupName", "Please enter a group name.");
			request.getSession().setAttribute("groupName", "");
			isNotValid = true;
		}
		else {
			request.getSession().removeAttribute("errorGroupName");
			request.getSession().setAttribute("groupName", aGroup.getGroupName());
		}
		
		//check if no employee is selected
		if (ValidateInput.isMissing(aGroup.getMember1())) {
			request.getSession().setAttribute("errorEmp", "Please select an employee.");
			request.getSession().setAttribute("emp1", "");
			isNotValid = true;
		}
		else {
			request.getSession().removeAttribute("errorEmp");
			request.getSession().setAttribute("emp1", aGroup.getMember1());
		}
		
		//Check if there's a duplicate employee selected
		if(ValidateInput.isEmployeeDuplicate(emp1, emp2, emp3, emp4, emp5, emp6)){
			request.getSession().setAttribute("errorEmp", "You've selected the same employee twice. Please select another employee.");
			
			isNotValid = true;
		}
		
		request.getSession().setAttribute("emp2", aGroup.getMember2());
		request.getSession().setAttribute("emp3", aGroup.getMember3());
		request.getSession().setAttribute("emp4", aGroup.getMember4());
		request.getSession().setAttribute("emp5", aGroup.getMember5());
		request.getSession().setAttribute("emp6", aGroup.getMember6());
		
		
		
		if (!isNotValid){
			try {
				//DatabaseAccess.createDatabase();
//				conn = DatabaseAccess.connectDataBase();
				
				//check if the insertion to the database succeeded 
				if(DatabaseManagement.insertGroup(aGroup.getDeptName(), aGroup.getGroupName(), aGroup.getMember1(), aGroup.getMember2(), aGroup.getMember3(), aGroup.getMember4(), aGroup.getMember5(), aGroup.getMember6())){ 
					//split employee's full name
					names = DatabaseHelper.SplitFullName(aGroup.getMember1());
					fname = names[0];
					lname = names[1];
					//insert data into employee_groups table
					DatabaseManagement.insertEmployeeGroup(DatabaseHelper.getEmpId(fname, lname), DatabaseHelper.getGroupId(groupName));
					
					//check if there are more employees included in the group
					if (!ValidateInput.isMissing(emp2)){
						//split employee full name
						names = DatabaseHelper.SplitFullName(emp2);
						fname = names[0];
						lname = names[1];
						//insert data into employee_groups table
						DatabaseManagement.insertEmployeeGroup(DatabaseHelper.getEmpId(fname, lname), DatabaseHelper.getGroupId(groupName));
					}
					
					if (!ValidateInput.isMissing(emp3)){
						//split employee full name
						names = DatabaseHelper.SplitFullName(emp3);
						fname = names[0];
						lname = names[1];
						//insert data into employee_groups table
						DatabaseManagement.insertEmployeeGroup(DatabaseHelper.getEmpId(fname, lname), DatabaseHelper.getGroupId(groupName));
					}
					
					if (!ValidateInput.isMissing(emp4)){
						//split employee full name
						names = DatabaseHelper.SplitFullName(emp4);
						fname = names[0];
						lname = names[1];
						//insert data into employee_groups table
						DatabaseManagement.insertEmployeeGroup(DatabaseHelper.getEmpId(fname, lname), DatabaseHelper.getGroupId(groupName));
					}
					
					if (!ValidateInput.isMissing(emp5)){
						//split employee full name
						names = DatabaseHelper.SplitFullName(emp5);
						fname = names[0];
						lname = names[1];
						//insert data into employee_groups table
						DatabaseManagement.insertEmployeeGroup(DatabaseHelper.getEmpId(fname, lname), DatabaseHelper.getGroupId(groupName));
					}
					
					if (!ValidateInput.isMissing(emp6)){
						//split employee full name
						names = DatabaseHelper.SplitFullName(emp6);
						fname = names[0];
						lname = names[1];
						//insert data into employee_groups table
						DatabaseManagement.insertEmployeeGroup(DatabaseHelper.getEmpId(fname, lname), DatabaseHelper.getGroupId(groupName));
					}
					
					request.setAttribute("groupInsertSuccess", "The " + groupName + " group was successfully created!");
		
					//clear form after successful submission
					request.getSession().setAttribute("department", null);
					request.getSession().setAttribute("groupName", null);
					request.getSession().setAttribute("emp1", null);
					request.getSession().setAttribute("emp2", null);
					request.getSession().setAttribute("emp3", null);
					request.getSession().setAttribute("emp4", null);
					request.getSession().setAttribute("emp5", null);
					request.getSession().setAttribute("emp6", null);
				}
				else
					request.setAttribute("deptInsertError", "ERROR! The group creation failed.");
					
			}
			catch(Exception e){
				//error message if insertion to the database failed
				request.setAttribute("deptInsertError", e + "\nPlease try again.");
				System.out.print(e);
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("group-entry.jsp");
			dispatcher.forward(request, response);
		}
	}
}


