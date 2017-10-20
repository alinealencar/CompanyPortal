package helper;

import javax.servlet.http.HttpServletRequest;

public class SessionUtilities {

	public static void eraseSessionAttribute (String sessionName, String defaultValue, HttpServletRequest request) {
		defaultValue = "";
		request.getSession().setAttribute(sessionName, defaultValue);
	}
}
