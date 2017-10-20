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
* Description: This class contains methods to get cookie values and delete cookie
*********************************************************************************/

package helper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtilities {
	
	public static String getCookieValue(HttpServletRequest request, String cookieName, String defaultValue) {
		Cookie[] cookies = request.getCookies();
			if( cookies != null ) {
				for( Cookie cookie : cookies ){
					if(cookieName.equals(cookie.getName())){
						return (cookie.getValue());
					}
				}
			}
			return defaultValue;
		}
	
	public static Cookie eraseCookie(String strCookieName) {
	    Cookie cookie = new Cookie(strCookieName, "");
	    cookie.setMaxAge(0);
	    cookie.setValue("");
	    return cookie;
	}

}

