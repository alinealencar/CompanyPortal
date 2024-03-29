/*********************************************************************************
* Project: COMP3095_TechGirls
* Assignment: Assignment 2
* Author(s): Aline Neves Alencar,
* 				Kie Ogiya,
* 				Maria Alyssa Villacete,
* 				Princess Ilasin
* Student Number: 101036808,
* 					100984638
* 					100923181
* 					100879176
* Date: December 29th, 2017.
* Description: This servlet handles the attendance record creation.
*********************************************************************************/

package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataModel.Attendance;
import helper.DatabaseHelper;
import helper.DatabaseManagement;
import helper.HelperUtilities;
import helper.ValidateInput;


@WebServlet("/EnterAttendance")
public class EnterAttendance extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EnterAttendance() {
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
	 * It is responsible for creating an attendance record and inserting it into the database.
	 * 
	 * @param	request	HttpServletRequest object
     * @param	response HttpServletResponse object
     * @return	void
     * @exception	ServletException
     * @exception	 IOException on input error
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String deptName = EnterAttendanceHelper.dept;
		Attendance anAttendance = new Attendance();
		String[] selectedEmployeeIds = new String[50];
		
		if(!ValidateInput.isMissing(deptName)){
		//access form values
		anAttendance.setAttendanceDate(java.sql.Date.valueOf(request.getParameter("attendanceDate")));
		anAttendance.setDeptName(deptName);
		
		selectedEmployeeIds = request.getParameterValues("present");
		
		//response.sendRedirect("enter-attendance.jsp");
	
		try {
				//check if there is a duplicate entry in the database (same department and same date)
				if(!DatabaseHelper.isDuplicateAttendance(anAttendance.getDeptName(),anAttendance.getAttendanceDate()))
				{
					//check if insertion to the database succeeded
					if(DatabaseManagement.insertAttendance(anAttendance.getAttendanceDate(), anAttendance.getDeptName())) {
					
						String[] employeeIdList = HelperUtilities.getStringFromResultSet(DatabaseManagement.selectEmployees(anAttendance.getDeptName()), "emp_id");
							
						//loop through all the employees to get their attendance
						for(int i=0; i<employeeIdList.length; i++){
							//insert values into the database
							DatabaseManagement.insertEmployeeAttendance(Integer.parseInt(employeeIdList[i]), DatabaseHelper.getAttendanceId(anAttendance.getDeptName(), anAttendance.getAttendanceDate()));
						//}
						}
						
						//loop through the selected employees array
						if(selectedEmployeeIds != null){
							for(int j=0; j < selectedEmployeeIds.length; j++){
								DatabaseManagement.updatePresentEmployees(Integer.parseInt(selectedEmployeeIds[j]), DatabaseHelper.getAttendanceId(anAttendance.getDeptName(), anAttendance.getAttendanceDate()));
							}	
						}
					}	
					else
						request.setAttribute("attendanceInsertError", "ERROR! The marking of attendance failed");
				
					request.setAttribute("attendanceInsertSuccess", "The attendance for the " + anAttendance.getDeptName() + " department was successfully marked!");
				}
				else
					request.setAttribute("attendanceInsertError", "The attendance is already marked for the " + anAttendance.getDeptName() + " department on the selected date.");
			}
				
			catch(Exception e){
				//error message if insert failed
				request.setAttribute("attendanceInsertError", e + "\nPlease try again.");
			}
		}
		else
			request.setAttribute("attendanceInsertError", "Please select a department.");
		RequestDispatcher dispatcher = request.getRequestDispatcher("enter-attendance.jsp");
        dispatcher.forward(request, response);

}
}

