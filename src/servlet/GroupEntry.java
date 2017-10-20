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
		HttpSession session = request.getSession();
		Connection conn = null;
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Boolean isMissingValue = false;
		
		
		/*Cookie deptCookie = new Cookie("deptName", deptName);
		response.addCookie(deptCookie);
		response.sendRedirect("group-entry.jsp");*/
		
		String deptName = request.getParameter("deptName");
		String groupName = request.getParameter("groupName");
		String emp1 = request.getParameter("emp1");
		String emp2 = request.getParameter("emp2");
		String emp3 = request.getParameter("emp3");
		String emp4 = request.getParameter("emp4");
		String emp5 = request.getParameter("emp5");
		String emp6 = request.getParameter("emp6");
		
		//add selected employees to arraylist
		ArrayList<String> empList = new ArrayList<String>();
		empList.add(emp1);
		empList.add(emp2);
		empList.add(emp3);
		empList.add(emp4);
		empList.add(emp5);
		empList.add(emp6);
		
		response.sendRedirect("group-entry.jsp");
		
		//check if department name if missing
		if (ValidateInput.isMissing(deptName)) {
			request.getSession().setAttribute("errorDepartment", "Please select a department.");
			//request.getRequestDispatcher("/").include(request,response);
			request.getSession().setAttribute("deptName", "");
			//RequestDispatcher rd = getServletContext().getRequestDispatcher("/group-entry.jsp");
			//rd.forward(request, response);
			//deptName = "Missing department name!";
			isMissingValue = true;
		}
		else {
			request.getSession().removeAttribute("errorDepartment");
			request.getSession().setAttribute("deptName", deptName);
		}
		
		//check if group name is missing
		if (ValidateInput.isMissing(groupName)) {
			request.getSession().setAttribute("errorGroupName", "Please enter a group name.");
			request.getSession().setAttribute("groupName", "");
			isMissingValue = true;
		}
		else {
			request.getSession().removeAttribute("errorGroupName");
			request.getSession().setAttribute("groupName", groupName);
		}
		
		//check if no employee is selected
		if (ValidateInput.isMissing(emp1)) {
			request.getSession().setAttribute("errorEmp1", "Please select an employee.");
			request.getSession().setAttribute("emp1", "");
			isMissingValue = true;
		}
		//check if there are duplicate employees selected
		else if (empList.contains(emp2) || empList.contains(emp3) || empList.contains(emp4) || empList.contains(emp5) || empList.contains(emp6)) {
			request.getSession().setAttribute("errorEmp1", "Duplicate employee! Please select another employee.");
			request.getSession().setAttribute("emp1", "");
			isMissingValue = true;
		}
		else {
			request.getSession().removeAttribute("errorEmp1");
			request.getSession().setAttribute("emp1", emp1);
		}
		
		
		/*Cookie groupCookie = new Cookie("groupName", groupName);
		c1.setMaxAge( 60 * 60 * 24 * 7);
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
		response.addCookie(emp6Cookie);*/
	
		if (!isMissingValue){
			
			try {
				DatabaseAccess.createDatabase();
				conn = DatabaseAccess.connectDataBase();
			
				if(DatabaseManagement.insertGroup(deptName, groupName, emp1, emp2, emp3, emp4, emp5, emp6, conn)) 
					request.getSession().setAttribute("groupInsertSuccess", "The " + groupName + " group was successfully created!");
				//pw.println("The " + groupName + " group was succesfully created!");
				//pw.println("\n" + DatabaseManagement.selectFromTable("department", conn));
			}
			catch(Exception e){
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
		//else
			//response.sendRedirect("group-entry.jsp");
			//CookieUtilities.eraseCookie(request, response, c1);
			//CookieUtilities.eraseCookie(request, response, c2);
	}

	}


