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
* Description: This class acts as filter for certain pages. The Authentication filter
* 				is applied to pages that require a user to be logged in in order to
* 				be viewed.
*********************************************************************************/

package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import helper.AuthenticationHelper;

@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter {

	@Override
	public void destroy() {

	}

	/**
	 * This method holds the logic of the filter. It checks whether the a user is 
	 * logged in by their session, using the isLoggedIn method from the AuthenticationHelper
	 * class.
	 * 
	 * @see helper.AuthenticationHelper#isLoggedIn(HttpSession)
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpSession session = request.getSession();
		
		if(AuthenticationHelper.isLoggedIn(session)) {
			chain.doFilter(request, response);
		}
		else {
			//Redirect user back to the login page
			response.sendRedirect(request.getContextPath());
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
