package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataModel.Attendance;
import database.DatabaseAccess;
import helper.DatabaseManagement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

@WebServlet("/AttendanceServlet")
public class AttendanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AttendanceServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn;
		response.setContentType("text/html");
		Boolean isNotValid = false;
		ArrayList<String> deptNames = new ArrayList<String>();
		ResultSet rsDept = null; 
		
		Attendance attend = new Attendance();

		/*try{
			conn = DatabaseAccess.connectDataBase();
			rsDept = DatabaseManagement.selectFromTable("department", conn);
			//String deptName = request.getParameter("department");
			
			while(rsDept.next()){
				deptNames.add(rsDept.getString("dept_name"));
				attend.setDeptName(rsDept.getString("dept_name"));
				//request.setAttribute("attendance", attend);
			}
			
			attend.setDeptNames(deptNames);
			//attend.setDeptName(deptName);
			
			request.setAttribute("attendance", attend);
			request.setAttribute("deptNames", deptNames);
			RequestDispatcher rd = request.getRequestDispatcher("attendance.jsp");  
	        rd.include(request, response);  
		
			
		}catch(Exception e){
			
		}*/
		//access form values
			
		
		
	}

}
