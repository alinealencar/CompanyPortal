package servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataModel.Attendance;
import dataModel.Employee;
import dataModel.EmployeeAttendance;
import helper.DatabaseHelper;
import helper.DatabaseManagement;
import helper.HelperUtilities;
import helper.ValidateInput;

@WebServlet("/ViewAttendance")
public class ViewAttendance extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ViewAttendance() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		String dept = request.getParameter("department");
		String[] attendanceList = null;
		List<List<Attendance>> attendanceByDept = new ArrayList<List<Attendance>>();
		//List<Attendance> = new ArrayList<Attendance>;
		try{
			if(!ValidateInput.isMissing(dept)){
				request.setAttribute("deptViewAttendance", dept);
		
				//ResultSet rsAttendance = DatabaseManagement.selectAttendanceByDept(dept);
				//List<Attendance> attendanceList = DatabaseHelper.getAttendance(rsAttendance);
				
				//get all the attendance dates for the selected department
				String[] dateList = HelperUtilities.getStringFromResultSet(DatabaseManagement.selectAttendanceByDept(dept), "attendance_date");
			
				//get the employees for the selected department
				ResultSet rsEmployee= DatabaseManagement.selectEmployees(dept);
				List<Employee> employees = DatabaseHelper.getEmployees(rsEmployee);
				
				//get employees' attendance
				//for(int i = 0; i < employees.size(); i++){
					//attendanceList = HelperUtilities.getStringFromResultSet(DatabaseManagement.selectPresentEmployees(employees.get(i).getEmpId()), "present");
					//ResultSet rsPresent = DatabaseManagement.selectPresentEmployees(DatabaseHelper.getDeptId(dept));
					//List<EmployeeAttendance> presentList = DatabaseHelper.getPresentEmployees(rsPresent);
					
					//attendanceByDept.add(presentList);
				//}
				
				request.setAttribute("dateList", dateList);
				request.setAttribute("employeesByDept", employees);
				//request.setAttribute("attendanceList", presentList);
			}
		}catch(Exception e){
			
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("view-attendance.jsp");
        dispatcher.forward(request, response);
	}
	

}
