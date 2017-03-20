package controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Question;
import beans.Quiz;
import beans.User;
import dao.ConnectionDB;
import dao.QuestionsDAO;
import dao.QuizzesDAO;

public class QuestionsManagementController extends HttpServlet {

	private static final long serialVersionUID = 1759620581262972555L;
	public static final String REQUEST_QUIZ = "quiz";
	public static final String REQUEST_QUESTIONS = "questions";
	public static final String REQUEST_ERROR = "message_error";
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getAttribute(LoginController.REQUEST_USER);
		if (user.isAdmin()) {
			String resource = request.getPathInfo();
			if (resource != null && resource.trim().length() > 1) {
				resource = resource.replace("/", "");
			}
			else {
				// TODO error and return
			}
			
			Quiz quiz = new Quiz();
			QuizzesDAO qd = null;
			
			try {
				qd = new QuizzesDAO(ConnectionDB.getInstance());
				QuestionsDAO qtd = new QuestionsDAO(ConnectionDB.getInstance());
				
				// DELETE
				String id = null;
				if ((id = request.getParameter("delete")) != null && id.trim().length() > 0) {
					Question questionToDelete = new Question();
					questionToDelete.setQuestion_id(Integer.parseInt(id));
					boolean ret = qtd.delete(questionToDelete);// TODO display message in HTML if deleted (1) or not (0)
				}
				
				quiz.setQuiz_id(Integer.parseInt(resource));
				
				request.setAttribute(REQUEST_QUESTIONS, qtd.findAll(quiz.getQuiz_id())); // TODO handle order
				request.setAttribute(REQUEST_QUIZ, qd.find(quiz)); // TODO only if this quiz was created by current user can be deleted
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("message_error", e.getMessage()); // TODO in html
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			// TODO add a method isEditable() in quiz, which sets a boolean depending on its creator. 
			this.getServletContext().getRequestDispatcher("/WEB-INF/pages/questionsmanagement.jsp").forward(request, response);
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
		
		String question = utils.Requests.getRequestValue(request, "question");
		String quiz = utils.Requests.getRequestValue(request, "quiz");
		
		int _quiz = Integer.parseInt(quiz);

		Question q = new Question();
		q.setQuestion(question);
		q.setQuiz(_quiz);
		String error = "";
		try {
			if (question == null || question.trim().length() == 0) {
				throw new Exception("Missing fields");
			}
			
			QuestionsDAO dao = new QuestionsDAO(ConnectionDB.getInstance());
			
			dao.insert(q);
			
		} catch (Exception e) {
			if (e.getMessage().contains("Duplicate"))
				error = "This question is already existing.";
			else
				error = e.getMessage();
		}
		request.setAttribute(REQUEST_ERROR, error);
		doGet(request, response); // TODO GIVE ERRORS here and handle them in onGet
	}
}
