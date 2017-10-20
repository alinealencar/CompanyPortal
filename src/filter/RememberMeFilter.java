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

	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		// Check if user chose Remember Me (automatically log the user in and redirect them to the home page)
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		try{
			Connection conn = null;
			//DatabaseAccess.createDatabase();
			conn = DatabaseAccess.connectDataBase();
			
			// Check if user has the RememberMe cookies (uuid and user) or if the user iscurrently logged in
			if(AuthenticationHelper.isRememberCookies(request, conn) || AuthenticationHelper.isLoggedIn(request.getSession())){
				response.sendRedirect("home.jsp");
			}
			else
				chain.doFilter(request, response);
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
