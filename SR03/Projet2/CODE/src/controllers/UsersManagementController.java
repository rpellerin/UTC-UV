package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.User;
import dao.ConnectionDB;
import dao.UsersDAO;
import utils.Email;

public class UsersManagementController extends HttpServlet {

	private static final long serialVersionUID = 224355541211544664L;
	public static final String REQUEST_USERS = "users";
	private static final int NUMBER_PER_PAGE = 2;
	public static final String REQUEST_NB_PAGES = "countPages"; 
	public static final String REQUEST_CURRENT_PAGE = "currentPage";
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getAttribute(LoginController.REQUEST_USER);
		if (user.isAdmin()) {
			
			try {
				UsersDAO ud = new UsersDAO(ConnectionDB.getInstance());
				
				
				// DELETE
				String email = null;
				if ((email = request.getParameter("delete")) != null && email.trim().length() > 0) {
					User userToDelete = new User();
					userToDelete.setEmail(email);
					boolean ret = ud.delete(userToDelete);// TODO display message in HTML if deleted (1) or not (0)
					response.sendRedirect(request.getContextPath()+"/dashboard/users");
					return;
				}
				
				List<User> res = new ArrayList<User>();
				List<User> sqlRes = new ArrayList<User>();
				String page = null;
				int _page = 1;
				
				// SEARCH
				String recherche = null;
				if ((recherche = request.getParameter("recherche")) != null && recherche.trim().length() > 0) {
					User userToFind = new User();
					userToFind.setName(recherche);
					userToFind = ud.find(userToFind);
					res.add(userToFind);
					
				}
				else {
					// PAGINATION
					if (!((page = request.getParameter("page")) != null && page.trim().length() > 0)) {
						page = "1";
					}
					sqlRes = ud.findAll();
					_page = Integer.parseInt(page);
					if (_page < 1) _page = 1;
					
					res = new ArrayList<User>();
					for (int i = (_page-1)*NUMBER_PER_PAGE; i <_page*NUMBER_PER_PAGE && i < sqlRes.size(); i++) {
						res.add(sqlRes.get(i));
					}
				}
				
				request.setAttribute(REQUEST_NB_PAGES, (int) Math.ceil((double)sqlRes.size() / NUMBER_PER_PAGE));
				request.setAttribute(REQUEST_USERS, res);
				request.setAttribute(REQUEST_CURRENT_PAGE, _page);
			} catch (SQLException e) {
				// TODO request.setAttribute(error, e.error) + display in HTML
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			this.getServletContext().getRequestDispatcher("/WEB-INF/pages/usersmanagement.jsp").forward(request, response);
		}
		else
			response.sendRedirect(request.getContextPath()+"/dashboard");
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User connUser = (User) request.getAttribute(LoginController.REQUEST_USER);
		if (!connUser.isAdmin()) {
			// TODO ERROR
		}
		
		String email       = utils.Requests.getRequestValue(request, "email"),
			   password    = utils.Requests.getRequestValue(request, "password"),
			   name        = utils.Requests.getRequestValue(request, "name"),
			   company     = utils.Requests.getRequestValue(request, "company"),
			   phoneNumber = utils.Requests.getRequestValue(request, "phoneNumber"),
			   admin       = utils.Requests.getRequestValue(request, "isAdmin");

		
		User user = new User(email,password,name,company,phoneNumber,null,true,admin!=null&&admin.equals("on"));
		try {
			if (!user.isValid()) {
				throw new Exception("Missing fields");
			}
			
			UsersDAO u = new UsersDAO(ConnectionDB.getInstance());
			
			u.insert(user);
			Email _email = new Email(user.getEmail(),"Votre compte a été créé","Votre compte a bien été créé avec le mot de passe suivant : "+user.getPassword());
			_email.send();
			
		} catch (Exception e) {
			//error = e.getMessage(); // TODO
		}
		doGet(request, response); // TODO GIVE ERRORS here and handle them in onGet
	}
}
