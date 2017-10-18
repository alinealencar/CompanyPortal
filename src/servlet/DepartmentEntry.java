package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DatabaseAccess;
import helper.DatabaseManagement;


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
		Connection conn = null;
		response.setContentType("text/html");
		
		//String deptName = request.getParameter("deptName");
		//String loc = request.getParameter("location");
		
		try {
			DatabaseAccess.createDatabase();
			conn = DatabaseAccess.connectDataBase();
			
			PrintWriter pw = response.getWriter();
			//pw.println(DatabaseManagement.insertDepartment(deptName, loc, conn));
			pw.println("\n" + DatabaseManagement.selectFromTable("department", conn));
		}
		catch(Exception e){
			System.out.println(e);
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


