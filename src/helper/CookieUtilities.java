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
	
	public static Cookie createRememberMeCookie(String cookieName, String cookieValue) {
		Cookie c = new Cookie(cookieName, cookieValue);
		c.setMaxAge(365 * 24 * 60 * 60); // One year
		return c;
	}

}

