package Database;
/****************************************************************************************************
* Description: DatabaseAccess - Example provides access to database
****************************************************************************************************/
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseAccess {
	private String username = "admin";
	private String password = "admin";
	private String database = "COMP3095";
	
	private static Connection connect = null;
	  
	public Connection connectDataBase() throws Exception {
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
//			connect = DriverManager
//		          .getConnection("jdbc:mysql://localhost:3306/" + database + "?"
//		              + "user=" + username + "&password=" + password);
			connect = DriverManager
			          .getConnection("jdbc:mysql://localhost:3306/" + database,
			              username, password);
	      return connect;
	    } catch (Exception e) {
	      throw e;
	    } 
	  }
	
	public String getFoo() { return "foo"; }
}
