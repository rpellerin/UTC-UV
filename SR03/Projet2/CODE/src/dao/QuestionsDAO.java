package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import beans.Question;

public class QuestionsDAO extends DAO<Question> {
	
	private final static String TABLE                     = "QUESTION";
	private final static String SELECT_QUERY              = "SELECT * FROM "+TABLE+" WHERE question_id = ? ORDER BY _order";
	private final static String INSERT_QUERY              = "INSERT INTO "+TABLE+"(question, _order, quiz) VALUES"
			+ "(?,"
			+ "( SELECT count+1 FROM (SELECT IFNULL((SELECT MAX(_order) FROM "+TABLE+" WHERE quiz = ?),0) as count) as tmp),"
			+ "?)";
	private final static String UPDATE_QUERY              = ""; // TODO if needed
	private final static String SELECT_NB_CORRECT_ANSWERS = "select * from QUESTION, ANSWER "
			+ "where QUESTION.question_id = ANSWER.question and ANSWER.isCorrect = true and QUESTION.question_id = ?";
	private final static String DELETE_QUERY              = "DELETE FROM "+TABLE+" WHERE question_id = ?";
	
	
	public QuestionsDAO(Connection c) {
		super(c);
	}

	@Override
	public String getTableName() {
		return TABLE;
	}

	@Override
	protected Question toBean(ResultSet rs, Question q) throws SQLException {
		if (q == null)
			q = new Question();
		q.set_order(rs.getInt("_order"));
		q.setActive(rs.getBoolean("isActive"));
		q.setQuestion(rs.getString("question"));
		q.setQuestion_id(rs.getInt("question_id"));
		q.setQuiz(rs.getInt("quiz"));
		return q;
	}

	@Override
	protected PreparedStatement prepareStatementFromBean(PreparedStatement ps, Question q, String req) throws SQLException {
		switch(req) {
			case DELETE_QUERY:
			case SELECT_QUERY:
			case SELECT_NB_CORRECT_ANSWERS:
				ps.setInt(1, q.getQuestion_id());
				break;
			case INSERT_QUERY:
				ps.setString(1, q.getQuestion());
				ps.setInt(2, q.getQuiz());
				ps.setInt(3, q.getQuiz());
				break;
		}
		return ps;
	}

	public int countNbCorrectAnswers(Question q) throws SQLException {
		PreparedStatement req = super.genericRequest(q, SELECT_NB_CORRECT_ANSWERS);
		ResultSet res = req.executeQuery();
		
		int size = 0;
		while (res.next()) {
		    size++;
		}
		
		return size;
	}
	
	public List<Question> findAll(int quiz_d) throws SQLException {
		List<Question> res = super.findAll();
		res.removeIf(el -> el.getQuiz()!=quiz_d);
		return res;
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
}