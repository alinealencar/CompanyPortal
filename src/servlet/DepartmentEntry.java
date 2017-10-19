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
* Date: October 17, 2017.
* Description: This class provides the the validation of user's input.
 */

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
import helper.DatabaseManagement;
import helper.ValidateInput;


@WebServlet("/DepartmentEntry")
public class DepartmentEntry extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public DepartmentEntry() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//HttpSession session = request.getSession(true);
		Connection conn = null;
		response.setContentType("text/html");
		
		PrintWriter pw = response.getWriter();
		Boolean isMissingValue = false;
		
		String deptName = request.getParameter("deptName");
		String loc = request.getParameter("location");
		
		Cookie c1 = new Cookie("deptName", deptName);
		response.addCookie(c1);
		Cookie c2 = new Cookie("location", loc);
		response.addCookie(c2);
				  
		if (ValidateInput.isMissing(deptName)) {
			//session.setAttribute("error", "Please try again.");
			//request.getRequestDispatcher("/").include(request,response);
			request.setAttribute("deptNameError", "Invalid department name!");
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/dept-entry.jsp");
			rd.forward(request, response);
			isMissingValue = true;
		}
		
		if (ValidateInput.isMissing(loc)) {
			request.setAttribute("deptLocError", "Invalid location!");
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/dept-entry.jsp");
			rd.forward(request, response);
			isMissingValue = true;
		}
			
		if (!isMissingValue){
		
			try {
				DatabaseAccess.createDatabase();
				conn = DatabaseAccess.connectDataBase();
			
				if(DatabaseManagement.insertDepartment(deptName, loc, conn)) 
				pw.println("The " + deptName + " department was succesfully created!");
				//pw.println("\n" + DatabaseManagement.selectFromTable("department", conn));
			}
			catch(Exception e){
				System.out.println("Something went wrong.\n" + e + "\nPlease try again.");
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


