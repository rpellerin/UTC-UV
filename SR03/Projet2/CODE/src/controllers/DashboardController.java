package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Course;
import beans.User;
import dao.ConnectionDB;
import dao.CourseDAO;
import dao.QuizzesDAO;

public class DashboardController extends HttpServlet {

	private static final long serialVersionUID = 4960035699713242483L;
	public static final String REQUEST_QUIZZES = "quizzes";
	public static final String REQUEST_COURSES = "courses";
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getAttribute(LoginController.REQUEST_USER);
		if (!user.isAdmin()) {
			try {
				QuizzesDAO qd = new QuizzesDAO(ConnectionDB.getInstance());
				CourseDAO cd = new CourseDAO(ConnectionDB.getInstance());
				List<Course> courses = cd.findAll();
				HashMap<Integer,Integer[]> map = new HashMap<Integer,Integer[]>();
				for(Course c : courses) {
					Integer[] a = {c.getScore(),c.getCourse_id()};
					map.put(c.getQuiz(), a);
				}
				request.setAttribute(REQUEST_QUIZZES, qd.findAll());
				request.setAttribute(REQUEST_COURSES, map);
			} catch (SQLException e) {
				// TODO request.setAttribute(error, e.error) + display in HTML
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/pages/dashboard.jsp").forward(request, response);
	}
}
