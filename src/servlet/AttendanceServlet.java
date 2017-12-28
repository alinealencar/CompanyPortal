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
		response.setContentType("text/html");
		Boolean isNotValid = false;
		//Date attendanceDateSql;
		String attendanceDate;
		boolean present;
		String deptName;
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
	
		//access form values
		deptName = request.getParameter("department");
		/*try {
			attendanceDate = dateFormat.parse(request.getParameter("attendanceDate"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		attendanceDateSql = new java.sql.Date(attendanceDate.getTime()); */
		attendanceDate = request.getParameter("attendanceDate");
		present = Boolean.valueOf(request.getParameter("present"));
		
		//create an attendance object
		Attendance anAttendance = new Attendance(attendanceDate, present, deptName);
		
		//redirect to attendance.jsp
		response.sendRedirect("attendance.jsp");
		
		//check if date is missing
		if (ValidateInput.isMissing(anAttendance.getAttendanceDate()) || anAttendance.getAttendanceDate().equals("yyyy-mm-dd")) {
			request.getSession().setAttribute("errorAttendanceDate", "Please enter a date.");
			request.getSession().setAttribute("attendanceDate", "");
			isNotValid = true;
		}
		else {
			request.getSession().removeAttribute("errorAttendanceDate");
			request.getSession().setAttribute("attendanceDate", anAttendance.getAttendanceDate());
		}
		
		if (!isNotValid){
			try {
				//DatabaseAccess.createDatabase();
				//conn = DatabaseAccess.connectDataBase();
			
				//check if insertion to the database succeeded
				if(DatabaseManagement.insertAttendance(anAttendance.getAttendanceDate(), anAttendance.isPresent(), anAttendance.getDeptName())) {
					request.getSession().setAttribute("attendanceInsertSuccess", "The attendance for the " + anAttendance.getDeptName() + " department was successfully marked!");
					String[] employeeIdList = HelperUtilities.getStringFromResultSet(DatabaseManagement.selectEmployees(anAttendance.getDeptName()), "emp_id");
					
					for(int i=0; i<employeeIdList.length; i++){
						DatabaseManagement.insertEmployeeAttendance(Integer.parseInt(employeeIdList[i]), DatabaseHelper.getAttendanceId(deptName, attendanceDate));
					}
					//clear form
					//request.getSession().setAttribute("deptName", "");
					//request.getSession().setAttribute("location", "");
					
				}
			}
			catch(Exception e){
				//error message if insert failed
				request.getSession().setAttribute("attendanceInsertError", e + "\nPlease try again.");
			}
		
	}

}
}
