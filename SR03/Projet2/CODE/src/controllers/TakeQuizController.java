package controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.PlayableQuiz;
import beans.Quiz;
import beans.User;
import dao.ConnectionDB;
import dao.QuizzesDAO;

public class TakeQuizController extends HttpServlet {

	private static final long serialVersionUID = 7955869548734605054L;
	public static final String REQUEST_QUIZ = "quiz";
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getAttribute(LoginController.REQUEST_USER);
		if (!user.isAdmin()) {
			String quizID = utils.Requests.getRequestValue(request, "id");
			int id = 0;
			
			if (quizID != null && quizID.trim().length() > 0) {
				try {
					id = Integer.parseInt(quizID);
				}
				catch(NumberFormatException e) {
					// TODO no redirect, just show an error msg
					response.sendRedirect(request.getContextPath()+"/dashboard");
					return;
				}
			}
			else {
				// TODO no redirect, just show an error msg
				response.sendRedirect(request.getContextPath()+"/dashboard");
				return;
			}
			
			//TODO CHECK ADMIN OR SAME USER
			
			PlayableQuiz quiz = new PlayableQuiz();
			quiz.setQuiz_id(id);
			
			QuizzesDAO qd = null;
			
			try {
				qd = new QuizzesDAO(ConnectionDB.getInstance());
				
				Quiz q = qd.find(quiz);
				quiz.setCreator(q.getCreator());
				quiz.setSubject(q.getSubject());
				quiz = qd.findAndPopulateJavaObject(quiz);
				if (quiz.getQuestions().size() == 0) {
					request.setAttribute("message_error", "Ce questionnaire ne comporte pas encore de question.");
				}

				request.setAttribute(REQUEST_QUIZ, quiz);
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("message_error", e.getMessage()); // TODO in html
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} 
			request.setAttribute("date", new java.util.Date().getTime());
			this.getServletContext().getRequestDispatcher("/WEB-INF/pages/takequiz.jsp").forward(request, response);
		}
		else
			response.sendRedirect(request.getContextPath()+"/dashboard");
	}
}
