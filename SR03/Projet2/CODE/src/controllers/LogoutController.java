package controllers;

import java.io.IOException;
import java.sql.Connection;

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
 * Servlet used to logout an user
 * @author Romain Pellerin
 */
public class LogoutController extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute(LoginController.SESSION_USER, null);
		response.sendRedirect(request.getContextPath()+"/login");
	}
}
