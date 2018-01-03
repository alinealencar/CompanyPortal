package servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataModel.Employee;
import helper.DatabaseHelper;
import helper.DatabaseManagement;
import helper.ValidateInput;


@WebServlet("/EnterAttendanceHelper")
public class EnterAttendanceHelper extends HttpServlet {
	
	protected static String dept;
	private static final long serialVersionUID = 1L;
       
    public EnterAttendanceHelper() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		dept = request.getParameter("department");
		
		try{
			//get the employees for the selected department
			ResultSet rsEmployee= DatabaseManagement.selectEmployees(dept);
			List<Employee> employees = DatabaseHelper.getEmployees(rsEmployee);
		
			request.setAttribute("department", dept);
			request.setAttribute("employeesByDept", employees);
		}
		catch(Exception e){
			
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("enter-attendance.jsp");
        dispatcher.forward(request, response);
	}

}
