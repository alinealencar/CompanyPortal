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
* Description: This class provides the the validation of users in the authentication 
* 				process.
*********************************************************************************/

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
