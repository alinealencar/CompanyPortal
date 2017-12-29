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
import helper.DatabaseHelper;
import helper.DatabaseManagement;
import helper.HelperUtilities;
import helper.ValidateInput;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;


@WebServlet("/EnterAttendance")
public class EnterAttendance extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EnterAttendance() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String deptName;
		deptName = AttendanceHelperServlet.dept;
		Attendance anAttendance = new Attendance();
		
		//access form values
		//deptName = request.getParameter("department");
		anAttendance.setAttendanceDate(request.getParameter("attendanceDate"));
		anAttendance.setDeptName(deptName);
		String[] selectedEmployeeIds = request.getParameterValues("present");
		
		response.sendRedirect("enter-attendance.jsp");
		
		//check if date is missing
		/*if (ValidateInput.isMissing(anAttendance.getAttendanceDate()) || anAttendance.getAttendanceDate().equals("yyyy-mm-dd")) {
			request.getSession().setAttribute("errorAttendanceDate", "Please enter a date.");
			request.getSession().setAttribute("attendanceDate", "");
			isNotValid = true;
		}
		else {
			request.getSession().removeAttribute("errorAttendanceDate");
			request.getSession().setAttribute("attendanceDate", anAttendance.getAttendanceDate());
		}*/
		
		//if (!isNotValid){
			try {
				//check if there is a duplicate entry in the database (same department and same date)
				if(!DatabaseHelper.isDuplicate(anAttendance.getDeptName(),anAttendance.getAttendanceDate()))
				{
					//check if insertion to the database succeeded
					if(DatabaseManagement.insertAttendance(anAttendance.getAttendanceDate(), anAttendance.getDeptName())) {
						request.getSession().setAttribute("attendanceInsertSuccess", "The attendance for the " + anAttendance.getDeptName() + " department was successfully marked!");
					
						String[] employeeIdList = HelperUtilities.getStringFromResultSet(DatabaseManagement.selectEmployees(anAttendance.getDeptName()), "emp_id");
							
						//loop through all the employees to get their attendance
						for(int i=0; i<employeeIdList.length; i++){
							//insert values into the database
							DatabaseManagement.insertEmployeeAttendance(Integer.parseInt(employeeIdList[i]), DatabaseHelper.getAttendanceId(anAttendance.getDeptName(), anAttendance.getAttendanceDate()));
						//}
						}
						//loop through the selected employees array
						for(int j=0; j < selectedEmployeeIds.length; j++){
							DatabaseManagement.updatePresentEmployees(Integer.parseInt(selectedEmployeeIds[j]));
						}
					}	
					else
					request.getSession().setAttribute("attendanceInsertError", "ERROR! The marking of attendance failed");
					}
				else
					request.getSession().setAttribute("attendanceInsertError", "ERROR! The attendance is already marked for the " + anAttendance.getDeptName() + " department on the selected date");
			}
				
			catch(Exception e){
				//error message if insert failed
				request.getSession().setAttribute("attendanceInsertError", e + "\nPlease try again.");
			}
		
	

}
}
