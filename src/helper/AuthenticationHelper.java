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
* Description: This class provides the methods required in the validation of the
* 					authentication process, as well as handles the RememberMe
* 					feature (when a user selects RememberMe, they will be automatically
* 					logged in in the server side) process.
*********************************************************************************/

package helper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dataModel.User;
import database.DatabaseAccess;

public class AuthenticationHelper {
	
	/**
	 * This method checks for the existence of the two cookies associated with the
	 * RememberMe feature, user and token.
	 * @param request	HttpServletRequest object
	 * @param conn	Connection to the database
	 * @return	boolean	Returns true if the cookies exist, false otherwise
	 * @throws Exception
	 */
	public static boolean isRememberCookies(HttpServletRequest request) throws Exception
	{
		Connection conn = DatabaseAccess.connectDataBase();
		
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
	
	/**
	 * This method checks if the combination of the value of the user and token cookies
	 * exist in the appusers table in the database.
	 * @param request	HttpServletRequest object
	 * @param conn	Connection to the database
	 * @param userId	Cookie userId, contains the id for a specific user from the appusers table
	 * @param token		Cookie token, contains a uuid (random unique identifier) for a specific user
	 * @return	boolean	Returns true if the combination is found in the table, false otherwise
	 * @throws Exception
	 */
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
	
	/**
	 * Checks for a username/password combination in the database.
	 * @param conn	Connection to the database
	 * @param username	String that contains the username
	 * @param password	String that contains the password
	 * @return	User	Returns a null object of type user if the combination is
	 * 					not found. Returns an object with the user's information pulled
	 * 					from the database
	 * @throws Exception
	 */
	
	public static User isValidUser(String username, String password)
		throws Exception {
		Connection conn = DatabaseAccess.connectDataBase();
		
		User aUser = null;
		
		//Validate the user/password combination exists in the Users table
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("select * from appusers where username='" 
				+ username + "' and password='" + password + "';" );
		if(rs != null){
			if (rs.next()) {
				aUser = new User();
				aUser.setId(Integer.parseInt(rs.getString(1)));
				aUser.setFirstname(rs.getString(2));
				aUser.setLastname(rs.getString(3));
				aUser.setEmail(rs.getString(4));
				aUser.setRole(rs.getString(5));
				aUser.setUsername(rs.getString(6));
			}
		}
		
		return aUser;
	}
	
	/**
	 * This method checks if the session is null or if the user attribute of the
	 * session is null. The user attribute of the session contains a user object. 
	 * If this object is null, it means the user is not logged in.
	 * @param session	HttpSession object (current active session)
	 * @return boolean	True if the user is currently logged in, false otherwise
	 */
	public static boolean isLoggedIn(HttpSession session){
		return !(session == null || session.getAttribute("user") == null);
	}
}
