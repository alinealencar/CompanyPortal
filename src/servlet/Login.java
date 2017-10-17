package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import database.DatabaseAccess;
import helper.ValidateAuthentication;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection conn = null;
	String redirectTo = "login.jsp";
	String username;
	String password;
	
	
    public Login() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		username = request.getParameter("username");
		password = request.getParameter("password");
		HttpSession session = request.getSession(true); // Returns the session associated with this request. If null, create a new session.
		
		try {
			DatabaseAccess.createDatabase();
			conn = DatabaseAccess.connectDataBase();
			// Read from the users table
			if(ValidateAuthentication.isValidLogin(username, password, conn)){
				session.setAttribute("user", username);
				redirectTo = "index.jsp";
			}
			else {
				session.setAttribute("error", "Invalid username and/or password.");
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
		finally {
			try {
				// Close the connection
				conn.close();
			}
			catch(SQLException ex){
				ex.printStackTrace();
			}
			// Send user to the appropriate page
			response.sendRedirect(redirectTo);
		}
	}

}
