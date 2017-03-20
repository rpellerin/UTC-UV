package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Answer;
import beans.Course;
import beans.PlayableQuestion;
import beans.PlayableQuiz;
import beans.QuestionAnswer;
import beans.Quiz;
import beans.User;
import dao.ConnectionDB;
import dao.CourseDAO;
import dao.QuestionAnswersDAO;
import dao.QuizzesDAO;

public class FinishQuizController extends HttpServlet {

	private static final long serialVersionUID     = 7949838618333831771L;
	public static final String REQUEST_QUIZ 	   = "quiz";
	public static final String REQUEST_USER_ANSWER = "user_answers";
	public static final String REQUEST_SCORE       = "score";
	public static final String REQUEST_DURATION    = "duration";
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getAttribute(LoginController.REQUEST_USER);

		// DAOs
		QuizzesDAO qd = null;
		CourseDAO coursedao = null;
		QuestionAnswersDAO dao = null;
		
		String course_id = utils.Requests.getRequestValue(request, "id");
		
		// Models
		Course course = new Course();
		PlayableQuiz quiz = new PlayableQuiz();
		List<QuestionAnswer> questionanswers = null;
		
		course.setCourse_id(Integer.parseInt(course_id));
		
			
		try {
			qd 		  = new QuizzesDAO(ConnectionDB.getInstance());
			coursedao = new CourseDAO(ConnectionDB.getInstance());
			dao       = new QuestionAnswersDAO(ConnectionDB.getInstance());
			
			course = coursedao.find(course);
			
			// TODO CHECK ADMIN OR SAME USER
			
			quiz.setQuiz_id(course.getQuiz());
			
			quiz = qd.findAndPopulateJavaObject(quiz);
			
			
			questionanswers = dao.findAll(course.getCourse_id());
			
			HashMap<Integer, Integer> answers = new HashMap<Integer, Integer>();
			for (QuestionAnswer t : questionanswers) {
				answers.put(t.getQuestion(), t.getAnswer());
			}
			
			request.setAttribute(REQUEST_QUIZ, quiz);
			request.setAttribute(REQUEST_USER_ANSWER, answers);
			request.setAttribute(REQUEST_SCORE, course.getScore());
			request.setAttribute(REQUEST_DURATION, course.getDuration());
			this.getServletContext().getRequestDispatcher("/WEB-INF/pages/finishquiz.jsp").forward(request, response);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// TODO redirect vers error page
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User connUser = (User) request.getAttribute(LoginController.REQUEST_USER);
		if (connUser.isAdmin()) {
			response.sendRedirect("../dashboard");
			return;
		}
	
		String quizIDform = null;
		String date = null;
		
		
		HashMap<Integer, Integer> answers = new HashMap<Integer, Integer>();
		
		Map<String, String[]> vals = request.getParameterMap();
		for (Map.Entry<String, String[]> entry : vals.entrySet()) {
			System.out.println(entry.getKey());
			System.out.println("=> "+entry.getValue()[0]);
			switch (entry.getKey()) {
			case "date":
				date = entry.getValue()[0];
				break;
			case "quiz":
				quizIDform = entry.getValue()[0];
				break;
			default:
				answers.put(Integer.parseInt(entry.getKey()), Integer.parseInt(entry.getValue()[0]));
			}
		}
		
		if (quizIDform == null || date == null) {
			// error
			request.setAttribute("message_error", "Invalid request");
			doGet(request, response);
			return;
		}
		
		// CALCUL DU SCORE
		Quiz quiz = new Quiz();
		quiz.setQuiz_id(Integer.parseInt(quizIDform));
		QuizzesDAO qd = null;
		CourseDAO coursedao = null;
		QuestionAnswersDAO dao = null;
		
		int countQuestions = 0, correctQuestions = 0;
		try {
			coursedao = new CourseDAO(ConnectionDB.getInstance());
			qd = new QuizzesDAO(ConnectionDB.getInstance());
			dao = new QuestionAnswersDAO(ConnectionDB.getInstance());
			PlayableQuiz pq = qd.findAndPopulateJavaObject(quiz);
			for (PlayableQuestion plaQue: pq.getQuestions()) {
				countQuestions++;
				
				for (Answer a: plaQue.getAnswers()) {
					if(answers.get(plaQue.getQuestion_id()).equals(a.getAnswer_id())) {
						if (a.isCorrect()) {
							correctQuestions++;
						}
					}
					
				}
			}
			int score = (100 * correctQuestions) / countQuestions;
			Course course = new Course();
			long duration = new java.util.Date().getTime() - Long.parseLong(date);
			duration /= 1000; 
			course.setDuration((int)duration);
			course.setUser(connUser.getEmail());
			course.setQuiz(Integer.parseInt(quizIDform));
			course.setScore(score);
			coursedao.insert(course);
			int course_id = coursedao.find(course).getCourse_id();
			
			for (Entry<Integer, Integer> e : answers.entrySet()) {
				QuestionAnswer qa = new QuestionAnswer();
				qa.setCourse(course_id);
				qa.setQuestion(e.getKey());
				qa.setAnswer(e.getValue());
				
				dao.insert(qa);
			}
			response.sendRedirect(request.getContextPath()+"/dashboard/finishquiz?id="+String.valueOf(course_id));
			return;
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// HANDLE ERRORS HERE
		this.getServletContext().getRequestDispatcher("/WEB-INF/pages/finishquiz.jsp").forward(request, response);
	}
}
