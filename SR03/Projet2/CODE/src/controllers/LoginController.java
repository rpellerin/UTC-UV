package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;
import dao.ConnectionDB;
import dao.UsersDAO;
import filters.RestrictedPagesToConnectedUser;

/**
 * Servlet used to authenticate an user
 * @author Romain Pellerin
 */
public class LoginController extends HttpServlet {

	public static final String SESSION_USER = "user";
	public static final String REQUEST_USER = "user";
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (RestrictedPagesToConnectedUser.getUserIfConnected(request) != null)
			response.sendRedirect(request.getContextPath()+"/dashboard");
		else {
			this.getServletContext().getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
		}
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String email = utils.Requests.getRequestValue(request, "email");
		String password = utils.Requests.getRequestValue(request, "password");
		
		request.setAttribute("email", email);
		
		User user = null;
		String error = null;
		try {
			user = new User(email, password, null, null, null, null, null, null);
			
			if (!user.isValid()) {
				throw new Exception("Missing fields");
			}
			
			UsersDAO u = new UsersDAO(ConnectionDB.getInstance());
			
			if ((user = u.find(user)) != null && user.isActive()) {
				session.setAttribute(SESSION_USER, user);
				response.sendRedirect("dashboard");
				return;
			}
			else {
				error = "User not found";
			}
		} catch (Exception e) {
			error = e.getMessage();
		}
		
		request.setAttribute("message_error", error);
		
		session.setAttribute(SESSION_USER, null);
		this.getServletContext().getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
	}	
}
