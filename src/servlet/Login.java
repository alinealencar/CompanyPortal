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
import helper.CookieUtilities;
import helper.DatabaseManagement;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public Login() {
        super();
    }
    
    /**
     * This method is called whenever this servlet receives a GET request.
     * The method is responsible for checking whether the user is already logged in.
     * It redirects the logged in user to the home page home.jsp.
     * If the user is required to log in, it redirects them to the login page login.jsp.
     * 
     * The RememberMeFilter is applied to the login.jsp page to test for the RememberMe option.
     * 
     * @param	request	HttpServletRequest object
     * @param	response HttpServletResponse object
     * @return	void
     * @exception	ServletException
     * @exception	 IOException on input error
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
<<<<<<< Updated upstream
<<<<<<< Updated upstream
		String path = "login.jsp";
		HttpSession session = request.getSession();
		
		if(AuthenticationHelper.isLoggedIn(session)){
			path = "home.jsp";
=======
=======
>>>>>>> Stashed changes
		// Check if user chose Remember Me (automatically log the user in and redirect them to the home page)
		try{
			Connection conn = null;
			DatabaseAccess.createDatabase();
			conn = DatabaseAccess.connectDataBase();
			
			// Check if user has the RememberMe cookies (uuid and user)
			if(AuthenticationHelper.isRememberCookies(request, conn))
				response.sendRedirect("home.jsp");
			else
				response.sendRedirect("login.jsp");
		} catch (Exception e){
			e.printStackTrace();
>>>>>>> Stashed changes
		}
	}

	/**
	 * This method is called whenever this servlet receives a POST request (the form
	 * in the login.jsp page is submitted).
	 * This method is responsible for authentication the user to the application,
	 * according to the information sent in the form present in the login.jsp page.
	 * 
	 * @param	request	HttpServletRequest object
     * @param	response HttpServletResponse object
     * @return	void
     * @exception	ServletException
     * @exception	 IOException on input error
	 */
	
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
		
<<<<<<< Updated upstream
<<<<<<< Updated upstream
		String redirectTo = "login.jsp";
=======
		String redirectTo = "Login";
>>>>>>> Stashed changes
=======
		String redirectTo = "Login";
>>>>>>> Stashed changes
		
		if(!username.isEmpty() && !password.isEmpty()){
			try {
				DatabaseAccess.createDatabase();
				conn = DatabaseAccess.connectDataBase();
<<<<<<< Updated upstream
				User aUser = AuthenticationHelper.isValidUser(conn, username, password);
=======
				// Read from the users table
				boolean authSuccess = false;
				User aUser = new User();
				
				//Validate the user/password combination exists in the Users table
				Statement statement = conn.createStatement();
				ResultSet rs = statement.executeQuery("select * from appusers where username='" 
						+ username + "' and password='" + password + "';" );
				if(rs != null){
					if (rs.next()) {
						aUser.setId(rs.getInt(1));
						aUser.setFirstname(rs.getString(2));
						aUser.setLastname(rs.getString(3));
						aUser.setEmail(rs.getString(4));
						aUser.setRole(rs.getString(5));
						aUser.setUsername(rs.getString(6));
						aUser.setPassword(rs.getString(7));
						authSuccess = true;
					}
				}
>>>>>>> Stashed changes

				if(aUser != null){
					session.setAttribute("user", aUser);
					session.setAttribute("fName", aUser.getFirstName());

					//If the user doesn't make any requests in 60min, the session expires
					session.setMaxInactiveInterval(60*60);
				
					//Create 2 cookies to remember the user later
					if(rememberMe != null) {
						String uuid =  UUID.randomUUID().toString(); // Unique identifier
						String userId = Integer.toString(aUser.getId()); // User ID of the authenticated user
						
						Cookie token = CookieUtilities.createRememberMeCookie("uuid", uuid);
						Cookie user = CookieUtilities.createRememberMeCookie("user", userId);
						
						//Write the token in the database
						DatabaseManagement.insertUserToken(uuid, userId, conn);
						
						response.addCookie(token);
						response.addCookie(user);
					}
					
					//Send the user to the home page
					redirectTo = "home.jsp";
					
				}
				else
					session.setAttribute("error", "Invalid username and/or password.");
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
		else
			response.sendRedirect(redirectTo);
	}

}
