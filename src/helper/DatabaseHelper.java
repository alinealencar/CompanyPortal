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
import java.sql.ResultSet;
import java.sql.Statement;

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
	public static int getDeptId(String deptName, Connection conn) 
		throws Exception {
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
	public static int getEmpId(String fname, String lname, Connection conn) 
			throws Exception {
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
			return result;
		}
		
	/**
	 * This method returns a group ID according to its name.
	 * @param groupName	String that holds a group name.
	 * @param conn	Connection object that holds a connection to the database.
	 * @return int	Integer that represents a group ID.
	 * @throws Exception
	 */
	public static int getGroupId(String groupName, Connection conn) 
			throws Exception {
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
	
	
}				



