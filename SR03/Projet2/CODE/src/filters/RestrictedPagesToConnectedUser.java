package filters;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import beans.User;
import controllers.LoginController;

/**
 * Filter to restrict pages to connected users only
 * @author Romain Pellerin
 */
public class RestrictedPagesToConnectedUser implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		User user;
		if ((user = getUserIfConnected(request)) == null)
			response.sendRedirect(request.getContextPath()+"/logout"); // ensures logout
		else {
			//request.setCharacterEncoding("UTF-8");
			request.setAttribute(LoginController.REQUEST_USER, user);
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {}
	
	/**
	 * Returns the User if present in Tomcat sessions
	 * @param request The {@link HttpServletRequest} to read from (to get the session)
	 * @return The user if connected, or null
	 */
	public static User getUserIfConnected(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = null;
		if ((user = (User) session.getAttribute(LoginController.SESSION_USER)) != null) {
			return user;
		}
		else
			return null;
	}
}
