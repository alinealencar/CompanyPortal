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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DatabaseAccess;
import helper.AuthenticationHelper;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public Login() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Check if user chose Remember Me (automatically log the user in and redirect them to the home page)
		try{
			Connection conn = null;
			DatabaseAccess.createDatabase();
			conn = DatabaseAccess.connectDataBase();
			
			if(AuthenticationHelper.logUserRemember(request, conn)){
				response.sendRedirect("home.jsp");
			}
			// If the user does not have a Remember Me activated, redirect them to the log in page
			else {
				response.sendRedirect("login.jsp");
				//return;
			}
		} catch (Exception e){
			System.out.print(e);
		}


		//doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// If the form was submitted
		String username;
		String password;
		String rememberMe;
		username = request.getParameter("username");
		password = request.getParameter("password");
		rememberMe = request.getParameter("rememberMe");
		
		request.setAttribute("username", username);
		request.setAttribute("password", password);
		request.setAttribute("rememberMe", rememberMe);
		request.getRequestDispatcher("AuthenticateServlet").include(request,response);
		
		
	}

}
