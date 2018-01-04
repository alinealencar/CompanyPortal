/***********************************************************************
* Project: COMP3095_TechGirls
* Assignment: Assignment 1
* Author(s): Aline Neves Alencar,
* 				Kie Ogiya,
* 				Maria Alyssa Villacete,
* 				Princess Ilasin
* Student Number: 101036808,
* 					100984638
* 					100923181
* 					100879176
* Date: October 17, 2017.
<<<<<<< HEAD
* Description: This class contains methods that help provide and generate values to 
* 			   be stored in the database
*********************************************************************************/

package helper;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dataModel.Attendance;
import dataModel.Employee;
import dataModel.Group;
import dataModel.Report;
import dataModel.ReportTemplate;
import database.DatabaseAccess;

public class DatabaseHelper {
	/**
	 * This method returns and integer that represents the department a
	 * position belongs to.
	 * @param position	String that holds a certain position (role in the company).
	 * @return int Integer that holds the department ID a certain position is part of.
	 */
	public static int getEmployeeDeptID(String position) {
		
		int deptID;
		
		if (position.contains("Community Manager") || position.contains("Analytics Manager")) 
			deptID = 1;
		else if (position.contains("CTO") || position.contains("Front End") || position.contains("Back End") || position.contains("Database") || position.contains("Testing") || position.contains("System Operator")) 
			deptID = 2;
		else if (position.contains("Designer") || position.contains("Feature")) 
			deptID = 3;
		else if (position.contains("Introductory") || position.contains("Pre-Sales") || position.contains("Account")) 
			deptID = 4;
		else  
			deptID = 5;
		
		return deptID;	

	}
	
	/**
	 * This method returns a department ID according to its name.
	 * @param deptName	String that holds a department name.
	 * @param conn	Connection object that holds a connection to the database.
	 * @return	int	Integer that represents a department ID.
	 * @throws Exception
	 */
	public static int getDeptId(String deptName) 
		throws Exception {
		Connection conn = DatabaseAccess.connectDataBase();
		int result=0;
		Statement statement = conn.createStatement();
		String query = "select id from department "
				+ "where dept_name='" + deptName + "'";
		ResultSet rs = statement.executeQuery(query);
		if(rs != null){
			if(rs.next()){
				result = rs.getInt("id");
			 }
		}
		conn.close();
		return result;
	}
	
	/**
	 * This method returns an employee ID according to the employee's first and last name.
	 * @param fname	String that holds an employee's first name.
	 * @param lname	String that holds an employee's last name.
	 * @param conn	Connection object that holds a connection to the database.
	 * @return int	Integer that represents a employee ID.
	 * @throws Exception
	 */
	public static int getEmpId(String fname, String lname) 
			throws Exception {
		Connection conn = DatabaseAccess.connectDataBase();
		int result=0;
		Statement statement = conn.createStatement();
		String query = "select emp_id from employee "
				+ "where firstname='" + fname + "' and lastname='" + lname + "'";
		ResultSet rs = statement.executeQuery(query);
		if(rs != null){
			if(rs.next()){
				result = rs.getInt("emp_id");
			 }
		}
		conn.close();
		return result;
	}
		
	/**
	 * This method returns a group ID according to its name.
	 * @param groupName	String that holds a group name.
	 * @param conn	Connection object that holds a connection to the database.
	 * @return int	Integer that represents a group ID.
	 * @throws Exception
	 */
	public static int getGroupId(String groupName) 
			throws Exception {
		Connection conn = DatabaseAccess.connectDataBase();
		int result=0;
		Statement statement = conn.createStatement();
		String query = "select groups_id from groups "
				+ "where group_name='" + groupName + "'";
		ResultSet rs = statement.executeQuery(query);
		if(rs != null){
			if(rs.next()){
				result = rs.getInt("groups_id");
			 }
		}
		conn.close();
		return result;
	}
	
	/**
	 * This method returns an attendance ID according to the department name and attendance date.
	 * @param deptName	String that holds a department name.
	 * @return int	Integer that represents an attendance ID.
	 * @throws Exception
	 */
	public static int getAttendanceId(String deptName, Date attendanceDate) 
			throws Exception {
		Connection conn = DatabaseAccess.connectDataBase();
		int result=0;
		Statement statement = conn.createStatement();
		String query = "select attendance_id from attendance "
				+ "where dept_name='" + deptName + "' and attendance_date='" + attendanceDate + "'";
		ResultSet rs = statement.executeQuery(query);
		if(rs != null){
			if(rs.next()){
				result = rs.getInt("attendance_id");
			 }
		}
		conn.close();
		return result;
	}
	
	/**
	 * This method returns a String array containing the employee's first and last names
	 * @param fullname	String that holds an employee's full name.
	 * @return String[] array that contains the first and last names
	 */
	public static String[] SplitFullName(String fullname) {
        String[] names = fullname.split(" ");
        
        return names;
	}
	
