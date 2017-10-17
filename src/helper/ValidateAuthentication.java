package helper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ValidateAuthentication {
	public static boolean isValidLogin(String username, String password, Connection conn) 
		throws Exception {
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("select * from user where username='" 
				+ username + "' and password='" + password + "' " );
		return (rs != null);
	}
}
