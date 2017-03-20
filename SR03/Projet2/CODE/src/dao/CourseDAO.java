package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.Course;

public class CourseDAO extends DAO<Course> {
	
	private final static String TABLE        = "COURSE";
	private final static String SELECT_QUERY = "SELECT * FROM "+TABLE+" WHERE course_id = ?";
	private final static String SELECT_QUERY2= "SELECT * FROM "+TABLE+" WHERE user = ? AND quiz = ?";
	private final static String INSERT_QUERY = "INSERT INTO "+TABLE+"(duration, score, user, quiz) VALUES"
			+ "(?,?,?,?)";
	private final static String UPDATE_QUERY = ""; // TODO if needed
	private final static String DELETE_QUERY = "DELETE FROM "+TABLE+" WHERE course_id = ?";
	
	
	public CourseDAO(Connection c) {
		super(c);
	}

	@Override
	public String getTableName() {
		return TABLE;
	}

	@Override
	protected Course toBean(ResultSet rs, Course c) throws SQLException {
		if (c == null)
			c = new Course();
		c.setCourse_id(rs.getInt("course_id"));
		c.setDate(rs.getDate("date"));
		c.setDuration(rs.getInt("duration"));
		c.setScore(rs.getInt("score"));
		c.setUser(rs.getString("user"));
		c.setQuiz(rs.getInt("quiz"));
		return c;
	}

	@Override
	public Course find(Course c) throws SQLException {
		if (c.getCourse_id() != -1) return super.find(c);
		PreparedStatement req = super.genericRequest(c, SELECT_QUERY2);
		ResultSet res = req.executeQuery();
		if (!res.first()) {
			System.out.println("null");
			return null;
		}
		return toBean(res,c);
	}
	
	@Override
	protected PreparedStatement prepareStatementFromBean(PreparedStatement ps, Course c, String req) throws SQLException {
		switch(req) {
			case DELETE_QUERY:
			case SELECT_QUERY:
				ps.setInt(1, c.getCourse_id());
				break;
			case SELECT_QUERY2:
				ps.setString(1, c.getUser());
				ps.setInt(2, c.getQuiz());
				break;
			case INSERT_QUERY:
				ps.setInt(1, c.getDuration());
				ps.setInt(2, c.getScore());
				ps.setString(3, c.getUser());
				ps.setInt(4, c.getQuiz());
				break;
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
}