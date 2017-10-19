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

import database.DatabaseAccess;
import databaseTables.User;
import helper.AuthenticationHelper;
import helper.DatabaseManagement;

@WebServlet("/AuthenticateServlet")
public class AuthenticateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AuthenticateServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true); // Returns the session associated with this request. If null, create a new session.
		Connection conn = null;
		String username = (String) request.getAttribute("username");
		String password = (String) request.getAttribute("password");
		String rememberMe = (String) request.getAttribute("rememberMe");
		
		if(username.isEmpty() || password.isEmpty()){
			request.getRequestDispatcher("/").include(request,response);
		}
		else {
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
						aUser.setFirstname(rs.getString(1));
						aUser.setLastname(rs.getString(2));
						aUser.setEmail(rs.getString(3));
						aUser.setRole(rs.getString(4));
						aUser.setUsername(rs.getString(5));
						aUser.setPassword(rs.getString(6));
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
					//request.getRequestDispatcher("home.jsp").include(request,response);
					response.sendRedirect("home.jsp");
					return;
					
				}
				else {
					session.setAttribute("error", "Invalid username and/or password.");
					response.sendRedirect("/");
					return;
					//request.getRequestDispatcher("/").include(request,response);
				}
			}
			catch(Exception e){
				System.out.println(e);
			}
			finally {
				try{
					// Close the connection
					conn.close();
				}
				catch(SQLException ex){
					ex.printStackTrace();
				}
			}
		}

	}
}

