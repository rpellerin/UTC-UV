package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.Answer;
import beans.PlayableQuestion;
import beans.PlayableQuiz;
import beans.Quiz;

public class QuizzesDAO extends DAO<Quiz> {
	
	private final static String TABLE        = "QUIZ";
	private final static String SELECT_QUERY = "SELECT * FROM QUIZ WHERE quiz_id = ?";
	private final static String INSERT_QUERY = "INSERT INTO QUIZ(subject, creator) VALUES (?, ?)";
	private final static String UPDATE_QUERY = ""; // TODO if needed
	private final static String DELETE_QUERY = "DELETE FROM QUIZ WHERE quiz_id = ?";
	
	private final static String BIG_BOSS_QUERY = "SELECT "
			+ "quiz_id, "
			+ "subject, "
			+ "creator, "
			+ "question_id, "
			+ "QUESTION.question as actualQuestion, "
			+ "QUESTION._order as questionOrder, "
			+ "answer_id, answer, "
			+ "ANSWER._order as answerOrder, "
			+ "isCorrect, "
			+ "ANSWER.question as question_ref "
			+ "FROM QUESTION, QUIZ, ANSWER "
			+ "WHERE "
			+ "QUIZ.quiz_id = ? and "
			+ "QUIZ.quiz_id = QUESTION.quiz and "
			+ "ANSWER.question = QUESTION.question_id AND "
			+ "QUESTION.isActive = true AND QUIZ.isActive = true and ANSWER.isActive = true "
			+ "ORDER BY QUESTION._order ASC, ANSWER._order ASC";
	
	
	public QuizzesDAO(Connection c) {
		super(c);
	}

	@Override
	public String getTableName() {
		return TABLE;
	}

	@Override
	protected Quiz toBean(ResultSet rs, Quiz q) throws SQLException {
		if (q == null)
			q = new Quiz();
		q.setActive(rs.getBoolean("isActive"));
		q.setQuiz_id(rs.getInt("quiz_id"));
		q.setCreator(rs.getString("creator"));
		q.setSubject(rs.getString("subject"));
		return q;
	}

	@Override
	protected PreparedStatement prepareStatementFromBean(PreparedStatement ps, Quiz q, String req) throws SQLException {
		switch(req) {
			case DELETE_QUERY:
			case SELECT_QUERY:
				ps.setInt(1, q.getQuiz_id());
				break;
			case INSERT_QUERY:
				ps.setString(1, q.getSubject());
				ps.setString(2, q.getCreator());
				break;
			case BIG_BOSS_QUERY:
				ps.setInt(1, q.getQuiz_id());
		}
		return ps;
	}

	@Override
	protected String getSelectString() {
		return SELECT_QUERY;
	}

	@Override
	protected String getInsertString() {
		return INSERT_QUERY;
	}
	
	@Override
	protected String getUpdateString() {
		return UPDATE_QUERY;
	}
	
	@Override
	protected String getDeleteString() {
		return DELETE_QUERY;
	}
	
	public PlayableQuiz findAndPopulateJavaObject(Quiz q) throws SQLException {
		PreparedStatement req = super.genericRequest(q, BIG_BOSS_QUERY);
		ResultSet res = req.executeQuery();
		
		PlayableQuiz p = new PlayableQuiz(q.getQuiz_id(),q.getSubject(),q.isActive(),q.getCreator());
		
		PlayableQuestion pq = null;
		Answer a = null;
		while(res.next()) {
			if (p.getCreator() == null) {
				p.setCreator(res.getString("creator"));
			}
			if (p.getSubject() == null) {
				p.setSubject(res.getString("subject"));
			}
			
			if (pq == null || pq.getQuestion_id() != res.getInt("question_id")) {
				pq = new PlayableQuestion(res.getInt("question_id"),res.getString("actualQuestion"),res.getInt("questionOrder"),true,q.getQuiz_id());
				p.addQuestion(pq);
			}
			
			if (a == null || a.getAnswer_id() != res.getInt("answer_id")) {
				a = new Answer(res.getInt("answer_id"),res.getString("answer"),res.getInt("answerOrder"),res.getBoolean("isCorrect"),true,res.getInt("question_ref"));
				pq.addAnswer(a);
			}
		}
		return p;
	}
}