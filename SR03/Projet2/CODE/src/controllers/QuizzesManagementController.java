package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Quiz;
import beans.User;
import dao.ConnectionDB;
import dao.QuizzesDAO;

public class QuizzesManagementController extends HttpServlet {

	public static final String REQUEST_QUIZZES = "quizzes";
	public static final String REQUEST_ERROR = "message_error";
	private static final int NUMBER_PER_PAGE = 2;
	public static final String REQUEST_NB_PAGES = "countPages";
	public static final String REQUEST_CURRENT_PAGE = "currentPage";
	
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getAttribute(LoginController.REQUEST_USER);
		if (user.isAdmin()) {
			
			try {
				QuizzesDAO qd = new QuizzesDAO(ConnectionDB.getInstance());
				
				// DELETE
				String id = null;
				if ((id = request.getParameter("delete")) != null && id.trim().length() > 0) {
					Quiz quizToDelete = new Quiz();
					quizToDelete.setQuiz_id(Integer.parseInt(id));
					boolean ret = qd.delete(quizToDelete);// TODO display message in HTML if deleted (1) or not (0)
				}
				
				
				// PAGINATION
				String page = null;
				if (!((page = request.getParameter("page")) != null && page.trim().length() > 0)) {
					page = "1";
				}
				List<Quiz> sqlRes = qd.findAll();
				int _page = Integer.parseInt(page);
				if (_page < 1) _page = 1;
				
				List<Quiz> res = new ArrayList<Quiz>();
				for (int i = (_page-1)*NUMBER_PER_PAGE; i <_page*NUMBER_PER_PAGE && i < sqlRes.size(); i++) {
					res.add(sqlRes.get(i));
				}
				
				request.setAttribute(REQUEST_NB_PAGES, (int) Math.ceil((double)sqlRes.size() / NUMBER_PER_PAGE));
				request.setAttribute(REQUEST_QUIZZES, res);
				request.setAttribute(REQUEST_CURRENT_PAGE, _page);
				
				
				
				request.setAttribute(REQUEST_QUIZZES, res); // TODO only quizzes created by current user can be deleted
				// TODO add a method isEditable() in quiz, which sets a boolean depending on its creator. 
			} catch (SQLException e) {
				// TODO request.setAttribute(error, e.error) + display in HTML
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			this.getServletContext().getRequestDispatcher("/WEB-INF/pages/quizzesmanagement.jsp").forward(request, response);
		}
		else
			response.sendRedirect("../dashboard");
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User connUser = (User) request.getAttribute(LoginController.REQUEST_USER);
		//request.setCharacterEncoding("UTF-8");
		if (!connUser.isAdmin()) {
			// TODO ERROR
		}
		String subject = utils.Requests.getRequestValue(request, "subject");

		Quiz q = new Quiz();
		q.setSubject(subject);
		q.setCreator(connUser.getEmail());
		String error = "";
		try {
			if (subject == null || subject.trim().length() == 0) {
				throw new Exception("Missing fields");
			}
			
			QuizzesDAO dao = new QuizzesDAO(ConnectionDB.getInstance());
			
			dao.insert(q);
			
		} catch (Exception e) {
			if (e.getMessage().contains("Duplicate"))
				error = "This subject is already existing.";
			else
				error = e.getMessage();
		}
		request.setAttribute(REQUEST_ERROR, error);
		doGet(request, response); // TODO GIVE ERRORS here and handle them in onGet
	}
}
