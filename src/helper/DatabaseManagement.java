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
* Description: This class contains methods to read from and insert into the 
* 				database
*********************************************************************************/

package helper;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dataModel.Employee;
import dataModel.Group;
import dataModel.Report;
import dataModel.ReportTemplate;

import database.DatabaseAccess;

import java.sql.PreparedStatement;

public class DatabaseManagement {
	
	/**
	 * This class provides the ability to get all rows from a specific table.
	 * @param tableName	String that holds the table name.
	 * @return ResultSet This returns an object of ResultSet type, that holds information
	 * 						about all rows returned from the select statement.
	 * @throws Exception
	 */
	public static ResultSet selectFromTable(String tableName)
			throws Exception {
		Connection conn = DatabaseAccess.connectDataBase();
		Statement statement = conn.createStatement();
		String query = "select * from " + tableName;
		ResultSet rs = statement.executeQuery(query);
	
		//conn.close();
		
		return rs;
	}
	
	/**
	 * This method inserts a unique token in the token column of the appusers table
	 * in the database. This token, combined with the id of the user, provide a secure
	 * way to implement the RememberMe feature.
	 * @param token	String that holds a unique series of characters.
	 * @param userId	String that holds the id of the user that wants to be remembered.
	 * @return	boolean	This returns true if the operation was successful, false otherwise.
	 * @throws Exception
	 */
	public static boolean insertUserToken(String token, String userId)
		throws Exception {
		Connection conn = DatabaseAccess.connectDataBase();
		
		String query="update appusers set token='" + token + "' where id=" + Integer.parseInt(userId);
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		int rowsAffected = preparedStmt.executeUpdate();
		
		conn.close();
		
		return(rowsAffected > 0);
		
	}
	
	/**
	 * This method inserts data about an employee into the Employee table of the database.
	 * @param fName	
	 * @param lName
	 * @param empNo
	 * @param email
	 * @param hireYear
	 * @param position
	 * @return boolean	It returns true if the insert operation was successful, false otherwise.
	 * @throws Exception
	 */
	public static boolean insertEmployee(String fName, String lName, String empNo, String email, String hireYear, String position)
			throws Exception {
		Connection conn = DatabaseAccess.connectDataBase();
		String query = "insert into employee(firstname, lastname, emp_no, email, hire_year, job_position, dept_id_fk) values(?,?,?,?,?,?,?)";
			
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		    
		preparedStmt.setString (1, fName);
		preparedStmt.setString (2, lName);
		preparedStmt.setString (3, empNo);
		preparedStmt.setString (4, email);
		preparedStmt.setString (5, hireYear);
		preparedStmt.setString (6, position);
		preparedStmt.setInt(7, DatabaseHelper.getEmployeeDeptID(position));
		    
		int rowsAffected = preparedStmt.executeUpdate();
		
		conn.close();
		
		return rowsAffected > 0;     
	}
	
	/**
	 * This method inserts info about a department into the Department table of the database.
	 * @param deptName
	 * @param location
	 * @return	boolean It returns true if the insert operation was successful, false otherwise.
	 * @throws Exception
	 */
	public static boolean insertDepartment(String deptName, String location, Connection conn)
			throws Exception {
			String query = "insert into department(dept_name, location) values(?,?)";
			
		    PreparedStatement preparedStmt = conn.prepareStatement(query);
		    
		    preparedStmt.setString (1, deptName);
		    preparedStmt.setString (2, location);
		    
		    int rowsAffected = preparedStmt.executeUpdate();
		    if (rowsAffected > 0)
		    	return true;
		    else
		    	return false;
		    
	}
	
	/**
	 * This inserts the group information into the Group table of the database.
	 * @param deptName
	 * @param groupName
	 * @param member1
	 * @param member2
	 * @param member3
	 * @param member4
	 * @param member5
	 * @param member6
	 * @return boolean It returns true if the insert operation was successful, false otherwise.
	 * @throws Exception
	 */
	public static boolean insertGroup(String deptName, String groupName, String member1, String member2, String member3, String member4, String member5, String member6)
			throws Exception {
		Connection conn = DatabaseAccess.connectDataBase();
				
		String query = "insert into groups(dept_name, group_name, member1, member2, member3, member4, member5, member6, dept_id_fk)  values(?,?,?,?,?,?,?,?,?)";
		
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		    
		preparedStmt.setString (1, deptName);
		preparedStmt.setString (2, groupName);
		preparedStmt.setString (3, member1);
		preparedStmt.setString (4, member2);
		preparedStmt.setString (5, member3);
		preparedStmt.setString (6, member4);
		preparedStmt.setString (7, member5);
		preparedStmt.setString (8, member6);
		preparedStmt.setInt (9, DatabaseHelper.getDeptId(deptName));
			
	    int rowsAffected = preparedStmt.executeUpdate();
	    return rowsAffected > 0;
	}
	
