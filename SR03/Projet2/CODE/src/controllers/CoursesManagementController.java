package controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Course;
import beans.User;
import dao.ConnectionDB;
import dao.CourseDAO;

public class CoursesManagementController extends HttpServlet {

	private static final long serialVersionUID = 3097485401211857454L;
	public static final String REQUEST_COURSES = "courses";
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getAttribute(LoginController.REQUEST_USER);
		if (user.isAdmin()) {
			try {
				CourseDAO c = new CourseDAO(ConnectionDB.getInstance());
				
				String id = null;
				if ((id = request.getParameter("delete")) != null && id.trim().length() > 0) {
					Course courseToDelete = new Course();
					courseToDelete.setCourse_id(Integer.parseInt(id));
					boolean ret = c.delete(courseToDelete);// TODO display message in HTML if deleted (1) or not (0)
				}
				
				request.setAttribute(REQUEST_COURSES, c.findAll());
			} catch (SQLException e) {
				// TODO request.setAttribute(error, e.error) + display in HTML
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/pages/coursesmanagement.jsp").forward(request, response);
	}
}
