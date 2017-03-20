package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import beans.Answer;

public class AnswersDAO extends DAO<Answer> {
	
	private final static String TABLE        = "ANSWER";
	private final static String SELECT_QUERY = "SELECT * FROM "+TABLE+" WHERE answer_id = ? ORDER BY _order";
	private final static String INSERT_QUERY = "INSERT INTO "+TABLE+"(answer, _order, isCorrect, question) VALUES"
			+ "(?,"
			+ "( SELECT count+1 FROM (SELECT IFNULL((SELECT MAX(_order) FROM "+TABLE+" WHERE question = ?),0) as count) as tmp),"
			+ "?, ?)";
	private final static String UPDATE_QUERY = ""; // TODO if needed
	private final static String DELETE_QUERY = "DELETE FROM "+TABLE+" WHERE answer_id = ?";
	
	
	public AnswersDAO(Connection c) {
		super(c);
	}

	@Override
	public String getTableName() {
		return TABLE;
	}

	@Override
	protected Answer toBean(ResultSet rs, Answer a) throws SQLException {
		if (a == null)
			a = new Answer();
		a.set_order(rs.getInt("_order"));
		a.setActive(rs.getBoolean("isActive"));
		a.setAnswer(rs.getString("answer"));
		a.setAnswer_id(rs.getInt("answer_id"));
		a.setQuestion(rs.getInt("question"));
		a.setCorrect(rs.getBoolean("isCorrect"));
		return a;
	}

	@Override
	protected PreparedStatement prepareStatementFromBean(PreparedStatement ps, Answer q, String req) throws SQLException {
		switch(req) {
			case DELETE_QUERY:
			case SELECT_QUERY:
				ps.setInt(1, q.getAnswer_id());
				break;
			case INSERT_QUERY:
				ps.setString(1, q.getAnswer());
				ps.setInt(2, q.getQuestion());
				ps.setBoolean(3, q.isCorrect());
				ps.setInt(4, q.getQuestion());
				break;
		}
		return ps;
	}

	public List<Answer> findAll(int question_id) throws SQLException {
		List<Answer> res = super.findAll();
		res.removeIf(el -> el.getQuestion()!=question_id);
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