	/**
	 * This inserts the employee and group IDs into the Employee_Groups table of the database.
	 * @param empId
	 * @param groupId
	 * @throws Exception
	 */
	public static void insertEmployeeGroup(int empId, int groupId) throws Exception { 
		Connection conn = DatabaseAccess.connectDataBase();
		
		String query = "insert into employee_groups(emp_id_fk, groups_id_fk) values(?,?)";
		
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		
		preparedStmt.setInt(1, empId);
		preparedStmt.setInt(2, groupId);
		preparedStmt.executeUpdate();
		
		conn.close();
	}
	
	/**
	 * This method gets all the employees from a specific department.
	 * @param deptName	String that holds the department name.
	 * @return	ResultSet This object holds all rows in the result of the select statement.
	 * @throws Exception
	 */
	public static ResultSet selectEmployees(String deptName) 
			throws Exception {
		Connection conn = DatabaseAccess.connectDataBase();
		Statement statement = conn.createStatement();
		String query = "select * from employee "
				+ "inner join department on department.id = employee.dept_id_fk "
				+ "where dept_name='" + deptName + "'";
		ResultSet rs = statement.executeQuery(query);
		//conn.close();
		return rs;
	}
	
	/**
	 * This inserts the attendance into the Attendance table of the database.
	 * @param attendanceDate - date of the attendance marking
	 * @param present - if the employee is present on the date of the attendance marking
	 * @param deptName - department name
	 * @throws Exception
	 */
	public static boolean insertAttendance(Date attendanceDate, String deptName)
			throws Exception {
			Connection conn = DatabaseAccess.connectDataBase();
			String query = "insert into attendance(attendance_date, dept_name, dept_id_fk) values(?,?,?)";
			
		    PreparedStatement preparedStmt = conn.prepareStatement(query);
		    
		    preparedStmt.setDate (1, attendanceDate);
		    //preparedStmt.setBoolean (2, present);
		    preparedStmt.setString (2, deptName);
		    preparedStmt.setInt (3, DatabaseHelper.getDeptId(deptName));
		    
		    int rowsAffected = preparedStmt.executeUpdate();
		    if (rowsAffected > 0)
		    	return true;
		    else
		    	return false;
		    
	}
	
	/**
	 * This inserts the employee and attendance IDs into the Employee_Attendance table of the database.
	 * @param empId
	 * @param attendanceId
	 * @throws Exception
	 */
	public static void insertEmployeeAttendance(int empId, int attendanceId) throws Exception { 
		Connection conn = DatabaseAccess.connectDataBase();
		
		String query = "insert into employee_attendance(emp_id_fk, attendance_id_fk) values(?,?)";
		
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		
		//preparedStmt.setBoolean(1, present);
		preparedStmt.setInt(1, empId);
		preparedStmt.setInt(2, attendanceId);
		preparedStmt.executeUpdate();
		
		conn.close();
	}
	
	/**
	 * This method inserts a report template into the database.
	 * 
	 * @param reportTemplate
	 * @return
	 * @throws Exception
	 */
	public static boolean insertReportTemplate(ReportTemplate reportTemplate) throws Exception{
		Connection conn = DatabaseAccess.connectDataBase();
		
		String query = "insert into report_template(template_name, template_date, "
				+ "section1, s1_criteria1, s1_criteria2, s1_criteria3, s1_criteria4, s1_criteria5,"
				+ "s1_c1_maximum, s1_c2_maximum, s1_c3_maximum, s1_c4_maximum, s1_c5_maximum,"
				+ "section2, s2_criteria1, s2_criteria2, s2_criteria3,"
				+ "s2_c1_maximum, s2_c2_maximum, s2_c3_maximum,"
				+ "section3, s3_criteria1, s3_criteria2, s3_criteria3,"
				+ "s3_c1_maximum, s3_c2_maximum, s3_c3_maximum,"
				+ "dept_id_fk) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
				+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		
		preparedStmt.setString(1, reportTemplate.getTemplateName());
		preparedStmt.setDate(2, reportTemplate.getTemplateDate());
		preparedStmt.setInt (28, reportTemplate.getDeptId());
		//Section 1
		preparedStmt.setString(3, reportTemplate.getSection1());
		preparedStmt.setString(4,  reportTemplate.getS1Criteria1());
		preparedStmt.setString(5, reportTemplate.getS1Criteria2());
		preparedStmt.setString(6, reportTemplate.getS1Criteria3());
		preparedStmt.setString(7, reportTemplate.getS1Criteria4());
		preparedStmt.setString(8, reportTemplate.getS1Criteria5());
		preparedStmt.setInt(9, reportTemplate.getS1Crit1Max());
		preparedStmt.setInt(10, reportTemplate.getS1Crit2Max());
		preparedStmt.setInt(11, reportTemplate.getS1Crit3Max());
		preparedStmt.setInt(12, reportTemplate.getS1Crit4Max());
		preparedStmt.setInt(13, reportTemplate.getS1Crit5Max());
		//Section 2
		preparedStmt.setString(14, reportTemplate.getSection2());
		preparedStmt.setString(15,  reportTemplate.getS2Criteria1());
		preparedStmt.setString(16, reportTemplate.getS2Criteria2());
		preparedStmt.setString(17, reportTemplate.getS2Criteria3());
		preparedStmt.setInt(18, reportTemplate.getS2Crit1Max());
		preparedStmt.setInt(19, reportTemplate.getS2Crit2Max());
		preparedStmt.setInt(20, reportTemplate.getS2Crit3Max());
		//Section 3
		preparedStmt.setString(21, reportTemplate.getSection3());
		preparedStmt.setString(22,  reportTemplate.getS3Criteria1());
		preparedStmt.setString(23, reportTemplate.getS3Criteria2());
		preparedStmt.setString(24, reportTemplate.getS3Criteria3());
		preparedStmt.setInt(25, reportTemplate.getS3Crit1Max());
		preparedStmt.setInt(26, reportTemplate.getS3Crit2Max());
		preparedStmt.setInt(27, reportTemplate.getS3Crit3Max());
		
		int rowsAffected = preparedStmt.executeUpdate();
		return (rowsAffected > 0);
	}

