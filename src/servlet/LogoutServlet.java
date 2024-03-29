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
* Description: This servlet is responsible for logging out the user from the
* 				application.
*********************************************************************************/

package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import helper.CookieUtilities;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LogoutServlet() {
        super();
    }

    /**
     * This method is called whenever this servlet gets a GET request. It is responsible
     * for invalidating the current session and redirecting the user back to the login page
     * login.jsp, thus, logging out the user.
     * 
     *  @param request HttpServletRequest request
     *  @param response HttpServletResponse response
     *  @return void
     *  @exception ServletException
     *  @exception IOException
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		response.setContentType("text/html");
		
		Cookie uuid = CookieUtilities.eraseCookie("uuid");
		Cookie user = CookieUtilities.eraseCookie("user");
		response.addCookie(uuid);
		response.addCookie(user);
		
		if(session != null) {
			session.invalidate();
		}
		
		response.sendRedirect("Login");
	}

	/**
	 * This method is called whenever this servlet gets a POST request. It automatically
	 * calls the doGet method.
	 * 
	 *  @param request HttpServletRequest request
     *  @param response HttpServletResponse response
     *  @return void
     *  @exception ServletException
     *  @exception IOException
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
