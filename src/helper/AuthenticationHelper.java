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
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import databaseTables.User;

public class AuthenticationHelper {
//	public static boolean isValidLogin(String username, String password, Connection conn) 
//		throws Exception {
//		Statement statement = conn.createStatement();
//		ResultSet rs = statement.executeQuery("select * from appusers where username='" 
//				+ username + "' and password='" + password + "'" );
//		if(rs != null){
//			// There is any entry in the appusers table that satisfies the conditions for username and password
//			return(rs.next());
//		}
//		else return false;
//	}
	
	public static Cookie[] isRememberCookies(HttpServletRequest request){
		Cookie[] cookies = request.getCookies();
		Cookie[] returnCookies = {};
		if(cookies != null){
			for(Cookie aCookie : cookies){
				if(aCookie.getName().equals("user")){
					cookies[0] = aCookie;
				}
				if(aCookie.getName().equals("uuid")){
					cookies[1] = aCookie;
				}
			}
			return returnCookies;
		}
		else
			return null;
	}
	
	public static boolean logUserRemember(HttpServletRequest request, Connection conn)
			throws Exception {
		
		Cookie[] rememberCookies = isRememberCookies(request);
		
		if(isRememberCookies(request) != null){
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("select * from appusers where id='" 
					+ rememberCookies[0].getValue() + "' and token='" + rememberCookies[1].getValue() + "'" );
			if(rs != null){
				// There is any entry in the appusers table that satisfies the conditions for user and token
				return(rs.next());
			}
			else
				return false;
		}
		else
			return false;

	}
	
	public static boolean isLoggedIn(HttpSession session){
		return !(session == null || session.getAttribute("user") == null);
	}
}