	public static Report selectReportById(String reportId) throws Exception{
		Connection conn = DatabaseAccess.connectDataBase();
		Report report = new Report();
		String query = "select * from report where report_id = " + Integer.parseInt(reportId);
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		ResultSet rs = preparedStmt.executeQuery(query);
		
		if(rs.next()){
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
		}
		return report;
	}
	
	public static ReportTemplate selectReportTemplateById(String templateId) throws Exception {
		Connection conn = DatabaseAccess.connectDataBase();
		ReportTemplate template = new ReportTemplate();
		
		String query = "select * from report_template where template_id = " + Integer.parseInt(templateId);
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		ResultSet rs = preparedStmt.executeQuery(query);
		
		template = (DatabaseHelper.getReportTemplates(rs)).get(0);
		
		return template;
	}
	
	/**
	 * This method retrieves all report templates associated to a department, provided a department name.
	 * @param deptName
	 * @return
	 * @throws Exception
	 */
	public static ResultSet selectReportTemplateByDepartment(String deptName) throws Exception {
		Connection conn = DatabaseAccess.connectDataBase();
		
		String query = "select * from report_template where dept_id_fk = " + DatabaseHelper.getDeptId(deptName);
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		ResultSet rs = preparedStmt.executeQuery(query);
		
		return rs;
	}
	
	/**
	 * This method retrieves all reports associated to a specific template.
	 * 
	 * @param templateId
	 * @return
	 * @throws Exception
	 */
	public static ResultSet selectReportByTemplate(String templateId) throws Exception{
		Connection conn = DatabaseAccess.connectDataBase();
		
		String query = "select * from report where template_id_fk = " + Integer.parseInt(templateId);
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		ResultSet rs = preparedStmt.executeQuery(query);
		
		return rs;
	}
	
	/**
	 * This method updates the attendance status of an employee, provided the employee id.
	 * 
	 * @param empId
	 * @throws Exception
	 */
	public static void updatePresentEmployees(int empId) throws Exception { 
		Connection conn = DatabaseAccess.connectDataBase();
		
		String query = "update employee_attendance set present = " + 1 + " where emp_id_fk = " + empId;
		
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.executeUpdate();
		
		conn.close();
	}

	/**
	 * This method gets all the values from the attendance table for a specific department.
	 * @param deptName	String that holds the department name.
	 * @return	ResultSet This object holds all rows in the result of the select statement.
	 * @throws Exception
	 */
	public static ResultSet selectAttendanceByDept(String deptName) 
			throws Exception {
		Connection conn = DatabaseAccess.connectDataBase();
		Statement statement = conn.createStatement();
		String query = "select * from attendance "
				+ "where dept_name='" + deptName + "'";
		ResultSet rs = statement.executeQuery(query);

		return rs;
	}
	
	/**
	 * This method gets all the values from the attendance table for a specific department.
	 * @param deptName	String that holds the department name.
	 * @return	ResultSet This object holds all rows in the result of the select statement.
	 * @throws Exception
	 */
	public static ResultSet selectPresentEmployees(int deptId) 
			throws Exception {
		Connection conn = DatabaseAccess.connectDataBase();
		Statement statement = conn.createStatement();
		String query = "select * from employee_attendance "
				+ "where dept_id_fk='" + deptId + "'";
		ResultSet rs = statement.executeQuery(query);

		return rs;
	}
	