	/**
	 * This method checks if the attendance for a certain date is already present in the database, in
	 * order to avoid duplicates.
	 * 
	 * @param deptName
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static boolean isDuplicateAttendance(String deptName, Date date) 
		throws Exception {
		Connection conn = DatabaseAccess.connectDataBase();
		Statement statement = conn.createStatement();
		String query = "select COUNT(*) from attendance "
				+ "where dept_name='" + deptName + "' and attendance_date='" + date + "'";
		ResultSet rs = statement.executeQuery(query);
		if(rs.next())
			rs.getInt(1);
		if(rs.getInt(1) > 0)
			return true;
		else
			return false;
	}
	
	/**
	 * This method checks if a department name is already present in the database, in order to avoid
	 * duplicates. 
	 * 
	 * @param deptName
	 * @return
	 * @throws Exception
	 */
	public static boolean isDuplicateDept(String deptName) 
			throws Exception {
			Connection conn = DatabaseAccess.connectDataBase();
			Statement statement = conn.createStatement();
			String query = "select COUNT(*) from department "
					+ "where dept_name='" + deptName + "'";
			ResultSet rs = statement.executeQuery(query);
			if(rs.next())
				rs.getInt(1);
			if(rs.getInt(1) > 0)
				return true;
			else
				return false;
	}
	
	
	/**
	 * This method transforms report templates from a ResultSet into a List of
	 * Report Template objects.
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	public static List<ReportTemplate> getReportTemplates(ResultSet rs) throws SQLException{
		List<ReportTemplate> reportTemplates = new ArrayList<ReportTemplate>();
		
		while(rs.next()){
			ReportTemplate template = new ReportTemplate();
			template.setTemplateId(rs.getInt(1));
			template.setTemplateName(rs.getString(2));
			template.setTemplateDate(rs.getDate(3));
			template.setSection1(rs.getString(4));
			template.setS1Criteria1(rs.getString(5));
			template.setS1Criteria2(rs.getString(6));
			template.setS1Criteria3(rs.getString(7));
			template.setS1Criteria4(rs.getString(8));
			template.setS1Criteria5(rs.getString(9));
			template.setS1Crit1Max(rs.getInt(10));
			template.setS1Crit2Max(rs.getInt(11));
			template.setS1Crit3Max(rs.getInt(12));
			template.setS1Crit4Max(rs.getInt(13));
			template.setS1Crit5Max(rs.getInt(14));
			template.setSection2(rs.getString(15));
			template.setS2Criteria1(rs.getString(16));
			template.setS2Criteria2(rs.getString(17));
			template.setS2Criteria3(rs.getString(18));
			template.setS2Crit1Max(rs.getInt(19));
			template.setS2Crit2Max(rs.getInt(20));
			template.setS2Crit3Max(rs.getInt(21));
			template.setSection3(rs.getString(22));
			template.setS3Criteria1(rs.getString(23));
			template.setS3Criteria2(rs.getString(24));
			template.setS3Criteria3(rs.getString(25));
			template.setS3Crit1Max(rs.getInt(26));
			template.setS3Crit2Max(rs.getInt(27));
			template.setS3Crit3Max(rs.getInt(28));
			template.setDeptId(rs.getInt(29));
			
			reportTemplates.add(template);
		}
		
		return reportTemplates;
	}
	
	/**
	 * This method transforms reports from a ResultSet into a List of
	 * Report objects.
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	public static List<Report> getReports(ResultSet rs) throws SQLException{
		List<Report> reports = new ArrayList<Report>();
		
		while(rs.next()){
			Report report = new Report();
			report.setReportId(rs.getInt(1));
			report.setReportTitle(rs.getString(2));
			report.setReportDate(rs.getDate(3));
			report.setReportType(rs.getString(4));
			report.setS1Crit1(rs.getInt(5));
			report.setS1Crit2(rs.getInt(6));
			report.setS1Crit3(rs.getInt(7));
			report.setS1Crit4(rs.getInt(8));
			report.setS1Crit5(rs.getInt(9));
			report.setComment1(rs.getString(10));
			report.setS2Crit1(rs.getInt(11));
			report.setS2Crit2(rs.getInt(12));
			report.setS2Crit3(rs.getInt(13));
			report.setComment2(rs.getString(14));
			report.setS3Crit1(rs.getInt(15));
			report.setS3Crit2(rs.getInt(16));
			report.setS3Crit3(rs.getInt(17));
			report.setComment3(rs.getString(18));
			report.setTemplateId(rs.getInt(19));
			
			reports.add(report);
		}
		
		return reports;
		
	}
	
	/**
	 * This method transforms attendance from a ResultSet into a List of
	 * Attendance objects.
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	public static List<Attendance> getAttendance(ResultSet rs) throws SQLException{
		List<Attendance> attendanceList = new ArrayList<Attendance>();
		while(rs.next()){
			Attendance attendance = new Attendance();
			attendance.setAttendanceDate(rs.getDate(1));
			attendance.setDeptName(rs.getString(2));
			
			attendanceList.add(attendance);
		}
		return attendanceList;
	}	
	
	/**
	 * This method transforms employees from a ResultSet into a list of
	 * Employee objects.
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	public static List<Employee> getEmployees(ResultSet rs) throws SQLException{
		List<Employee> employees = new ArrayList<Employee>();
		while(rs.next()){
			Employee employee = new Employee();
			employee.setEmpId(rs.getInt(1));
			employee.setFirstName(rs.getString(2));
			employee.setLastName(rs.getString(3));
			employee.setEmail(rs.getString(4));
			employee.setEmpNo(rs.getString(5));
			employee.setHireYear(rs.getString(6));
			employee.setJobPosition(rs.getString(7));
			employee.setRole(rs.getString(8));
			employee.setUserName(rs.getString(9));
			employee.setPassword(rs.getString(10));
			employee.setToken(rs.getString(11));
			employee.setDeptId(rs.getInt(12));
			
			employees.add(employee);
		}
		return employees;
	}
	
	/**
	 * This method returns a list of Employees with a presence in their attendance.
	 * 
	 * @param rs1
	 * @return
	 * @throws Exception
	 */
	public static List<Employee> getPresentEmployees(ResultSet rs1) throws Exception{
		List<Employee> employees = new ArrayList<Employee>();
		List<Integer> presentList = new ArrayList<Integer>();
		while(rs1.next()){
			Employee employee = new Employee();
			employee.setEmpId(rs1.getInt(1));
			employee.setFirstName(rs1.getString(2));
			employee.setLastName(rs1.getString(3));
			employee.setEmpNo(rs1.getString(4));
			
			ResultSet rs2 = DatabaseManagement.selectPresentEmployees(rs1.getInt(1));
			while(rs2.next())
				presentList.add(rs2.getInt(5));
			
			employee.setPresent(presentList);
			employees.add(employee);
		}
		return employees;
	}
	
