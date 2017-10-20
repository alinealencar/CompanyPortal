/*********************************************************************************
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
* Description: This class provides the connection and creation of the database.
*********************************************************************************/

package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseAccess {
	private static String username = "admin";
	private static String password = "admin";
	private static String database = "COMP3095";
	
	private static Connection connect = null;
	  
	public static Connection connectDataBase() throws Exception {
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Set up the connection with the DB
			connect = DriverManager
			          .getConnection("jdbc:mysql://localhost:3306/" + database,
			              username, password);
	      return connect;
	    } catch (Exception e) {
	      throw e;
	    } 
	  }
	
	public static void createDatabase() throws Exception {
		
	}
	
	public String getFoo() { return "foo"; }
}

