package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Answer;
import beans.Question;
import beans.User;
import dao.AnswersDAO;
import dao.ConnectionDB;
import dao.QuestionsDAO;

public class AnswersManagementController extends HttpServlet {

	public static final String REQUEST_QUESTION = "question";
	public static final String REQUEST_ANSWERS = "answers";
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getAttribute(LoginController.REQUEST_USER);
		if (user.isAdmin()) {
			String resource = request.getPathInfo();
			if (resource != null && resource.trim().length() > 1) {
				resource = resource.replace("/", "");
			}
			else {
				// TODO error
			}
			Question q = new Question();
			QuestionsDAO qd = null;
			try {
				qd = new QuestionsDAO(ConnectionDB.getInstance());
				
				q.setQuestion_id(Integer.parseInt(resource));
				
				AnswersDAO qtd = new AnswersDAO(ConnectionDB.getInstance());
				
				// DELETE
				String id = null;
				if ((id = request.getParameter("delete")) != null && id.trim().length() > 0) {
					Answer answerToDelete = new Answer();
					answerToDelete.setAnswer_id(Integer.parseInt(id));
					boolean ret = qtd.delete(answerToDelete);// TODO display message in HTML if deleted (1) or not (0)
				}
				
				request.setAttribute(REQUEST_ANSWERS, qtd.findAll(q.getQuestion_id())); // TODO handle order
				request.setAttribute(REQUEST_QUESTION, qd.find(q)); // TODO only if this quiz was created by current user can be deleted
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("message_error", e.getMessage()); // TODO in html
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			// TODO add a method isEditable() in quiz, which sets a boolean depending on its creator. 
			this.getServletContext().getRequestDispatcher("/WEB-INF/pages/answersmanagement.jsp").forward(request, response);
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
		String answer = utils.Requests.getRequestValue(request, "answer");
		String isCorrect = utils.Requests.getRequestValue(request, "isCorrect");
		
		int _question = Integer.parseInt(question);
		boolean _isCorrect = isCorrect != null && isCorrect.equals("on");

		Answer a = new Answer();
		a.setAnswer(answer);
		a.setCorrect(_isCorrect);
		a.setQuestion(_question);
		
		String error = "";
		try {
			if (answer == null || answer.trim().length() == 0
					|| question == null || question.trim().length() == 0) {
				throw new Exception("Missing fields");
			}
			
			AnswersDAO dao = new AnswersDAO(ConnectionDB.getInstance());
			QuestionsDAO qdao = new QuestionsDAO(ConnectionDB.getInstance());
			
			if (a.isCorrect()) {
				Question q = new Question();
				q.setQuestion_id(_question);
				int count = qdao.countNbCorrectAnswers(q);
				if (count == 1)
					throw new Exception("There's already a correct answer for this question.");
			}
			
			dao.insert(a);
			
		} catch (Exception e) {
			error = e.getMessage();
		}
		request.setAttribute("message_error", error);
		doGet(request, response); // TODO GIVE ERRORS here and handle them in onGet
	}
}