	public static boolean updateReport(Report report) throws Exception{
		Connection conn = DatabaseAccess.connectDataBase();
		
		String query = "update report set "+
				"s1_c1_evaluation =" + report.getS1Crit1() +
				", s1_c2_evaluation =" + report.getS1Crit2() +
				", s1_c3_evaluation =" + report.getS1Crit3() +
				", s1_c4_evaluation =" + report.getS1Crit4() +
				", s1_c5_evaluation =" + report.getS1Crit5() +
				", comment1 = '" + report.getComment1() + "'" +
				", s2_c1_evaluation =" + report.getS2Crit1() +
				", s2_c2_evaluation =" + report.getS2Crit2() +
				", s2_c3_evaluation =" + report.getS2Crit3() +
				", comment2 = '" + report.getComment2() + "'" +
				", s3_c1_evaluation =" + report.getS3Crit1() +
				", s3_c2_evaluation =" + report.getS3Crit2() +
				", s3_c3_evaluation =" + report.getS3Crit3() +
				", comment3 = '" + report.getComment3() + "'" +
				"where report_id = " + report.getReportId();
		
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		
		int rowsAffected = preparedStmt.executeUpdate();
	    return rowsAffected > 0;
		
	}

	public static void updatePresentEmployees(int empId, int attendanceId) throws Exception { 
		Connection conn = DatabaseAccess.connectDataBase();
		
		String query = "update employee_attendance set present = " + 1 + " where emp_id_fk = " + empId + " and attendance_id_fk = " + attendanceId;
		
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.executeUpdate();
		
		conn.close();
	}
	
	public static Employee selectEmployeeByReport(Report report) throws Exception{
		Connection conn = DatabaseAccess.connectDataBase();
		
		Statement statement = conn.createStatement();
		String query = "select employee_id from employee_report where report_id_fk=" + report.getReportId();
		ResultSet rs = statement.executeQuery(query);
		
		Employee employee = new Employee();
		
		int employeeId = 0;
		if(rs.next()){
			employeeId = rs.getInt("employee_id");
		}
		
		employee = DatabaseHelper.getEmployeeById(employeeId);
		
		return employee;
	}
	
	public static Group selectGroupByReport(Report report) throws Exception{
Connection conn = DatabaseAccess.connectDataBase();
		
		Statement statement = conn.createStatement();
		String query = "select group_id from group_report where report_id_fk=" + report.getReportId();
		ResultSet rs = statement.executeQuery(query);
		
		Group group = new Group();
		
		int groupId = 0;
		if(rs.next()){
			groupId = rs.getInt("group_id");
		}
		
		group = DatabaseHelper.getGroupById(groupId);
		
		return group;
	}
	
	/**
	 * This method inserts a report into the database.
	 * 
	 * @param report
	 * @return
	 * @throws Exception
	 */
	public static boolean insertReport(Report report) throws Exception{
		Connection conn = DatabaseAccess.connectDataBase();
		
		String query = "insert into report(report_title, report_date, report_type,"
				+ "s1_c1_evaluation, s1_c2_evaluation, s1_c3_evaluation, s1_c4_evaluation, s1_c5_evaluation, comment1,"
				+ "s2_c1_evaluation, s2_c2_evaluation, s2_c3_evaluation, comment2,"
				+ "s3_c1_evaluation, s3_c2_evaluation, s3_c3_evaluation, comment3,"
				+ "template_id_fk)"
				+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		
		preparedStmt.setString(1, report.getReportTitle());
		preparedStmt.setDate(2, report.getReportDate());
		preparedStmt.setString(3, report.getReportType());
		//Section 1
		preparedStmt.setInt(4, report.getS1Crit1());
		preparedStmt.setInt(5, report.getS1Crit2());
		preparedStmt.setInt(6, report.getS1Crit3());
		preparedStmt.setInt(7, report.getS1Crit4());
		preparedStmt.setInt(8, report.getS1Crit5());
		preparedStmt.setString(9, report.getComment1());
		//Section 2
		preparedStmt.setInt(10, report.getS2Crit1());
		preparedStmt.setInt(11, report.getS2Crit2());
		preparedStmt.setInt(12, report.getS2Crit3());
		preparedStmt.setString(13, report.getComment2());
		//Section 3
		preparedStmt.setInt(14, report.getS3Crit1());
		preparedStmt.setInt(15, report.getS3Crit2());
		preparedStmt.setInt(16, report.getS3Crit3());
		preparedStmt.setString(17, report.getComment3());
		preparedStmt.setInt(18,  report.getTemplateId());
		
		int rowsAffected = preparedStmt.executeUpdate();
		return (rowsAffected > 0);
	}
}

