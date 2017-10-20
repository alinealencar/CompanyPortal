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

@WebServlet("/GroupEntry")
public class GroupEntry extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GroupEntry() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Boolean isMissingValue = false;
		
		String deptName = request.getParameter("deptName");
		String groupName = request.getParameter("groupName");
		String emp1 = request.getParameter("emp1");
		String emp2 = request.getParameter("emp2");
		String emp3 = request.getParameter("emp3");
		String emp4 = request.getParameter("emp4");
		String emp5 = request.getParameter("emp5");
		String emp6 = request.getParameter("emp6");
		
		if (ValidateInput.isMissing(deptName)) {
			//session.setAttribute("error", "Please try again.");
			//request.getRequestDispatcher("/").include(request,response);
			//request.setAttribute("departmentError", "Please select a department.");
			//RequestDispatcher rd = getServletContext().getRequestDispatcher("/dept-entry.jsp");
			//rd.forward(request, response);
			deptName = "Missing department name!";
			isMissingValue = true;
		}
		
		if (ValidateInput.isMissing(groupName)) {
			deptName = "Missing group name!";
			isMissingValue = true;
		}
		
		if (ValidateInput.isMissing(emp1)) {
			deptName = "Please select atleast one employee";
			isMissingValue = true;
		}
		
		Cookie groupCookie = new Cookie("groupName", groupName);
		//c1.setMaxAge( 60 * 60 * 24 * 7);
		response.addCookie(groupCookie);
		Cookie emp1Cookie = new Cookie("emp1", emp1);
		response.addCookie(emp1Cookie);
		Cookie emp2Cookie = new Cookie("emp2", emp2);
		response.addCookie(emp2Cookie);
		Cookie emp3Cookie = new Cookie("emp3", emp3);
		response.addCookie(emp3Cookie);
		Cookie emp4Cookie = new Cookie("emp4", emp4);
		response.addCookie(emp4Cookie);
		Cookie emp5Cookie = new Cookie("emp5", emp5);
		response.addCookie(emp5Cookie);
		Cookie emp6Cookie = new Cookie("emp6", emp6);
		response.addCookie(emp6Cookie);
	
		if (!isMissingValue){
			
			try {
				DatabaseAccess.createDatabase();
				conn = DatabaseAccess.connectDataBase();
			
				if(DatabaseManagement.insertGroup(deptName, groupName, emp1, emp2, emp3, emp4, emp5, emp6, conn)) 
				pw.println("The " + groupName + " group was succesfully created!");
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
		else
			response.sendRedirect("group-entry.jsp");
			//CookieUtilities.eraseCookie(request, response, c1);
			//CookieUtilities.eraseCookie(request, response, c2);
	}

	}


