package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Database.DatabaseAccess;
import helper.ValidateAuthentication;

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
		
		// If any of the two parameters are missing, redirect user
		// back to the login servlet without displaying any error messages
		if(username.isEmpty() || password.isEmpty()){
			//return;
			request.getRequestDispatcher("/").include(request,response);
		}
		else{
		
			try {
				DatabaseAccess.createDatabase();
				conn = DatabaseAccess.connectDataBase();
				// Read from the users table
//				boolean authSuccess = false;
//				User aUser;
				
				//Validate the user/password combination exists in the Users table
//				Statement statement = conn.createStatement();
//				ResultSet rs = statement.executeQuery("select * from appusers where username='" 
//						+ username + "' and password='" + password + "';" );
//				if(rs != null){
//					if (rs.next()) {
//						String fName = rs.getString(1);
//						String lName = rs.getString(2);
//						String email = rs.getString(3);
//						String role = rs.getString(4);
//						aUser = new User(fName, lName, email, role);
//					}
//				}

				if(ValidateAuthentication.isValidLogin(username, password, conn)){
				//If the authentication is successful
//				if(authSuccess){
//					session.setAttribute("user", aUser);
					session.setAttribute("user", username);
					//Send the user to the home page
					request.getRequestDispatcher("/WEB-INF/home.jsp").include(request,response);
					
				}
				else {
					session.setAttribute("error", "Invalid username and/or password.");
					//response.sendRedirect("/");
					//return;
					request.getRequestDispatcher("/").include(request,response);
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

