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
* Description: This servlet handles the login of the user into the application.
*********************************************************************************/

package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dataModel.User;
import database.DatabaseAccess;
import helper.AuthenticationHelper;
import helper.DatabaseManagement;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public Login() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "login.jsp";
		
		// Check if user chose Remember Me (automatically log the user in and redirect them to the home page)
		try{
			Connection conn = null;
			DatabaseAccess.createDatabase();
			conn = DatabaseAccess.connectDataBase();
			
			// Check if user has the RememberMe cookies (uuid and user)
			if(AuthenticationHelper.isRememberCookies(request, conn)){
				path = "home.jsp";
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		
		response.sendRedirect(path);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// If the form was submitted
		String username;
		String password;
		String rememberMe;
		username = request.getParameter("username");
		password = request.getParameter("password");
		rememberMe = request.getParameter("rememberMe");
		
		Connection conn = null;
		HttpSession session = request.getSession(true);
		
		String redirectTo = "login.jsp";
		
		if(!username.isEmpty() && !password.isEmpty()){
			try {
				DatabaseAccess.createDatabase();
				conn = DatabaseAccess.connectDataBase();
				// Read from the users table
				boolean authSuccess = false;
				User aUser = new User();
				
				//Validate the user/password combination exists in the Users table
				Statement statement = conn.createStatement();
				ResultSet rs = statement.executeQuery("select * from appusers where username='" 
						+ username + "' and password='" + password + "';" );
				if(rs != null){
					if (rs.next()) {
						aUser.setId(Integer.parseInt(rs.getString(1)));
						aUser.setFirstname(rs.getString(2));
						aUser.setLastname(rs.getString(3));
						aUser.setEmail(rs.getString(4));
						aUser.setRole(rs.getString(5));
						aUser.setUsername(rs.getString(6));
						aUser.setPassword(rs.getString(7));
						authSuccess = true;
					}
				}

				if(authSuccess){
					session.setAttribute("user", aUser);
					//If the user doesn't make any request in 20min, the session will expire
					session.setMaxInactiveInterval(20*60);
				
					//Create 2 cookies to remember the user later
					if(rememberMe != null) {
						String uuid =  UUID.randomUUID().toString(); // Unique identifier
						String userId = Integer.toString(aUser.getId()); // User ID of the authenticated user
						Cookie token = new Cookie("uuid", uuid);
						Cookie user = new Cookie("user", userId);
						
						token.setMaxAge(365 * 24 * 60 * 60); // one year
						user.setMaxAge(365 * 24 * 60 * 60); // one year
						
						//Write the token in the database
						DatabaseManagement.insertUserToken(uuid, userId, conn);
						
						response.addCookie(token);
						response.addCookie(user);
						

					}
						
					//Send the user to the home page
					redirectTo = "home.jsp";
					
				}
				else {
					session.setAttribute("error", "Invalid username and/or password.");
				}
			}
			catch(Exception e){
				System.out.println(e);
			}
			finally {
				try{
					// Close the connection
					conn.close();
					response.sendRedirect(redirectTo);
					return;
				}
				catch(SQLException ex){
					ex.printStackTrace();
				}
			}
		}
		else {
			response.sendRedirect(redirectTo);
		}
	}

}
