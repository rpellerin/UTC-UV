package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import beans.QuestionAnswer;

public class QuestionAnswersDAO extends DAO<QuestionAnswer> {
	
	private final static String TABLE        = "QQQAAANSWER";
	private final static String SELECT_QUERY = "SELECT * FROM "+TABLE+" WHERE course = ? AND question = ?";
	private final static String INSERT_QUERY = "INSERT INTO "+TABLE+"(course, question, answer) VALUES"
			+ "(?,?,?)";
	private final static String UPDATE_QUERY = ""; // TODO if needed
	private final static String DELETE_QUERY = "DELETE FROM "+TABLE+" WHERE course = ? and answer = ?";
	
	
	public QuestionAnswersDAO(Connection c) {
		super(c);
	}

	@Override
	public String getTableName() {
		return TABLE;
	}

	@Override
	protected QuestionAnswer toBean(ResultSet rs, QuestionAnswer q) throws SQLException {
		if (q == null)
			q = new QuestionAnswer();
		q.setAnswer(rs.getInt("answer"));
		q.setCourse(rs.getInt("course"));
		q.setQuestion(rs.getInt("question"));
		return q;
	}

	@Override
	protected PreparedStatement prepareStatementFromBean(PreparedStatement ps, QuestionAnswer q, String req) throws SQLException {
		switch(req) {
			case DELETE_QUERY:
			case SELECT_QUERY:
				ps.setInt(1, q.getCourse());
				ps.setInt(2, q.getQuestion());
				break;
			case INSERT_QUERY:
				ps.setInt(1, q.getCourse());
				ps.setInt(2, q.getQuestion());
				ps.setInt(3, q.getAnswer());
				break;
		}
		return ps;
	}

	public List<QuestionAnswer> findAll(int course) throws SQLException {
		List<QuestionAnswer> res = super.findAll();
		res.removeIf(el -> el.getCourse()!=course);
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