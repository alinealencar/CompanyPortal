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
* Description: This servlet handles the viewing of the attendance records.
*********************************************************************************/

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

    /**
	 * This method is called whenever this servlet receives a GET request.
	 * It calls the doPost method.
	 * 
	 * @param	request	HttpServletRequest object
     * @param	response HttpServletResponse object
     * @return	void
     * @exception	ServletException
     * @exception	 IOException on input error
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * This method is called whenever this servlet receives a POST request.
	 * It is responsible for showing the attendance for a certain department.
	 * 
	 * @param	request	HttpServletRequest object
     * @param	response HttpServletResponse object
     * @return	void
     * @exception	ServletException
     * @exception	 IOException on input error
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		String dept = request.getParameter("department");
  		request.setAttribute("deptViewAttendance", dept);
  		
		RequestDispatcher dispatcher = request.getRequestDispatcher("view-attendance.jsp");
        dispatcher.forward(request, response);
	}
	

}