	/**
	 * This method transforms groups from a ResultSet into a List of
	 * Group objects.
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	public static List<Group> getGroups(ResultSet rs) throws SQLException{
		List<Group> groups = new ArrayList<Group>();
		
		while(rs.next()){
			Group group = new Group();
			group.setGroupId(rs.getInt(1));
			group.setDeptName(rs.getString(2));
			group.setGroupName(rs.getString(3));
			group.setMember1(rs.getString(4));
			group.setMember2(rs.getString(5));
			group.setMember3(rs.getString(6));
			group.setMember4(rs.getString(7));
			group.setMember5(rs.getString(8));
			group.setMember6(rs.getString(9));
			group.setDepartmentId(rs.getInt(10));
			
			groups.add(group);
		}
		
		return groups;
	}
	
	/**
	 * This method returns the name of a department, given its id.
	 * 
	 * @param deptId	id of the department
	 * @return String	name of the department
	 * @throws Exception
	 */

	public static String getDeptNameById(int deptId) throws Exception{
		String deptName = null;
		
		Connection conn = DatabaseAccess.connectDataBase();
		Statement statement = conn.createStatement();
		String query = "select dept_name from department "
				+ "where id=" + deptId;
		ResultSet rs = statement.executeQuery(query);
		if(rs != null){
			if(rs.next()){
				deptName = rs.getString("dept_name");
			 }
		}
		conn.close();
		
		return deptName;
	}
	
	/**
	 * This method returns a String that represents either the group name or the employee name, which
	 * a report, given as a Report object, is associated to.
	 * 
	 * @param report	Report object
	 * @return String
	 * @throws Exception
	 */
	public static String getReportFor(Report report) throws Exception{
		String reportFor = null;
		Connection conn = DatabaseAccess.connectDataBase();
		Statement statement = conn.createStatement();
	
		//Employee report
		if(report.getReportType().equals("e")){
			reportFor = DatabaseManagement.selectEmployeeByReport(report).getFirstName() + " " +
					DatabaseManagement.selectEmployeeByReport(report).getLastName(); 
		}
		//Group report
		else if(report.getReportType().equals("g")){
			reportFor = DatabaseManagement.selectGroupByReport(report).getGroupName();
		}
		return reportFor;
	}
	
	/**
	 * This method returns an Employee object based on the employee Id.
	 * 
	 * @param employeeId
	 * @return
	 * @throws Exception
	 */
	public static Employee getEmployeeById(int employeeId) throws Exception{
		Connection conn = DatabaseAccess.connectDataBase();
		String query = "select first_name, last_name from employee where emp_id=" + employeeId;
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(query);
		
		Employee employee = new Employee();
		if(rs!= null && rs.next()){
			employee.setFirstName(rs.getString("first_name"));
			employee.setLastName(rs.getString("last_name"));
		}
		return employee;
	}
	
	/**
	 * This method returns a Group object based on the group Id.
	 * 
	 * @param groupId
	 * @return
	 * @throws Exception
	 */
	public static Group getGroupById(int groupId) throws Exception{
		Connection conn = DatabaseAccess.connectDataBase();
		String query = "select group_name from group where group_id=" + groupId;
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(query);
		
		Group group = new Group();
		if(rs!= null && rs.next()){
			group.setGroupName("group_name");
		}
		return group;
	}
}



