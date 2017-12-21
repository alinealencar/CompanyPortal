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
* Description: This class acts as filter for certain pages. The RememberMe filter
* 				is applied to pages responsible for the login.
*********************************************************************************/


package filter;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DatabaseAccess;
import helper.AuthenticationHelper;

@WebFilter("/RememberMeFilter")
public class RememberMeFilter implements Filter {

	@Override
	public void destroy() {
	}

	/**
	 * This method holds the logic of the filter. It checks whether the cookies
	 * associated with the RememberMe feature exist, by calling the isRememberCookies
	 * method from the AuthenticationHelper class.
	 * @see helper.AuthenticationHelper#isRememberCookies(HttpServletRequest, Connection)
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		// Check if user chose Remember Me (automatically log the user in and redirect them to the home page)
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		try{
			// Check if user has the RememberMe cookies (uuid and user) or if the user iscurrently logged in
			if(AuthenticationHelper.isRememberCookies(request) || AuthenticationHelper.isLoggedIn(request.getSession())){
				response.sendRedirect("home.jsp");
			}
			else
				chain.doFilter(request, response);
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
