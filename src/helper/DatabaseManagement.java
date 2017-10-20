/***********************************************************************
* Project: COMP3095_TechGirls
* Assignment: Assignment 1
* Author(s): Aline Neves Alencar,
* 			 Kie Ogiya,
* 			 Maria Alyssa Villacete,
* 			 Princess Ilasin
* Student Number: 101036808,
* 				  100984638
* 				  100923181
* 				  100879176
* Date: October 17, 2017.
* Description: This class contains methods to access/manage the database.
*************************************************************************/

package helper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class DatabaseManagement {
	public static ResultSet selectFromTable(String tableName, Connection conn)
			throws Exception {
			Statement statement = conn.createStatement();
			String query = "select * from " + tableName;
			ResultSet rs = statement.executeQuery(query);
			
			/*if(rs != null){
				while(rs.next())
					rs.next();
			}*/
			return rs;
		}
	
	
	public static Boolean insertUserToken(String token, String userId, Connection conn)
		throws Exception {
		String query="update appusers set token=? where id=?";
		
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		
		preparedStmt.setString(1, token);
		preparedStmt.setInt(2, Integer.parseInt(userId));
		
		int rowsAffected = preparedStmt.executeUpdate();
		
		return(rowsAffected > 0);
		
	}
	
	public static Boolean insertEmployee(String fName, String lName, String empNo, String email, String hireYear, String position, Connection conn)
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
	
	public static Boolean insertDepartment(String deptName, String location, Connection conn)
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
	
	public static Boolean insertGroup(String deptName, String groupName, String member1, String member2, String member3, String member4, String member5, String member6, Connection conn)
			throws Exception {
			member2 = null;
			member3 = null;
			member4 = null;
			member5 = null;
			member6 = null;
			Statement statement = conn.createStatement();
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
	
	public static ResultSet selectEmployees(String deptName, Connection conn) 
			throws Exception {
			String result = "";
			Statement statement = conn.createStatement();
			String query = "select firstname, lastname from employee "
					+ "inner join department on department.id = employee.dept_id_fk "
					+ "where dept_name='" + deptName + "'";
			ResultSet rs = statement.executeQuery(query);
			
			return rs;
	}
	
}

