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
* Description: This class provides methods for creating, accessing and deleting
* 				cookies.
*********************************************************************************/

package helper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtilities {
	
	/**
	 * This method returns the value stored in a specific cookie.
	 * @param request	HttpServletRequest object
	 * @param cookieName	String that holds the name of the cookie that will be accessed
	 * @param defaultValue	String that holds a default value to be returned in case no cookie with
	 * 							a name cookieName is found
	 * @return	String	This returns the value of the cookie or a default value
	 */
	public static String getCookieValue(HttpServletRequest request, String cookieName, String defaultValue) {
		Cookie[] cookies = request.getCookies();
			if( cookies != null ) {
				for(Cookie cookie : cookies){
					if(cookieName.equals(cookie.getName())){
						return (cookie.getValue());
					}
				}
			}
			return defaultValue;
		}
	
	/**
	 * This method deleted a cookie by cookie name.
	 * @param strCookieName	String that holds the name of the cookie that will be deleted.
	 * @return Cookie	This returns a Cookie object with an empty string value and maxAge of 0 (deleted).
	 */
	public static Cookie eraseCookie(String strCookieName) {
	    Cookie cookie = new Cookie(strCookieName, "");
	    cookie.setMaxAge(0);
	    cookie.setValue("");
	    return cookie;
	}
	
	/**
	 * This creates a cookie that will be used in the implementation of 
	 * the RememberMe feature. This cookie has a maximum age  of one year.
	 * @param cookieName	String that holds the name of the cookie
	 * @param cookieValue	String that holds the value of the cookie
	 * @return	Cookie	This returns a Cookie object with the name cookieName,
	 * 					value cookieValue and maximum age of one year.
	 */
	public static Cookie createRememberMeCookie(String cookieName, String cookieValue) {
		Cookie c = new Cookie(cookieName, cookieValue);
		c.setMaxAge(365 * 24 * 60 * 60); // One year
		return c;
	}

}

