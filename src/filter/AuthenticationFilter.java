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