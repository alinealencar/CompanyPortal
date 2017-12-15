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
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class DatabaseManagement {
	
	/**
	 * This class provides the ability to get all rows from a specific table.
	 * @param tableName	String that holds the table name.
	 * @param conn	Connection object that represents a connection to the database.
	 * @return ResultSet This returns an object of ResultSet type, that holds information
	 * 						about all rows returned from the select statement.
	 * @throws Exception
	 */
	public static ResultSet selectFromTable(String tableName, Connection conn)
			throws Exception {
			Statement statement = conn.createStatement();
			String query = "select * from " + tableName;
			ResultSet rs = statement.executeQuery(query);
	
			return rs;
		}
	
	/**
	 * This method inserts a unique token in the token column of the appusers table
	 * in the database. This token, combined with the id of the user, provide a secure
	 * way to implement the RememberMe feature.
	 * @param token	String that holds a unique series of characters.
	 * @param userId	String that holds the id of the user that wants to be remembered.
	 * @param conn	Connection object that holds a connection to the database.
	 * @return	boolean	This returns true if the operation was successful, false otherwise.
	 * @throws Exception
	 */
	public static boolean insertUserToken(String token, String userId, Connection conn)
		throws Exception {
		String query="update appusers set token='" + token + "' where id=" + Integer.parseInt(userId);
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		int rowsAffected = preparedStmt.executeUpdate();
		
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
	 * @param conn
	 * @return boolean	It returns true if the insert operation was successful, false otherwise.
	 * @throws Exception
	 */
	public static boolean insertEmployee(String fName, String lName, String empNo, String email, String hireYear, String position, Connection conn)
			throws Exception {
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
		    if (rowsAffected > 0)
		    	return true;
		    else
		    	return false;	     
	}
	
	/**
	 * This method inserts info about a department into the Department table of the database.
	 * @param deptName
	 * @param location
	 * @param conn	Connection object that holds a connection to the database.
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
	 * @param conn Connection object that holds a connection to the database.
	 * @return boolean It returns true if the insert operation was successful, false otherwise.
	 * @throws Exception
	 */
	public static boolean insertGroup(String deptName, String groupName, String member1, String member2, String member3, String member4, String member5, String member6, Connection conn)
			throws Exception {
				
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
			preparedStmt.setInt (9, DatabaseHelper.getDeptId(deptName, conn));
			
		    int rowsAffected = preparedStmt.executeUpdate();
		    if (rowsAffected > 0)
		    	return true;
		    else
		    	return false;
	}
	
	/**
	 * This inserts the employee and group IDs into the Employee_Groups table of the database.
	 * @param empId
	 * @param groupId
	 * @param conn Connection object that holds a connection to the database.
	 * @throws Exception
	 */
	public static void insertEmployeeGroup(int empId, int groupId, Connection conn) throws Exception { 
		
		String query = "insert into employee_groups(emp_id_fk, groups_id_fk) values(?,?)";
		
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		
		preparedStmt.setInt(1, empId);
		preparedStmt.setInt(2, groupId);
		preparedStmt.executeUpdate();
		
	}
	
	/**
	 * This method gets all the employees from a specific department.
	 * @param deptName	String that holds the department name.
	 * @param conn	Connection object that holds a connection to the database.
	 * @return	ResultSet This object holds all rows in the result of the select statement.
	 * @throws Exception
	 */
	public static ResultSet selectEmployees(String deptName, Connection conn) 
			throws Exception {
			Statement statement = conn.createStatement();
			String query = "select firstname, lastname from employee "
					+ "inner join department on department.id = employee.dept_id_fk "
					+ "where dept_name='" + deptName + "'";
			ResultSet rs = statement.executeQuery(query);
			
			return rs;
	}
	
}

