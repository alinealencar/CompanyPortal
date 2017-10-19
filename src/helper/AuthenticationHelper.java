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

import dataModel.User;

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
	
	public static boolean isRememberCookies(HttpServletRequest request, Connection conn) throws Exception
	{
		Cookie[] cookies = request.getCookies();
		Cookie userIdCookie = null;
		Cookie tokenCookie = null;
		
		boolean isRemember = false;
		
		if(cookies != null){
			for(Cookie aCookie : cookies){
				if(aCookie.getName().equals("user")){
					userIdCookie = aCookie;
				}
				if(aCookie.getName().equals("uuid")){
					tokenCookie = aCookie;
				}
			}
			if(userIdCookie != null && tokenCookie != null)
				isRemember = logUserRemember(request, conn, userIdCookie, tokenCookie);
		}

		return isRemember;
	}
	
	public static boolean logUserRemember(HttpServletRequest request, Connection conn, Cookie userId, Cookie token)
			throws Exception {
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("select * from appusers where id=" 
					+Integer.parseInt(userId.getValue()) + " and token='" + token.getValue() + "'" );
			if(rs != null){
				// There is any entry in the appusers table that satisfies the conditions for user and token
				return (rs.next());
			}
			else
				return false;
	}
	
	public static boolean isLoggedIn(HttpSession session){
		return !(session == null || session.getAttribute("user") == null);
	}
}
